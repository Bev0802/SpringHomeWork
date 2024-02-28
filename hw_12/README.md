## Урок 12. Паттерны проектирония и GoF паттерны в Spring приложении

### Задание:

1. На базе первого примера разобранного на семинаре, добавить в один из проектов разработанных ранее spring Integration. Сохранять запросы от пользователя в файл.

### Выполнила:

- добавила [IntegrationConfig.java](Notes/src/main/java/com/geekbrains/bev0802/notes/Configuration/IntegrationConfig.java)
- добавила [FileGateway.java](Notes/src/main/java/com/geekbrains/bev0802/notes/servises/FileGateway.java)
- в [Controller/WebController.java](Notes/src/main/java/com/geekbrains/bev0802/notes/Controller/WebController.java) в методы _addUser_ и _addNote_ добавила реализацию записи в файл при создании.

2. Добавить в проект один из паттернов разобранных на лекции.

### Выполнила:

В моем проэкте есть паттерн "Репозиторий" (Repository Pattern), через интерфейсы NoteRepository и UserRepository. Этот паттерн помогает изолировать логику доступа к данным от бизнес-логики приложения, что упрощает тестирование и обслуживание кода.

На уровне архитектуры приложения, применен патерн "Слои" (Layered Architecture) — разделение на контроллеры (Controller), сервисы (Service), репозитории (Repository) и сущности (Entity).
