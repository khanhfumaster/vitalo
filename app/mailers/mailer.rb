class Mailer < ActionMailer::Base
  default from: 'vitalo@remotelab.club'

  def notify(notification)
    @notification = notification
    mail(subject: "Vitalo: #{notification.patient.name} triggered #{notification.notifier.name}", to: notification.user.email)
  end
end