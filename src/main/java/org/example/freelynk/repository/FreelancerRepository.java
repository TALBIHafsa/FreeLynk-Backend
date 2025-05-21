package org.example.freelynk.repository;

import org.example.freelynk.model.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, UUID> {
    @Query("SELECT f FROM Freelancer f JOIN f.skills s WHERE s IN :skills")
    List<Freelancer> findBySkills(@Param("skills") List<String> skills);

    @Query("SELECT f FROM Freelancer f JOIN f.reviews r GROUP BY f HAVING AVG(r.level) >= :minRating")
    List<Freelancer> findTopRated(@Param("minRating") double minRating);
}