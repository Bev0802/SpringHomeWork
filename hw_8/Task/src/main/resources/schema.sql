CREATE TABLE users (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      age INTEGER,
                      email VARCHAR(255)
);

INSERT INTO users (name, age, email) VALUES ('Tom', '20', '9wI8j@example.com');
INSERT INTO users (name, age, email) VALUES ('Jerry', '5', '9wI8j@example.com');
INSERT INTO users (name, age, email) VALUES ('Alice', '16', '9wI8j@example.com');
INSERT INTO users (name, age, email) VALUES ('Bob', '50', '9wI8j@example.com');
