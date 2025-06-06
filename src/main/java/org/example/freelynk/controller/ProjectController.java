package org.example.freelynk.controller;

import java.util.List;

import org.example.freelynk.dto.AddProjectRequest;
import org.example.freelynk.model.Client;
import org.example.freelynk.model.Project;
import org.example.freelynk.model.User;
import org.example.freelynk.security.SecurityUtil;
import org.example.freelynk.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProject(@RequestBody AddProjectRequest request) {
        User currentClient =  SecurityUtil.getCurrentUser(); 
        Project project = projectService.addProject(request);
        return ResponseEntity.ok(project);
    }
    @GetMapping("/myProjects")
    public ResponseEntity<?> getMyProjects() {
    User currentClient = SecurityUtil.getCurrentUser();
    List<Project> projects = projectService.getProjectsByClient((Client) currentClient);
    return ResponseEntity.ok(projects);
}


}
