class CreateNotifiers < ActiveRecord::Migration
  def change
    create_table :notifiers do |t|
      t.integer :vitalo_device_id
      t.integer :sensor
      t.boolean :enabled, default: true
      t.boolean :send_email
      t.boolean :send_app_notification
      t.boolean :send_sms
      t.float :threshold_min
      t.float :threshold_max
      t.string :name
      t.text :note

      t.timestamps null: false
    end
  end
end
