INSERT INTO user (id, user_name, password, active) VALUES (1, 'tom', '$2a$10$2OylqmFRWSIEneZ4JgpLxOqKqStAHpPilTXEnWBWYSX5FcEaQABQ2', 1);
INSERT INTO user (id, user_name, password, active) VALUES (2, 'henk', '$2a$10$2OylqmFRWSIEneZ4JgpLxOqKqStAHpPilTXEnWBWYSX5FcEaQABQ2', 1);

INSERT INTO role (id, role_name) VALUES (1, 'ROLE_USER');
INSERT INTO role (id, role_name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id)  VALUES (1, 1);
INSERT INTO user_role (user_id, role_id)  VALUES (2, 1);
INSERT INTO user_role (user_id, role_id)  VALUES (2, 2);

INSERT INTO song (id, location, artist, title, user_id) VALUES (1, 'C:\\Users\\tomde\\bensound-creativeminds.mp3', 'tom', 'liedje', 1);
# INSERT INTO song (id, artist, location, name, user_id) VALUES (1, "tom", "C:\\Users\\tomde\\bensound-creativeminds.mp3", "liedje", 1);