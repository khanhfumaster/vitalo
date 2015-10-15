class VitaloDevice < ActiveRecord::Base
  belongs_to :patient

  has_many :readings
  has_many :spo2_readings, -> { where sensor: Reading.sensors[:spo2]},
           class: Reading
  has_many :pulse_readings, -> { where sensor: Reading.sensors[:pulse]},
           class: Reading
  has_many :movement_readings, -> { where sensor: Reading.sensors[:movement]},
           class: Reading
end
