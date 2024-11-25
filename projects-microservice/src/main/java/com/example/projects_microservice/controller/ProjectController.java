package com.example.projects_microservice.controller;

import com.example.projects_microservice.entity.Project;
import com.example.projects_microservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Obtener todos los proyectos
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Obtener un proyecto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo proyecto
    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        try {
            Project newProject = projectService.createProject(project);
            return ResponseEntity.ok(newProject);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear un proyecto: " + e.getMessage());
        }
    }

    // Actualizar un proyecto existente
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        try {
            Project updatedProject = projectService.updateProject(id, projectDetails);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un proyecto (lógica de eliminación)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.status(HttpStatus.OK).body("Proyecto eliminado" );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar el estado de un proyecto
    @PatchMapping("/{id}/state")
    public ResponseEntity<Project> updateProjectStatus(@PathVariable Long id, @RequestBody String estado) {
        try {
            Project project = projectService.getProjectById(id).orElseThrow();
            project.setEstado(estado);
            Project updatedProject = projectService.updateProject(id, project);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
