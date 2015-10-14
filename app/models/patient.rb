class Patient < ActiveRecord::Base
  enum gender: [:male, :female, :other]
  belongs_to :user

  validates_presence_of :name, :gender, :date_of_birth
end
