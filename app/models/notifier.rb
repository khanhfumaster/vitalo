class Notifier < ActiveRecord::Base
  enum sensor: [:spo2, :pulse, :movement]

  belongs_to :vitalo_device
  has_many :notifications

  def thresholds
    vals = []
    vals.push "Minimum: #{threshold_min}" if threshold_min
    vals.push "Maximum: #{threshold_max}" if threshold_max
    vals.join(', ')
  end

  def notification_methods
    methods = []
    methods.push 'Email' if send_email
    methods.push 'App Notification' if send_app_notification
    methods.push 'SMS' if send_sms
    methods.join(', ')
  end

  def notify(reading, messages)
    notification = notifications.create( reading_id: reading.id, message: messages.join(', '),
                          user_id: vitalo_device.patient.user_id,
                          patient_id: vitalo_device.patient_id )

    Mailer.notify(notification).deliver
  end
end
