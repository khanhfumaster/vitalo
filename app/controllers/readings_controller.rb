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
      @readings = @vitalo_device.readings.where(sensor: sensor).order(created_at: :asc)

      @readings.each do |reading|
        results.push([reading.created_at.to_f * 1000, reading.value])
      end

      render json: results
    else
      render json: {success: false}
    end
  end

end


