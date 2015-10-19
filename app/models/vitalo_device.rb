class VitaloDevice < ActiveRecord::Base
  belongs_to :patient

  has_many :readings
  has_many :spo2_readings, -> { where sensor: Reading.sensors[:spo2]},
           class: Reading
  has_many :pulse_readings, -> { where sensor: Reading.sensors[:pulse]},
           class: Reading
  has_many :movement_readings, -> { where sensor: Reading.sensors[:movement]},
           class: Reading

  has_many :notifiers
  has_many :spo2_notifiers, -> { where sensor: Notifier.sensors[:spo2]}, class: Notifier
  has_many :pulse_notifiers, -> { where sensor: Notifier.sensors[:pulse]}, class: Notifier
  has_many :movement_notifiers, -> { where sensor: Notifier.sensors[:movement]}, class: Notifier
end
