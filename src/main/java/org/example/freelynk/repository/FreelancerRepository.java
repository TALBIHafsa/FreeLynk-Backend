package org.example.freelynk.repository;

import org.example.freelynk.model.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    // Custom query: Find freelancers by skill (from PDF categories)
    @Query("SELECT f FROM Freelancer f JOIN f.skills s WHERE s IN :skills")
    List<Freelancer> findBySkills(@Param("skills") List<String> skills);

    // Find top-rated freelancers (for recommendations)
    @Query("SELECT f FROM Freelancer f JOIN f.reviews r GROUP BY f HAVING AVG(r.rating) >= :minRating")
    List<Freelancer> findTopRated(@Param("minRating") double minRating);
}