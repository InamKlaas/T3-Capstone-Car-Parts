package za.ac.cput.carpartmarket.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordResetService {

    private static final Logger log = LoggerFactory.getLogger(PasswordResetService.class);
    private static final Duration EXPIRY = Duration.ofMinutes(10);
    private static final int MAX_ATTEMPTS = 5;

    private final EmailService emailService;
    private final SecureRandom random = new SecureRandom();
    private final Map<String, ResetCode> codes = new ConcurrentHashMap<>();

    private record ResetCode(String code, Instant expiresAt, int attempts) {}

    public PasswordResetService(EmailService emailService) {
        this.emailService = emailService;
    }

    // Creates a new code for this email (replacing any older one) and emails it
    public void issueCode(String email) {
        String code = String.format("%06d", random.nextInt(1_000_000));
        codes.put(email, new ResetCode(code, Instant.now().plus(EXPIRY), 0));

        try {
            emailService.sendPasswordResetCode(email, code);
            log.info("Password reset code emailed to {}", email);
        } catch (Exception e) {
            // Safety net so a network/email problem never blocks a reset
            log.warn("Could not email reset code to {} ({}). Code: {}", email, e.getMessage(), code);
        }
    }

    // True only for the correct, unexpired code. A used code can't be reused.
    public boolean verifyAndConsume(String email, String code) {
        ResetCode entry = codes.get(email);
        if (entry == null || code == null) return false;

        if (Instant.now().isAfter(entry.expiresAt())) {
            codes.remove(email);
            return false;
        }

        if (!entry.code().equals(code.trim())) {
            int attempts = entry.attempts() + 1;
            if (attempts >= MAX_ATTEMPTS) {
                codes.remove(email);
            } else {
                codes.put(email, new ResetCode(entry.code(), entry.expiresAt(), attempts));
            }
            return false;
        }

        codes.remove(email);
        return true;
    }
}