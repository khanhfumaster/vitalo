class Reading < ActiveRecord::Base

  enum sensor: [:spo2, :pulse, :movement]

  belongs_to :vitalo_device
  has_many :notifications

  def check_notifiers
    if spo2?
      notifiers = vitalo_device.spo2_notifiers.where(enabled: true)
    elsif pulse?
      notifiers = vitalo_device.pulse_notifiers.where(enabled: true)
    elsif movement?
      notifiers = vitalo_device.movement_notifiers.where(enabled: true)
    end

    notifiers.each do |notifier|

      messages = []

      if notifier.threshold_max.present?
        if value >= notifier.threshold_max
          messages.push('Exceeded maximum')
        end
      end

      if notifier.threshold_max.present?
        if value >= notifier.threshold_max
          messages.push('Below minimum.')
        end
      end

      notifier.notify(self, messages) if messages.length
    end
  end
end
