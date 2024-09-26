CREATE TABLE Role (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL
);

-- Insert default roles
INSERT INTO Role (name) VALUES ('ROLE_USER');
INSERT INTO Role (name) VALUES ('ROLE_ADMIN');