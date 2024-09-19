CREATE TABLE tasks
(
    task_id          BIGSERIAL PRIMARY KEY,
    task_name        TEXT      NOT NULL,
    task_description TEXT      NOT NULL,
    task_status      VARCHAR   NOT NULL,
    task_user_id     BIGSERIAL NOT NULL
);

CREATE TABLE users
(
    user_id       BIGSERIAL PRIMARY KEY,
    user_name     VARCHAR(255) NOT NULL,
    user_password VARCHAR(60) NOT NULL
);