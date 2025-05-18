package org.example.freelynk.repository;

import org.example.freelynk.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Filter projects by budget range (matching PDF filters)
    List<Project> findByBudgetBetween(Double minBudget, Double maxBudget);

    // Filter by required skills (e.g., "Graphic Design")
    @Query("SELECT p FROM Project p JOIN p.requiredSkills s WHERE s IN :skills")
    List<Project> findByRequiredSkills(@Param("skills") List<String> skills);
}