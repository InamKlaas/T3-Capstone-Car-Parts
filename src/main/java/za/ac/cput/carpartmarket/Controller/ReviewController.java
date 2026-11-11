package za.ac.cput.carpartmarket.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Review;
import za.ac.cput.carpartmarket.Repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    public record ReviewRequest(String carPartId, String reviewerName, int rating, String title, String comment) {}

    private final ReviewRepository repository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Review> getAll() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/part/{carPartId}")
    public List<Review> forPart(@PathVariable String carPartId) {
        return repository.findByCarPartIdOrderByCreatedAtDesc(carPartId);
    }

    // Logged-in buyers/sellers only; one review per user per part. Reviewer identity comes from the JWT.
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReviewRequest req, Authentication auth) {
        if (req.carPartId() == null || req.carPartId().isBlank()
                || req.rating() < 1 || req.rating() > 5
                || req.comment() == null || req.comment().isBlank()
                || req.comment().length() > 2000) {
            return ResponseEntity.badRequest().body("Pick a rating from 1 to 5 and write a comment.");
        }
        String email = auth.getName();
        if (repository.existsByCarPartIdAndReviewerEmail(req.carPartId(), email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("You already reviewed this part.");
        }
        String role = auth.getAuthorities().stream().findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", "").toLowerCase()).orElse("buyer");
        String name = (req.reviewerName() == null || req.reviewerName().isBlank()) ? "Anonymous" : req.reviewerName().trim();

        Review review = new Review(UUID.randomUUID().toString(), req.carPartId(), email, name, role,
                req.rating(), req.title() == null ? "" : req.title().trim(), req.comment().trim(), LocalDateTime.now());
        return ResponseEntity.ok(repository.save(review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, Authentication auth) {
        var review = repository.findById(id);
        if (review.isEmpty()) return ResponseEntity.notFound().build();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin && !review.get().getReviewerEmail().equals(auth.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
