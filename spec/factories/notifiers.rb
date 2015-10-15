FactoryGirl.define do
  factory :notifier do
    device_id 1
sensor 1
enabled false
send_email false
send_app_notification false
send_sms false
threshold_min 1.5
threshold_max 1.5
name "MyString"
note "MyText"
  end

end
