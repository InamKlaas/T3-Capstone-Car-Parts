package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ContactMessage {
    @Id
    private String messageId;
    private String fullName;
    private String email;
    private String subject;
    @Column(length = 4000)
    private String message;
    private boolean resolved;
    private LocalDateTime createdAt;

    protected ContactMessage() {}

    public ContactMessage(String messageId, String fullName, String email, String subject,
                          String message, LocalDateTime createdAt) {
        this.messageId = messageId;
        this.fullName = fullName;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getMessageId() { return messageId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getSubject() { return subject; }
    public String getMessage() { return message; }
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
