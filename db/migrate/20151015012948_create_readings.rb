class CreateReadings < ActiveRecord::Migration
  def change
    create_table :readings do |t|
      t.float :value
      t.integer :sensor
      t.integer :vitalo_device_id

      t.timestamps null: false
    end
  end
end
