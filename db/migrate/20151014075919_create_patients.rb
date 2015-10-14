class CreatePatients < ActiveRecord::Migration
  def change
    create_table :patients do |t|
      t.string :name
      t.integer :user_id
      t.integer :gender
      t.text :note
      t.date :date_of_birth

      t.timestamps null: false
    end
  end
end
