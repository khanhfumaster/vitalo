namespace :mock_vitalo do
  desc "Create demo data"
  task demo_data: :environment do
    current_time = 3.days.ago

    patient = User.find_by_email('khanhplications@gmail.com').patients.create(name: Faker::Name.name, gender: 0, date_of_birth: Faker::Date.between(10.years.ago, Date.today))

    vitalo_device = VitaloDevice.create(serial_number: Faker::Address.zip, patient_id: patient.id )

    (1..3.days).each do |time_increment|

      vitalo_device.readings.create(sensor: Reading.sensors[:spo2], value: rand(85..95), created_at: current_time)
      vitalo_device.readings.create(sensor: Reading.sensors[:pulse], value: rand(90..110), created_at: current_time)
      vitalo_device.readings.create(sensor: Reading.sensors[:movement], value: rand(0..1), created_at: current_time)

      current_time = current_time + 10.second

    end


  end

end