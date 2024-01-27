package com.geekbrains.bev0802.projectmanager.repositories;

import com.geekbrains.bev0802.projectmanager.etities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> getProjectsByUserId(Long userId);
}