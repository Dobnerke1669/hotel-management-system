CREATE TABLE users(
                      id SERIAL PRIMARY KEY,
                      username VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      enabled BOOLEAN NOT NULL DEFAULT TRUE,
                      first_name VARCHAR(255) NOT NULL,
                      last_name VARCHAR(255) NOT NULL
);

CREATE TABLE roles(
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_roles(
                           user_id INTEGER NOT NULL,
                           role_id INTEGER NOT NULL,
                           PRIMARY KEY (user_id, role_id),
                           CONSTRAINT fk_user
                               FOREIGN KEY (user_id)
                                   REFERENCES users(id),
                           CONSTRAINT fk_role
                               FOREIGN KEY (role_id)
                                   REFERENCES roles(id)
);