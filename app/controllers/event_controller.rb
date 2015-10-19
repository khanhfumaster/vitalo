class EventController < WebsocketRails::BaseController

  around_filter :time_request

  def time_request
    start = Time.now
    yield
    delta = Time.now - start
    puts "Action took #{delta.to_f} seconds"
  end

  def store_reading
    p message
    device = VitaloDevice.find_by_serial_number(message['serial_number'])

    if device
      reading = device.readings.new(sensor: message['sensor'], value: message['value'])
      if reading.save
        WebsocketRails["vitalo:#{device.serial_number}"].trigger('store_reading_success', reading.to_json)
      else
        WebsocketRails["vitalo:#{device.serial_number}"].trigger('store_reading_failed', 'Invalid reading params.')
      end
    else
      WebsocketRails["vitalo:#{device.serial_number}"].trigger('store_reading_failed', 'Invalid device serial number.')
    end
  end
end