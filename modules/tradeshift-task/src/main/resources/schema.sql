DROP table task if exists ;
DROP table user if exists ;
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
insert into task(create_date,task_name,task_type,status) values('2015-08-29','new task1','Normal','New');
insert into task(create_date,task_name,task_type,status) values('2015-08-29','new task2','Normal','Processing');

