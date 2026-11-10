package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetCode(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("AutoMarket <" + from + ">");
        message.setTo(to);
        message.setSubject("Your AutoMarket password reset code");
        message.setText(
                "Hi,\n\n" +
                        "Your AutoMarket password reset code is: " + code + "\n\n" +
                        "It expires in 10 minutes and can only be used once.\n" +
                        "If you didn't ask to reset your password, you can ignore this email.\n\n" +
                        "AutoMarket"
        );
        mailSender.send(message);
    }
}