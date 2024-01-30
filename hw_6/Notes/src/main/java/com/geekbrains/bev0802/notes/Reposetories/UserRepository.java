package com.geekbrains.bev0802.notes.Reposetories;

import com.geekbrains.bev0802.notes.Entities.Note;
import com.geekbrains.bev0802.notes.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с пользователями.
 * Предоставляет доступ к базовым операциям CRUD для сущности User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Получает пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь с заданным идентификатором.
     */
    User getUserById(Long id);
}
