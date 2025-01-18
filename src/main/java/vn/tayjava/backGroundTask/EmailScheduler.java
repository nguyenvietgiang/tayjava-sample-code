package vn.tayjava.backGroundTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.tayjava.service.EmailService;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;
   @Scheduled(fixedRate = 14400000) // 4 giờ (đơn vị: milliseconds)
    public void sendSystemStatusEmail() {
        try {
            String to = "vietgiang111001@gmail.com";
            String subject = "System Status Update";
            String templateName = "EmailNotification.html";
            String firstName = "Manager";

            emailService.sendEmail(to, subject, templateName, firstName);

            System.out.println("System status email sent successfully.");
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
