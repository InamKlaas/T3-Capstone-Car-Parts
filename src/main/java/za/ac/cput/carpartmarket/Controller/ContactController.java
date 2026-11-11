package za.ac.cput.carpartmarket.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.ContactMessage;
import za.ac.cput.carpartmarket.Repository.ContactMessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    public record ContactRequest(String fullName, String email, String subject, String message) {}

    private final ContactMessageRepository repository;

    public ContactController(ContactMessageRepository repository) {
        this.repository = repository;
    }

    // Public: anyone can send a message
    @PostMapping
    public ResponseEntity<?> send(@RequestBody ContactRequest req) {
        if (blank(req.fullName()) || blank(req.email()) || blank(req.subject()) || blank(req.message())
                || !req.email().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
                || req.message().length() > 4000) {
            return ResponseEntity.badRequest().body("Please fill in every field with a valid email.");
        }
        repository.save(new ContactMessage(UUID.randomUUID().toString(), req.fullName().trim(),
                req.email().trim(), req.subject().trim(), req.message().trim(), LocalDateTime.now()));
        return ResponseEntity.ok().build();
    }

    // Admin only (enforced in SecurityConfig)
    @GetMapping
    public List<ContactMessage> getAll() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<ContactMessage> resolve(@PathVariable String id) {
        return repository.findById(id).map(m -> {
            m.setResolved(true);
            return ResponseEntity.ok(repository.save(m));
        }).orElse(ResponseEntity.notFound().build());
    }

    private boolean blank(String s) { return s == null || s.isBlank(); }
}
