package vn.tayjava.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;  // Thay đổi từ javax sang jakarta
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String templateName, String firstName) throws Exception {
        // Load the HTML template
        Path path = Paths.get(new ClassPathResource("template/" + templateName).getURI());
        String content = new String(Files.readAllBytes(path));

        // Replace placeholder with dynamic data
        content = content.replace("${firstName}", firstName);

        // Create email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        // Send email
        mailSender.send(message);
    }
}