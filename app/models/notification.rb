class Notification < ActiveRecord::Base
  belongs_to :user
  belongs_to :patient
  belongs_to :reading
  belongs_to :notifier
end
