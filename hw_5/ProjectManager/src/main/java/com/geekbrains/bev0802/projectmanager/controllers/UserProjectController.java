package com.geekbrains.bev0802.projectmanager.controllers;

import com.geekbrains.bev0802.projectmanager.etities.Project;
import com.geekbrains.bev0802.projectmanager.etities.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.geekbrains.bev0802.projectmanager.services.UserProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user-project")
public class UserProjectController {
    @Autowired
    private UserProjectService userProjectService;

    @GetMapping("/{projectId}")
    public ResponseEntity<List<User>> getUsersByProjectId(@PathVariable Long projectId) {
        List<User> users = userProjectService.getUsersByProjectId(projectId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId) {
        List<Project> projects = userProjectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/add/{userId}/{projectId}")
    public ResponseEntity<Void> addUserToProject(@RequestParam Long userId, @RequestParam Long projectId) {
        userProjectService.addUserToProject(userId, projectId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove/{userId}/{projectId}")
    public ResponseEntity<Void> removeUserFromProject(@RequestParam Long userId, @RequestParam Long projectId) {
        userProjectService.removeUserFromProject(userId, projectId);
        return ResponseEntity.ok().build();
    }
}