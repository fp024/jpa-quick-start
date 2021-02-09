DROP TABLE `jpa_test`.`s_dept`;
DROP TABLE `jpa_test`.`s_emp`;

CREATE TABLE `jpa_test`.`s_dept`
(
    `dept_id` BIGINT      NOT NULL,
    `name`    VARCHAR(50) NOT NULL,
    PRIMARY KEY (`dept_id`)
);


CREATE TABLE `jpa_test`.`s_emp`
(
    `id`      BIGINT      NOT NULL,
    `name`    VARCHAR(25) NOT NULL,
    `dept_id` BIGINT NULL,
    PRIMARY KEY (`id`)
);


-- MySQL 워크벤치에서 관계 설정시 만들어주는 설정.

-- 인덱스를 On/Off 시켜줄 수 있는 기능이라함.
ALTER TABLE `jpa_test`.`s_emp`
    ADD INDEX `s_emp_dept_id_fk_idx` (`dept_id` ASC) VISIBLE;

-- ON DELETE/UPDATE 시에 NO ACTION 구문이 있긴한데, 아무것도 안썻을 때도 NO ACTION 일것이라
-- 워크벤치에서 만들어준 외래키 조건을 써도 괜찮겠다. 책의 진행은.. 이부분을 JPA 로서 처리할 것으로 보인다.
ALTER TABLE `jpa_test`.`s_emp`
    ADD CONSTRAINT `s_emp_dept_id_fk`
        FOREIGN KEY (`dept_id`)
            REFERENCES `jpa_test`.`s_dept` (`dept_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

INSERT INTO `jpa_test`.`s_dept` (`dept_id`, `name`) VALUES ('1', '개발부');

INSERT INTO `jpa_test`.`s_emp` (`id`, `name`, `dept_id`) VALUES ('1', '탈보가이', '1');
INSERT INTO `jpa_test`.`s_emp` (`id`, `name`, `dept_id`) VALUES ('2', '언데드', '1');
