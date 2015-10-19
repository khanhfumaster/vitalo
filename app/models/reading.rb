class Reading < ActiveRecord::Base

  enum sensor: [:spo2, :pulse, :movement]

  belongs_to :vitalo_device

  def check_notifiers
    if spo2?
      check_spo2_notifiers
    elsif pulse?
      check_pulse_notifiers
    elsif movment?
      check_movement_notifiers
    end
  end

  def check_spo2_notifiers
    vitalo_device.spo2_notifiers.each do |notifier|

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


    end
  end

  def check_pulse_notifiers
    vitalo_device.pulse_notifiers.each do |notifier|
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
    end
  end

  def check_movement_notifiers
    vitalo_device.movement_notifiers.each do |notifier|
      messages = []

      if notifier.threshold_max.present?
        if value >= notifier.threshold_max
          messages.push('Exceeded maximum')
        end
      end

      if notifier.threshold_min.present?
        if value >= notifier.threshold_min
          messages.push('Below minimum.')
        end
      end
    end
  end



end
