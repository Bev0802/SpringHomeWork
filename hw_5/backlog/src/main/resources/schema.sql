CREATE TABLE tasks (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      description VARCHAR(255) NOT NULL,
                      status VARCHAR(20) NOT NULL,
                      creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO tasks (description, status) VALUES ('Купить продукты', 'NOT_STARTED');
INSERT INTO tasks (description, status) VALUES ('Позвонить другу', 'IN_PROGRESS');
INSERT INTO tasks (description, status) VALUES ('Подготовить отчет', 'COMPLETED');
INSERT INTO tasks (description, status) VALUES ('Сходить в магазин', 'NOT_STARTED');
INSERT INTO tasks (description, status) VALUES ('Помыть посуду', 'IN_PROGRESS');