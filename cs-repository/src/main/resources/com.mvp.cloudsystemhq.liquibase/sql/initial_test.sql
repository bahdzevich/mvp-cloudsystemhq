INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO customer (id, email, password) VALUES (1, 'artur@mail.ru', '{bcrypt}$2a$10$SU.bQp7SWnhJ2uVYfqKqsOZ/yABefiK0V4Ff8CbF2pgPtZhrd0ny6');
INSERT INTO customer_role (role_id, customer_id) VALUES (1, 1);
