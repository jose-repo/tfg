CREATE TABLE IF NOT EXISTS UserEntity (
    id varchar(255) PRIMARY KEY,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    token varchar(255),
    createdAt TIMESTAMP
    );

INSERT INTO UserEntity (id, username, password, name, token, createdAt)
VALUES (RANDOM_UUID(), 'jose', 'password', 'José María', RANDOM_UUID(), CURRENT_TIMESTAMP());