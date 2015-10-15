json.array!(@notifiers) do |notifier|
  json.extract! notifier, :id, :device_id, :sensor, :enabled, :send_email, :send_app_notification, :send_sms, :threshold_min, :threshold_max, :name, :note
  json.url notifier_url(notifier, format: :json)
end
