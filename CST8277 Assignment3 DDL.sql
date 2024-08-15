-- Create a new schema named CST8277
CREATE SCHEMA CST8277;
-- Set the default database to CST8277
USE CST8277;

-- Drop and recreate tables with consistent data types
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS subscriptions;
DROP TABLE IF EXISTS tokens;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

-- Create Users table
CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_name VARCHAR(50) NOT NULL,
                       user_pwd VARCHAR(50) NOT NULL,
                       github_id BIGINT UNIQUE
);

-- Create Roles table
CREATE TABLE roles (
                       role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       role_name VARCHAR(50) NOT NULL
);

-- Create UserRoles table to handle many-to-many relationship between Users and Roles
CREATE TABLE user_roles (
                            user_role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES users(user_id),
                            FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

-- Create Messages table
CREATE TABLE messages (
                          message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          content VARCHAR(400) NOT NULL,
                          user_id BIGINT NOT NULL,
                          timestamp DATE NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Create Subscriptions table to handle many-to-many relationship between subscribers and producers
CREATE TABLE subscriptions (
                               subscription_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               subscriber_id BIGINT NOT NULL,
                               producer_id BIGINT NOT NULL,
                               FOREIGN KEY (subscriber_id) REFERENCES users(user_id),
                               FOREIGN KEY (producer_id) REFERENCES users(user_id)
);

-- Create Tokens table
CREATE TABLE tokens (
                        token VARCHAR(36) PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(user_id)
);
