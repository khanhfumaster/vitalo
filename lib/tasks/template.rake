namespace :template do
  desc "Rename the project"
  task rename: :environment do
    # Get the new name
    new_name = ENV["NEW_NAME"] || nil
    if new_name.nil?
      puts "\nYou must pass in a new name"
      exit
    end

    # Get the current name of the app
    current_name = IO.read('config/application.rb').match(/module (\w*)/)[1]

    puts "Renaming #{current_name} to #{new_name}"

    camel_files = [ 'config/application.rb',
                    'app/views/layouts/application.html.erb',
                    'README'
    ]

    files = [ 'config.ru',
              'Rakefile',
              'config/environment.rb',
              'config/routes.rb',
              'config/environments/development.rb',
              'config/environments/test.rb',
              'config/environments/production.rb',
              'config/initializers/secret_token.rb',
              'config/initializers/session_store.rb',
              'config/database.yml']

    camel_files.each do |file|
      if File.exist?(file)
        puts "  Updating #{file}"

        input = IO.read(file)
        current_n = current_name.camelcase
        new_n = new_name.camelcase

        output = File.new file, 'w'
        output << input.gsub(current_n, new_n)
        output.close
      end
    end

    files.each do |file|
      if File.exist?(file)
        puts "  Updating #{file}"

        input = IO.read(file)
        current_n = current_name.underscore
        new_n = new_name.underscore

        output = File.new file, 'w'
        output << input.gsub(current_n, new_n)
        output.close
      end
    end
  end

end
