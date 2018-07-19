DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id) VALUES
  (TIMESTAMP '2018-10-18 10:23:54', 'Завтрак', 500, 100000),
  (TIMESTAMP '2018-10-18 15:23:54', 'Обед', 400, 100001),
  (TIMESTAMP '2018-10-18 18:23:54', 'Ужин', 600, 100000),
  (TIMESTAMP '2018-10-18 10:23:54', 'Завтрак', 600, 100000),
  (TIMESTAMP '2018-10-19 12:23:54', 'Завтрак', 700, 100001),
  (TIMESTAMP '2018-10-19 15:23:54', 'Обед', 300, 100001),
  (TIMESTAMP '2018-10-19 18:23:54', 'Ужин', 800, 100001),
  (TIMESTAMP '2018-10-19 10:23:54', 'Завтрак', 200, 100000);