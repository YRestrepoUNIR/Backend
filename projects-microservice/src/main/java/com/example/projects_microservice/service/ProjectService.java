package com.example.projects_microservice.service;

import com.example.projects_microservice.entity.Project;
import com.example.projects_microservice.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            existingProject.setNombre(projectDetails.getNombre());
            existingProject.setDescripcion(projectDetails.getDescripcion());
            existingProject.setFechaInicio(projectDetails.getFechaInicio());
            existingProject.setFechaFin(projectDetails.getFechaFin());
            existingProject.setEstado(projectDetails.getEstado());
            return projectRepository.save(existingProject);
        } else {
            throw new RuntimeException("Proyecto no encontrado con id: " + id);
        }
    }

    public void deleteProject(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        optionalProject.ifPresent(projectRepository::delete);
    }
}
