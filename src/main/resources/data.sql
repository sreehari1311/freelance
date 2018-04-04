CREATE TABLE user ( 
  user_id  varchar(20) not null primary key,
  email varchar(100) not null,
  phone varchar(20) not null
  );

CREATE TABLE project ( 
   id int not null auto_increment primary key, 
   title VARCHAR(100) NOT NULL, 
   description Text , 
   budget number(10,2),
   last_bid_date datetime,
   submission_date datetime default SYSDATE, 
   update_date datetime default SYSDATE,
   user_id varchar(20)  NOT NULL,
   is_Open TINYINT(1) DEFAULT 1 NOT NULL 
);

create table bid (
    id int not null auto_increment primary key,
    project_id int NOT NULL,
    bid_amount number(10,2),
    user_id varchar(20)  NOT NULL ,
   submission_date datetime default SYSDATE, 
   update_date datetime default SYSDATE,
   FOREIGN KEY (project_id) REFERENCES project(id)
   ON DELETE CASCADE
);