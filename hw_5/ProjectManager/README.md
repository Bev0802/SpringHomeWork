# Текст сложного задания на тему Spring Data JPA:
------------------------------------------

## Задание

    Ваша команда разрабатывает приложение для управления проектами.
    Вам нужно реализовать функциональность связывания пользователей с проектами.

Создание сущности "Пользователь" (User):

        id: тип Long, автоматически генерируемый.
        username: тип String.
        password: тип String.
        email: тип String.
        role: тип String.

Создание сущности "Проект" (Project):

        id: тип Long, автоматически генерируемый.
        name: тип String.
        description: тип String.
        createdDate: тип LocalDate.

Создание абстрактного класса "Сущность с связью" (EntityWithRelation):

        id: тип Long, автоматически генерируемый.
        relatedEntityId: тип Long.

Создание сущности "Пользователи проекта" (UsersProject), наследующейся от EntityWithRelation:

        projectId: тип Long.
        userId: тип Long.


Создание интерфейсов репозиториев:

        UserRepository расширяет JpaRepository<User, Long>.
        ProjectRepository расширяет JpaRepository<Project, Long>.
        UsersProjectRepository расширяет JpaRepository<UsersProject, Long>.

Создание сервисного класса (UserProjectService):

        getUsersByProjectId(Long projectId): возвращает список пользователей проекта.
        getProjectsByUserId(Long userId): возвращает список проектов пользователя.
        addUserToProject(Long userId, Long projectId): добавляет пользователя к проекту.
        removeUserFromProject(Long userId, Long projectId): удаляет пользователя из проекта.

Создание контроллера (UserProjectController):

        getUsersByProjectId(Long projectId): обрабатывает GET-запрос для получения пользователей проекта.
        getProjectsByUserId(Long userId): обрабатывает GET-запрос для получения проектов пользователя.
        addUserToProject(Long userId, Long projectId): обрабатывает POST-запрос для добавления пользователя к проекту.
        removeUserFromProject(Long userId, Long projectId): обрабатывает POST-запрос для удаления пользователя из проекта.

