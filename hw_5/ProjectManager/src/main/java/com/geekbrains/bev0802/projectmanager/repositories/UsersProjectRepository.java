package com.geekbrains.bev0802.projectmanager.repositories;

import com.geekbrains.bev0802.projectmanager.etities.UsersProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UsersProjectRepository extends JpaRepository<UsersProject, Long> {
    List<UsersProject> findAllByProjectId(Long projectId);
    List<UsersProject> findAllByUserId(Long userId);
    UsersProject findByUserIdAndProjectId(Long userId, Long projectId);
}
