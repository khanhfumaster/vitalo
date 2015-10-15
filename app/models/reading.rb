class Reading < ActiveRecord::Base

  enum sensors: [:spo2, :pulse, :movement]

  belongs_to :vitalo_device

end
