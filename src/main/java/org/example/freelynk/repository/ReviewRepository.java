package org.example.freelynk.repository;

import org.example.freelynk.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    // Find all reviews for a freelancer (for profile page)
    List<Review> findByFreelancerId(UUID freelancerId);

    // Calculate average rating (for freelancer profile)
    @Query("SELECT AVG(r.level) FROM Review r WHERE r.freelancer.id = :freelancerId")
    Double calculateAverageRating(@Param("freelancerId") UUID freelancerId);
}