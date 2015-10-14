class VitaloDevicesController < ApplicationController
  before_action :set_vitalo_device, only: [:show, :update]

  def show
    respond_to do |format|
      format.json { render json: @vitalo_device }
    end
  end

  def create
    @vitalo_device = VitaloDevice.new(vitalo_device_params)

    respond_to do |format|
      if @vitalo_device.save
        format.html { redirect_to @vitalo_device.patient, notice: 'Vitalo Device was successfully registered.' }
        format.json { render :show, status: :created, location: @vitalo_device }
      else
        format.html { redirect_to @vitalo_device.patient, alert: 'Failed to register Vitalo Device. Please try again..' }
        format.json { render json: @vitalo_device.errors, status: :unprocessable_entity }
      end
    end
  end

  def update
    respond_to do |format|
      if @vitalo_device.update(vitalo_device_params)
        format.html { redirect_to @vitalo_device.patient, notice: 'Vitalo Device was successfully registered.' }
        format.json { render :show, status: :created, location: @vitalo_device }
      else
        format.html { redirect_to @vitalo_device.patient, alert: 'Failed to update Vitalo Device. Please try again..' }
        format.json { render json: @vitalo_device.errors, status: :unprocessable_entity }
      end
    end
  end


  private
  # Use callbacks to share common setup or constraints between actions.
  def set_vitalo_device
    @vitalo_device = VitaloDevice.find(params[:id])
  end

  # Never trust parameters from the scary internet, only allow the white list through.
  def vitalo_device_params
    params.require(:vitalo_device).permit(:serial_number, :patient_id)
  end
end
