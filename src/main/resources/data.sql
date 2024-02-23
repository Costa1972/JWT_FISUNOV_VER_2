CREATE TABLE IF NOT EXISTS users (
    id         bigint auto_increment,
    username   varchar(50) not null,
    password   varchar(255) not null,
    email      varchar(80) unique,
    primary key (id)
);
CREATE TABLE IF NOT EXISTS roles (
    id         bigint auto_increment,
    name       varchar(50) not null,
    primary key (id)
);
CREATE TABLE IF NOT EXISTS users_roles (
    user_id    bigint not null,
    role_id    bigint not null,
    primary key (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
INSERT INTO roles (name) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');
INSERT INTO users(username, password, email) VALUES
    ('admin', '$2a$16$UlhkEbBehZdNYr5EkZnz6e42F0E9dvKX0QB5xdEQ7rBUao.7o3QQu', 'admin@mail.ru'),
    ('user', '$2a$16$PYNeLUS6kJHBCV7StNTzD.vH0lTZrJi6nUirYxCecmcf/gT4IYRfm', 'user@mail.ru');
INSERT INTO users_roles(user_id, role_id) VALUES
    (1, 1), (2, 2);
