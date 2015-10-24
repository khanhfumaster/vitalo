class ReadingsController < ApplicationController
  skip_before_action :verify_authenticity_token
  def new
    device_serial = params[:serial_number]

    @vitalo_device = VitaloDevice.find_by_serial_number(device_serial)

    if @vitalo_device
      @reading = @vitalo_device.readings.new(sensor: params[:sensor], value: params[:value])
      if @reading.save
        @reading.check_notifiers
        render json: {success: true, reading: @reading}
      else
        render json: {success: false}
      end
    else
      render json: {success: false}
    end
  end

  def notifications
    user_id = params[:id]

    user = User.find(user_id)

    if user
      n = user.notifications.select(:message, :created_at, :id)
      render json: n
    else
      render json: {success: false}
    end
  end

  def current_reading
    device = VitaloDevice.find_by_serial_number(params[:serial_number])

    if device
      render json: {pulse: device.pulse_readings.last.value, spo2: device.spo2_readings.last.value, movement: device.movement_readings.last.value}
    else
      render json: {success: false}
    end

  end

  def chart_data
    device_id = params[:device_id]
    sensor = params[:sensor]

    @vitalo_device = VitaloDevice.find(device_id)

    if @vitalo_device
      results = []
      notifications = []
      @readings = @vitalo_device.readings.where(sensor: Reading.sensors[sensor.to_sym]).order(created_at: :asc)

      @readings.each do |reading|
        results.push([reading.created_at.to_f * 1000, reading.value])
        if reading.notification
          notifications.push({x: reading.created_at.to_f * 1000, title: 'Notified', text: "Message: '#{reading.notification.message}'"})
        end
      end

      daily_average = []
      @daily_readings = @readings.group_by {|r| r.created_at.beginning_of_day}
      @daily_readings.each_key do |day|
        average = 0
        @daily_readings[day].map {|r| average += r.value }
        average = average / @daily_readings[day].count
        daily_average.push([ day.to_f * 1000, average])
      end

      weekly_average = []
      @weekly_readings = @readings.group_by {|r| r.created_at.beginning_of_month}
      @weekly_readings.each_key do |week|
        average = 0
        @weekly_readings[week].map {|r| average += r.value }
        average = average / @weekly_readings[week].count
        weekly_average.push([ week.to_f * 1000, average])
      end

      monthly_average = []
      @monthly_readings = @readings.group_by {|r| r.created_at.beginning_of_month}
      @monthly_readings.each_key do |month|
        average = 0
        @monthly_readings[month].map {|r| average += r.value }
        average = average / @monthly_readings[month].count
        monthly_average.push([ month.to_f * 1000, average])
      end


      thresholds = []

      @notifiers = @vitalo_device.notifiers.where(sensor: Notifier.sensors[sensor.to_sym], enabled: true)

      @notifiers.each do |notifier|
        color = "#%06x" % (rand * 0xffffff)
        thresholds.push({color: color, value: notifier.threshold_min, width: 2, zIndex: 9999, label: {text: "#{notifier.name} minimum", align: 'right'}}) unless notifier.threshold_min.nil?
        thresholds.push({color: color, value: notifier.threshold_max, width: 2, zIndex: 9999, label: {text: "#{notifier.name} maximum", align: 'right'}}) unless notifier.threshold_max.nil?
      end


      render json: {results: results, thresholds: thresholds, notifications: notifications,
                    daily_average: daily_average, weekly_average: weekly_average, monthly_average: monthly_average}
    else
      render json: {success: false}
    end
  end
end


