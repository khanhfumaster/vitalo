class CreateVitaloDevices < ActiveRecord::Migration
  def change
    create_table :vitalo_devices do |t|
      t.integer :serial_number
      t.integer :status
      t.integer :patient_id
      t.datetime :last_synced

      t.timestamps null: false
    end
  end
end
