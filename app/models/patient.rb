class Patient < ActiveRecord::Base
  enum gender: [:male, :female, :other]
  belongs_to :user
  has_one :vitalo_device
  has_many :notifications

  validates_presence_of :name, :gender, :date_of_birth
end
