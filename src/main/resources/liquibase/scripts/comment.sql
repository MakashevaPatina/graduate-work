-- liquibase formatted sql

-- changeset nast:1
CREATE TABLE comments (
    pk BIGINT PRIMARY KEY NOT NULL,
    author INTEGER NOT NULL,
    author_image TEXT,
    author_first_name TEXT,
    created_at BIGINT NOT NULL,
    text TEXT NOT NULL,
    ad_id INTEGER NOT NULL
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone VARCHAR(50),
    role VARCHAR(50) NOT NULL
);


CREATE TABLE user_avatar (
    id BIGSERIAL PRIMARY KEY,
    file_path VARCHAR(500) NOT NULL,
    file_size BIGINT NOT NULL,
    media_type VARCHAR(255) NOT NULL,
    data BYTEA,
    user_id BIGINT UNIQUE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE advertisement (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    image VARCHAR(500),
    author_id BIGINT NOT NULL,
    CONSTRAINT fk_advertisement_user FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);
