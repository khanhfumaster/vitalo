class CreateNotifications < ActiveRecord::Migration
  def change
    create_table :notifications do |t|
      t.integer :patient_id
      t.integer :user_id
      t.text :message
      t.integer :notifier_id
      t.integer :reading_id

      t.timestamps null: false
    end
  end
end
