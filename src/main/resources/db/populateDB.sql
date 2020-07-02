DELETE FROM client;
DELETE FROM city;
DELETE FROM country;
DELETE FROM user;
DELETE FROM user_request_log;

ALTER SEQUENCE client_seq RESTART WITH 100000;
ALTER SEQUENCE city_seq RESTART WITH 100000;
ALTER SEQUENCE country_seq RESTART WITH 100000;
ALTER SEQUENCE user_seq RESTART WITH 100000;
ALTER SEQUENCE user_req_seq RESTART WITH 100000;

INSERT INTO user (name) VALUES ('User1');
INSERT INTO user (name) VALUES ('User2');

INSERT INTO country (code, name, capital) VALUES ('KZ','Kazakhstan','Astana');
INSERT INTO country (code, name, capital) VALUES ('US','USA','Washington');
INSERT INTO country (code, name, capital) VALUES ('RU','Russian Federation','Moscow');

INSERT INTO city (code, name, country_id) VALUES ('AST','Astana',100000);
INSERT INTO city (code, name, country_id) VALUES ('WGN', 'Washington', 100001);
INSERT INTO city (code, name, country_id) VALUES ('MSC', 'Moscow', 100002);

INSERT INTO client (name, iin, birthdate, phone_number, city_id)
    VALUES ('Client_KZ', '1975-05-30','750530789012', '+77172123456', 100000);
INSERT INTO client (name, iin, birthdate, phone_number, city_id)
    VALUES ('Client_USA', '1983-11-04','831104382957', '+02068570293', 100001);
INSERT INTO client (name, iin, birthdate, phone_number, city_id)
    VALUES ('Client_RU', '1986-07-18','860718092331', '+74958347275', 100002);

