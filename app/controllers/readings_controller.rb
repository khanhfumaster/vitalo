class ReadingsController < ApplicationController
  skip_before_action :verify_authenticity_token
  def new

    puts params

    device_serial = params[:serial_number]

    @vitalo_device = VitaloDevice.find_by_serial_number(device_serial)

    if @vitalo_device
      @reading = @vitalo_device.readings.new(sensor: params[:sensor], value: params[:value])
      if @reading.save
        render json: {success: true, reading: @reading}
      else
        render json: {success: false}
      end
    else
      render json: {success: false}
    end
  end

end


