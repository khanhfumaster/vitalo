class VisitorsController < ApplicationController
  def index
    redirect_to patients_path if current_user
    @no_nav = true
  end
end
