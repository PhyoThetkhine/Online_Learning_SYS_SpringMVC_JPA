package system.service.impl;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import system.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	 private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	    @Autowired
	    private JavaMailSender mailSender;
	@Override
	 public void sendPassword(String toEmail, String password) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("Your Password");
            String htmlContent = "<html><body>"
                    + "<p>Dear User,</p>"
                    + "<p>Here is your password. Please keep it safe and do not share it with anyone.</p>"
                    + "<div style='border:1px solid #cccccc; padding:10px; width: fit-content; "
                    + "background-color: #f9f9f9; font-size: 16px; font-family: Arial, sans-serif;'>"
                    + "<strong>" + password + "</strong>"
                    + "</div>"
                    + "<p>Regards,<br>YourApp Team</p>"
                    + "</body></html>";

            helper.setText(htmlContent, true);  // 'true' indicates HTML content
            // Send the message
            mailSender.send(mimeMessage);

            logger.info("Sent password email to {}", toEmail);
        } catch (Exception e) {
            logger.error("Error sending password email to {}: {}", toEmail, e.getMessage());
        }
    }

}
