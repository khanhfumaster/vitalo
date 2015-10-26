require "serialport"
require "patron"

port_str = "/dev/cu.usbmodem1411"
baud_rate = 9600
data_bits = 8
stop_bits = 1
parity = SerialPort::NONE

sp = SerialPort.new(port_str, baud_rate, data_bits, stop_bits, parity)

sess = Patron::Session.new
sess.timeout = 10
sess.base_url = "http://vitalo.remotelab.club"

#just read forever
while true do
   while (i = sp.gets.chomp) do
      data = {"serial_number":"34596","value": i,"sensor":"spo2"}
      r = sess.post("/readings", data, {"Content-Type" => "application/json"})
      puts r.body
    end
end
