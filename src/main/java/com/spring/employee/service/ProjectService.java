package com.spring.employee.service;

import com.spring.employee.entity.Project;
import com.spring.employee.entity.ProjectTask;
import com.spring.employee.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Transactional
    public Project createProject(Project project) {
        for(ProjectTask task:project.getTasks()){
            task.setProject(project);
        }
        return projectRepository.save(project);
    }


}
