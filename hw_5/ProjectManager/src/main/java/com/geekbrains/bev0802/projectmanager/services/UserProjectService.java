package com.geekbrains.bev0802.projectmanager.services;

import com.geekbrains.bev0802.projectmanager.etities.Project;
import com.geekbrains.bev0802.projectmanager.etities.User;
import com.geekbrains.bev0802.projectmanager.etities.UsersProject;
import com.geekbrains.bev0802.projectmanager.repositories.ProjectRepository;
import com.geekbrains.bev0802.projectmanager.repositories.UserRepository;
import com.geekbrains.bev0802.projectmanager.repositories.UsersProjectRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class UserProjectService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UsersProjectRepository usersProjectRepository;

    public List<User> getUsersByProjectId(Long projectId) {
        return usersProjectRepository.findAllByProjectId(projectId)
                .stream()
                .map(usersProject -> userRepository.findById(usersProject.getUserId()).orElse(null))
                .collect(Collectors.toList());
    }

    public List<Project> getProjectsByUserId(Long userId) {
        return usersProjectRepository.findAllByUserId(userId)
                .stream()
                .map(usersProject -> projectRepository.findById(usersProject.getProjectId()).orElse(null))
                .collect(Collectors.toList());
    }

    public void addUserToProject(Long userId, Long projectId) {
        UsersProject usersProject = new UsersProject();
        usersProject.setUserId(userId);
        usersProject.setProjectId(projectId);
        usersProjectRepository.save(usersProject);
    }

    public void removeUserFromProject(Long userId, Long projectId) {
        UsersProject usersProject = usersProjectRepository.findByUserIdAndProjectId(userId, projectId);
        if (usersProject != null) {
            usersProjectRepository.delete(usersProject);
        }
    }

}
