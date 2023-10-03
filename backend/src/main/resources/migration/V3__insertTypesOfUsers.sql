/* username: admin, password: admin */
INSERT INTO users(username, password, email, enabled, first_name, last_name)
VALUES ('admin', '$2a$12$k7zfj2HfI9oGn2W27rBYw.PolfhuAJ3uKPA230VlPCHN1HI7aMQfe', 'test@yahoo.com', 'true', 'test', 'test');

INSERT INTO user_roles(user_id, role_id) VALUES (1, 3);

/* username: client, password: client */
INSERT INTO users(username, password, email, enabled, first_name, last_name)
VALUES ('client', '$2a$12$FzmZcBGxfqwPj0lFBFP8aeTk9J9zGJe9MfUHlmXG5Py05oyGGEgAi', 'test2@yahoo.com', 'true', 'test2', 'test2');

INSERT INTO user_roles(user_id, role_id) VALUES (2, 2);

/* username: receptionist, password: receptionist */
INSERT INTO users(username, password, email, enabled, first_name, last_name)
VALUES ('receptionist', '$2a$12$ELOpxyH.sQR7nIRD5U8F4OWdI7rMq5Ve2OWtODTR8mjftkSEC/KM.', 'test3@yahoo.com', 'true', 'test3', 'test3');

INSERT INTO user_roles(user_id, role_id) VALUES (3, 4);

/* username: kitchen, password: kitchen */
INSERT INTO users(username, password, email, enabled, first_name, last_name)
VALUES ('kitchen', '$2a$12$FrIIYJMEYg3ntaFnF3Yv9u33na6SmSrZWgFlN3qalyFizvNMRwUTK', 'test4@yahoo.com', 'true', 'test4', 'test4');

INSERT INTO user_roles(user_id, role_id) VALUES (4, 5);

/* username: cleaning, password: cleaning */
INSERT INTO users(username, password, email, enabled, first_name, last_name)
VALUES ('cleaning', '$2a$12$dqvfWQvTDf.STxQiXmMVRO3jUHDTepYAb4cR85hAZTdViPP6s9Efi', 'test5@yahoo.com', 'true', 'test5', 'test5');

INSERT INTO user_roles(user_id, role_id) VALUES (5, 6);

/* username: all, password: all */
INSERT INTO users(username, password, email, enabled, first_name, last_name)
VALUES ('all','$2a$12$6dV7ODECs.nffnBjQMLM/uptyTKJEOMpVwA4tpgiDyMPPk2cKPG7q','test6@yahoo.com', 'true', 'test6', 'test6');

INSERT INTO user_roles(user_id, role_id) VALUES (6, 2);
INSERT INTO user_roles(user_id, role_id) VALUES (6, 3);
INSERT INTO user_roles(user_id, role_id) VALUES (6, 4);
INSERT INTO user_roles(user_id, role_id) VALUES (6, 5);
INSERT INTO user_roles(user_id, role_id) VALUES (6, 6);