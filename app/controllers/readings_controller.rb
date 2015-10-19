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


      thresholds = []

      @notifiers = @vitalo_device.notifiers.where(sensor: Notifier.sensors[sensor.to_sym], enabled: true)

      @notifiers.each do |notifier|
        color = "#%06x" % (rand * 0xffffff)
        thresholds.push({color: color, value: notifier.threshold_min, width: 2, zIndex: 9999, label: {text: "#{notifier.name} minimum", align: 'right'}}) unless notifier.threshold_min.nil?
        thresholds.push({color: color, value: notifier.threshold_max, width: 2, zIndex: 9999, label: {text: "#{notifier.name} maximum", align: 'right'}}) unless notifier.threshold_max.nil?
      end

      render json: {results: results, thresholds: thresholds, notifications: notifications}
    else
      render json: {success: false}
    end
  end

end


