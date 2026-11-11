package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    private String reviewId;
    private String carPartId;
    private String reviewerEmail;
    private String reviewerName;
    private String reviewerRole;
    private int rating;
    private String title;
    @Column(length = 2000)
    private String comment;
    private LocalDateTime createdAt;

    protected Review() {}

    public Review(String reviewId, String carPartId, String reviewerEmail, String reviewerName,
                  String reviewerRole, int rating, String title, String comment, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.carPartId = carPartId;
        this.reviewerEmail = reviewerEmail;
        this.reviewerName = reviewerName;
        this.reviewerRole = reviewerRole;
        this.rating = rating;
        this.title = title;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public String getReviewId() { return reviewId; }
    public String getCarPartId() { return carPartId; }
    public String getReviewerEmail() { return reviewerEmail; }
    public String getReviewerName() { return reviewerName; }
    public String getReviewerRole() { return reviewerRole; }
    public int getRating() { return rating; }
    public String getTitle() { return title; }
    public String getComment() { return comment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
