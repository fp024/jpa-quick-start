CREATE TABLE `jpa_test`.`s_emp` (
  `id` INT NOT NULL,
  `name` VARCHAR(25) NOT NULL,
  `start_date` DATETIME NULL,
  `title` VARCHAR(50) NULL,
  `dept_name` VARCHAR(50) NULL,
  `salary` DOUBLE NULL,
  PRIMARY KEY (`id`));

  
-- MySQL 8.1 테이블 생성시 경고에서 DOUBLE(5,2) 와 같이 소수점 관련 내용 지정하는 것은 
-- 앞으로 폐기될 수 있는 기능이라고 한다. 
INSERT INTO `jpa_test`.`s_emp` (`id`,`name`,`start_date`,`title`,`dept_name`,`salary`)
     VALUES (1, 'LHS', '2002-12-03', 'CTO', 'Radeon Technologies Group', '10000');
