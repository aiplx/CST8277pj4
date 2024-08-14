-- Set the default database to CST8277
USE CST8277;

-- Inserting roles
INSERT INTO roles (role_name) VALUES ('Producer');
INSERT INTO roles (role_name) VALUES ('Subscriber');

-- Inserting users
INSERT INTO users (user_name, user_pwd) VALUES ('Aiden', 'password');
INSERT INTO users (user_name, user_pwd) VALUES ('Biden', 'password');
INSERT INTO users (user_name, user_pwd) VALUES ('Ciden', 'password');

-- Inserting role assignments. Roles IDs for Producer and Subscriber are 1 and 2 respectively
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- Aiden as Producer
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- Biden as Subscriber
INSERT INTO user_roles (user_id, role_id) VALUES (3, 1); -- Ciden as Producer
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2); -- Ciden as Subscriber

-- Inserting messages by Aiden
INSERT INTO messages (content, user_id, timestamp) VALUES ('Hello from Aiden!', 1, '2023-05-20');
INSERT INTO messages (content, user_id, timestamp) VALUES ('Welcome to my blog.', 1, '2023-05-21');
INSERT INTO messages (content, user_id, timestamp) VALUES ('New updates are coming soon.', 1, '2023-05-22');

-- Inserting messages by Ciden
INSERT INTO messages (content, user_id, timestamp) VALUES ('Hello from Ciden!', 3, '2023-03-03');
INSERT INTO messages (content, user_id, timestamp) VALUES ('Thank you for joining us.', 3, '2023-04-03');

-- Inserting subscriptions where Biden subscribe to Ciden & Aiden and Ciden subscribe to Aiden
INSERT INTO subscriptions (subscriber_id, producer_id) VALUES (2, 3);
INSERT INTO subscriptions (subscriber_id, producer_id) VALUES (2, 1);
INSERT INTO subscriptions (subscriber_id, producer_id) VALUES (3, 1);


