package org.example.freelynk.repository;

import org.example.freelynk.model.Client;
import org.example.freelynk.model.Project;
import org.example.freelynk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    // Filter projects by budget range (matching PDF filters)
@Query("SELECT p FROM Project p WHERE p.minBudget >= :min AND p.maxBudget <= :max")
List<Project> findByBudgetRange(@Param("min") Double min, @Param("max") Double max);

    // Filter by required skills (e.g., "Graphic Design")
    @Query("SELECT p FROM Project p JOIN p.requiredSkills s WHERE s IN :skills")
    List<Project> findByRequiredSkills(@Param("skills") List<String> skills);

@Query("SELECT p FROM Project p WHERE p.client.id = :userId")
List<Project> findByClientId(@Param("userId") UUID userId);

List<Project> findAll();

}