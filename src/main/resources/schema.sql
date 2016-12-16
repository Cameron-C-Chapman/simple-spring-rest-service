create table customer(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  address1 VARCHAR(255),
  address2 VARCHAR(255),
  city VARCHAR(255),
  state VARCHAR(255),
  zip VARCHAR(255),
  create_timestamp TIMESTAMP,
  update_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
);