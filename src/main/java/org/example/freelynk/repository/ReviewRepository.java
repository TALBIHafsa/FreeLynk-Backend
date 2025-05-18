package org.example.freelynk.repository;

import org.example.freelynk.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Find all reviews for a freelancer (for profile page)
    List<Review> findByFreelancerId(Long freelancerId);

    // Calculate average rating (for freelancer profile)
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.freelancer.id = :freelancerId")
    Double calculateAverageRating(@Param("freelancerId") Long freelancerId);
}