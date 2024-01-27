package com.geekbrains.bev0802.projectmanager.etities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "users_projects")
public class UsersProject extends EntityWithRelation {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "project_id")
    private Long projectId;
}

