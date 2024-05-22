drop database tsk48;

create database tsk48;
use tsk48;

create table m_user(
user_id VARCHAR(24) PRIMARY KEY NOT NULL,
password VARCHAR(32) NOT NULL,
user_name VARCHAR(20) UNIQUE NOT NULL,
update_datetime TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT current_timestamp);

create table m_category(
category_id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
category_name VARCHAR(20) UNIQUE NOT NULL,
update_datetime TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT current_timestamp);

create table m_status(
status_code CHAR(2) PRIMARY KEY NOT NULL,
status_name VARCHAR(20) UNIQUE NOT NULL,
update_datetime TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT current_timestamp);

create table t_task(
task_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
task_name VARCHAR(50) NOT NULL,
category_id INT NOT NULL,
limit_date DATE,
user_id VARCHAR(24) NOT NULL,
status_code CHAR(2) NOT NULL,
memo VARCHAR(100),
create_datetime TIMESTAMP NOT NULL DEFAULT current_timestamp,
update_datetime TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT current_timestamp,
FOREIGN KEY(category_id) REFERENCES m_category(category_id),
FOREIGN KEY(user_id) REFERENCES m_user(user_id),
FOREIGN KEY(status_code) REFERENCES m_status(status_code));

INSERT INTO m_user(user_id, password, user_name) VALUES ('comsize', 'comsize', 'コンサイズ');
INSERT INTO m_user(user_id, password, user_name) VALUES ('tanaka', 'tanaka', '山口');
INSERT INTO m_category(category_name) VALUES ('新商品A：開発プロジェクト');
INSERT INTO m_category(category_name) VALUES ('既存商品B：改良プロジェクト');
INSERT INTO m_status(status_code, status_name) VALUES ('00', '未着手');
INSERT INTO m_status(status_code, status_name) VALUES ('50', '着手');
INSERT INTO m_status(status_code, status_name) VALUES ('99', '完了');
INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo) VALUES ('なにかしら', 1, '2024-06-30', 'comsize', '50', 'なにか');
INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo) VALUES ('どこかしら', 1, '2024-07-20', 'tanaka', '50', 'あれ');

CREATE TABLE t_comment( 
    comment_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT
    , task_id INT NOT NULL
    , FOREIGN KEY (task_id) REFERENCES t_task(task_id)
    , user_id VARCHAR (24) NOT NULL
    , FOREIGN KEY (user_id) REFERENCES m_user(user_id)
    , `comment` VARCHAR (100) NOT NULL
    , update_datetime TIMESTAMP NOT NULL 
        ON UPDATE CURRENT_TIMESTAMP DEFAULT current_timestamp
);

INSERT 
INTO tsk48.t_comment(task_id, user_id, `comment`) 
VALUES (1, 'comsize', 'テストコメント');
INSERT 
INTO tsk48.t_comment(task_id, user_id, `comment`) 
VALUES (1, 'tanaka', 'hi');
INSERT 
INTO tsk48.t_comment(task_id, user_id, `comment`) 
VALUES (2, 'tanaka', 'SO RE NA');