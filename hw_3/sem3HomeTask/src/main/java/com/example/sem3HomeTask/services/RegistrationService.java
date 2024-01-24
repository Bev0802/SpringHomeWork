package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final DataProcessingService dataProcessingService;
    private final NotificationService notificationService;
    private final UserService userService;

    /**
     * Конструктор с параметрами
     *
     * @param dataProcessingService - сервис для обработки данных
     * @param notificationService - сервис для отправки уведомлений
     * @param userService - сервис для работы с пользователями
     */
    public RegistrationService(DataProcessingService dataProcessingService,
                               NotificationService notificationService,
                               UserService userService) {
        this.dataProcessingService = dataProcessingService;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    /**
     * @return - сервис для обработки данных
     */
    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }
    /**
     * Обрабатывает регистрацию пользователя.
     *
     * @param  name  имя пользователя
     * @param  age   возраст пользователя
     * @param  email email пользователя
     */
    public void processRegistration (String name, int age, String email) {
        User user = userService.createUser(name, age, email);
        dataProcessingService.addUserToList(user);
        notificationService.sendNotification("Пользователь сохранен в базу данных");

    }
}
