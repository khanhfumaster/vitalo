class Reading < ActiveRecord::Base

  enum sensor: [:spo2, :pulse, :movement]

  belongs_to :vitalo_device

end
