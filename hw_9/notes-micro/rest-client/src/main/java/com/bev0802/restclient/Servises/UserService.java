package com.bev0802.restclient.Servises;

import com.bev0802.restclient.Entities.User;
import com.bev0802.restclient.Reposetories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для управления пользователями.
 * Предоставляет методы для работы с пользователями, включая их поиск, сохранение и удаление.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Конструктор для создания сервиса UserService.
     *
     * @param userRepository Репозиторий для работы с пользователями.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Получает список всех пользователей.
     *
     * @return Список пользователей.
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Находит пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь, найденный по идентификатору.
     * @throws EntityNotFoundException если пользователь с указанным идентификатором не найден.
     */
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User не найден с id: " + id));
    }

    /**
     * Сохраняет пользователя в базе данных.
     *
     * @param user Пользователь для сохранения.
     * @return Сохраненный пользователь.
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Удаляет пользователя из базы данных.
     *
     * @param userId Идентификатор пользователя для удаления.     *
     */
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}



