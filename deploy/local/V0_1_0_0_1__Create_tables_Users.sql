DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE test_users (
  id serial PRIMARY KEY,
  name varchar DEFAULT NULL
);
