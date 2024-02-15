package com.example.Task.services;

import com.example.Task.aop.TrackUserAction;
import com.example.Task.domain.User;
import com.example.Task.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

   //@Autowired
   private NotificationService notificationService;


    // Внедрение зависимости через конструктор
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @TrackUserAction
    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        // Отправляем уведомление о создании нового пользователя
        notificationService.notifyUser(user);
        return user;
    }
    @TrackUserAction
    public void deleteUser(Long userId) {

    }
}
