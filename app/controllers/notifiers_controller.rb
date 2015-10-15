class NotifiersController < ApplicationController
  before_action :set_vitalo_device
  before_action :set_notifier, only: [:show, :edit, :update, :destroy]

  layout false

  # GET /notifiers
  # GET /notifiers.json
  def index
    @notifiers = @vitalo_device.notifiers
  end

  # GET /notifiers/1
  # GET /notifiers/1.json
  def show
  end

  # GET /notifiers/new
  def new
    @notifier = @vitalo_device.notifiers.new
  end

  # GET /notifiers/1/edit
  def edit
  end

  # POST /notifiers
  # POST /notifiers.json
  def create
    @notifier = @vitalo_device.notifiers.new(notifier_params)

    respond_to do |format|
      if @notifier.save
        format.html { redirect_to patient_path(@vitalo_device.patient, anchor: 'notifiers'), notice: 'Notifier was successfully created.' }
        format.json { render :show, status: :created, location: patient_path(@vitalo_device.patient, anchor: 'notifiers') }
      else
        format.html { render :new }
        format.json { render json: @notifier.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /notifiers/1
  # PATCH/PUT /notifiers/1.json
  def update
    respond_to do |format|
      if @notifier.update(notifier_params)
        format.html { redirect_to patient_path(@vitalo_device.patient, anchor: 'notifiers'), notice: 'Notifier was successfully updated.' }
        format.json { render :show, status: :ok, location: patient_path(@vitalo_device.patient, anchor: 'notifiers') }
      else
        format.html { render :edit }
        format.json { render json: @notifier.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /notifiers/1
  # DELETE /notifiers/1.json
  def destroy
    @notifier.destroy
    respond_to do |format|
      format.html { redirect_to patient_path(@vitalo_device.patient, anchor: 'notifiers'), notice: 'Notifier was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_notifier
      @notifier = Notifier.find(params[:id])
    end

    def set_vitalo_device
      @vitalo_device = VitaloDevice.find(params[:vitalo_device_id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def notifier_params
      params.require(:notifier).permit(:vitalo_device_id, :sensor, :enabled, :send_email, :send_app_notification, :send_sms, :threshold_min, :threshold_max, :name, :note)
    end
end
