json.array!(@patients) do |patient|
  json.extract! patient, :id, :name, :user_id, :gender, :note
  json.url patient_url(patient, format: :json)
end
