package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByCarPartIdOrderByCreatedAtDesc(String carPartId);
    List<Review> findAllByOrderByCreatedAtDesc();
    boolean existsByCarPartIdAndReviewerEmail(String carPartId, String reviewerEmail);
}
