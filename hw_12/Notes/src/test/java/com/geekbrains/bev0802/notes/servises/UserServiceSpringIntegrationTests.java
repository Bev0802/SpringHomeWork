package com.geekbrains.bev0802.notes.servises;

import com.geekbrains.bev0802.notes.Entities.User;
import com.geekbrains.bev0802.notes.Reposetories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/*
*  @SpringBootTest используется в Spring Boot для указания, что класс является тестовым и должен запускаться
* с поддержкой Spring TestContext Framework. Эта аннотация обеспечивает полную загрузку Spring контекста,
* что позволяет проводить интеграционное тестирование, имитируя поведение приложения в реальных условиях работы.
* */
@SpringBootTest
public class UserServiceSpringIntegrationTests {

    @MockBean
    private UserRepository userRepository; // Мок репозитория пользователей

    @Autowired
    private UserService userService; // Тестируемый сервис пользователей

    private User user; // Тестовый пользователь

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L); // Инициализация тестового пользователя
        user.setName("Test User");
    }

    @Test
    public void testFindAllUsers() {
        // Настройка мока
        Mockito.when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        // Вызов тестируемого метода
        List<User> users = userService.findAllUsers();

        // Проверки
        verify(userRepository).findAll(); // Подтверждаем вызов метода findAll
        assertThat(users).isNotEmpty(); // Проверяем, что список пользователей не пуст
        assertThat(users).contains(user); // Проверяем, что список содержит нашего тестового пользователя
    }

    @Test
    public void testFindUserById() {
        // Настройка мока
        Mockito.when(userRepository.findById(eq(1L))).thenReturn(Optional.of(user));

        // Вызов тестируемого метода
        User foundUser = userService.findUserById(1L);

        // Проверки
        verify(userRepository).findById(eq(1L)); // Подтверждаем вызов метода findById с правильным ID
        assertThat(foundUser).isNotNull(); // Проверяем, что пользователь найден
        assertThat(foundUser.getId()).isEqualTo(user.getId()); // Проверяем, что ID найденного пользователя соответствует ожидаемому
    }

    @Test
    public void testSaveUser() {
        // Настройка мока
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        // Вызов тестируемого метода
        User savedUser = userService.saveUser(user);

        // Проверки
        verify(userRepository).save(any(User.class)); // Подтверждаем вызов метода save
        assertThat(savedUser).isNotNull(); // Проверяем, что пользователь был сохранён
        assertThat(savedUser.getId()).isEqualTo(user.getId()); // Проверяем, что ID сохранённого пользователя соответствует ожидаемому
    }

    @Test
    public void testDeleteUser() {
        // Вызов тестируемого метода
        userService.deleteUser(1L);

        // Проверка
        verify(userRepository).deleteById(eq(1L)); // Подтверждаем вызов метода deleteById с правильным ID
    }
}