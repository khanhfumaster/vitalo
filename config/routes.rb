Rails.application.routes.draw do
  resources :patients

  root to: 'visitors#index'
  devise_for :users, :controllers => { :omniauth_callbacks => "users/omniauth_callbacks" }
  devise_scope :user do
  	get 'sign_out', :to => 'devise/sessions#destroy', :as => :destroy_user_session
	end
  resources :users

  post 'vitalo_devices', :to => 'vitalo_devices#create', :as => :vitalo_devices

  resources :vitalo_devices, only: [:show, :create, :update] do
    resources :notifiers
  end
end
