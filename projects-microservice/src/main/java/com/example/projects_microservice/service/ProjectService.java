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
            if(projectDetails.getNombre() != null){
                existingProject.setNombre(projectDetails.getNombre());
            }
            if(projectDetails.getDescripcion() != null){
                existingProject.setDescripcion(projectDetails.getDescripcion());
            }
            if(projectDetails.getFechaInicio() != null){
                existingProject.setFechaInicio(projectDetails.getFechaInicio());
            }
            if(projectDetails.getFechaFin() != null){
                existingProject.setFechaFin(projectDetails.getFechaFin());
            }
            if(projectDetails.getEstado() != null){
                existingProject.setEstado(projectDetails.getEstado());
            }
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
