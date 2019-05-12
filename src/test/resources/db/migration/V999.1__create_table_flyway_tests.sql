CREATE TABLE flyway_tests(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL unique ,
  description VARCHAR(100) NOT NULL
);