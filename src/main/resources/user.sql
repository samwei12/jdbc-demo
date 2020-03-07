create table user (

id int primary key auto_increment,

name varchar(20),

password varchar(20)

)

insert into user values (null,'jack','123'),(null,'rose','456');

-- 登录, SQL 中大小写不敏感

select * from user where name='JACK' and password='123';

-- 登录失败

select * from user where name='JACK' and password='333';