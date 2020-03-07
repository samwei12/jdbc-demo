CREATE TABLE `bank` (
    `name` varchar(10) DEFAULT NULL,
    `money` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

insert into bank(money, name)
values (100, '张三'),
       (200, '李四'),
       (300, '王五'),
       (400, '赵六');