class Notifier < ActiveRecord::Base
  enum sensor: [:spo2, :pulse, :movement]

  belongs_to :vitalo_device

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

end
