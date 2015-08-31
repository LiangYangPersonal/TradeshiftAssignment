CREATE TABLE task(
  id INTEGER IDENTITY,
  create_date DATE NOT NULL,
  task_name varchar(200) NOT NULL,
  task_type varchar(200) NOT NULL,
  assigned_to INTEGER ,
  status varchar(200) NOT NULL
);


CREATE TABLE user(
  id INTEGER IDENTITY,
  user_name varchar(200) NOT NULL
);

