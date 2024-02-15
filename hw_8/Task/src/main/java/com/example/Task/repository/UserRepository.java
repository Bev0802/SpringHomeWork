package com.example.Task.repository;

import com.example.Task.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
   * Интерфейс UserRepository предоставляет методы для взаимодействия с базой данных пользователей.
   * Наследуется от JpaRepository для обеспечения CRUD-операций.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}