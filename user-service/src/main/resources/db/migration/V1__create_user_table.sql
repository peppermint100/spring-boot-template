CREATE TABLE users (
    id BINARY(16) not null,
    email VARCHAR(255) not null,
    password VARCHAR(60) not null,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE INDEX(email)
);