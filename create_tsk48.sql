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
INSERT INTO m_user(user_id, password, user_name) VALUES ('arino', 'arino', '有野');
INSERT INTO m_user(user_id, password, user_name) VALUES ('shige', 'shige', '重松');
INSERT INTO m_user(user_id, password, user_name) VALUES ('aoki', 'aoki', '青木');
INSERT INTO m_category(category_name) VALUES ('新商品A：開発プロジェクト');
INSERT INTO m_category(category_name) VALUES ('既存商品B：改良プロジェクト');
INSERT INTO m_category(category_name) VALUES ('パクリ商品C：法対策プロジェクト');
INSERT INTO m_status(status_code, status_name) VALUES ('00', '未着手');
INSERT INTO m_status(status_code, status_name) VALUES ('50', '着手');
INSERT INTO m_status(status_code, status_name) VALUES ('99', '完了');
INSERT INTO m_status(status_code, status_name) VALUES ('55', '打ち上げ');
INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo) VALUES ('なにかしら', 1, '2024-06-30', 'comsize', '50', 'なにか');
INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo) VALUES ('どこかしら', 3, '2024-07-20', 'tanaka', '50', 'あれ');
INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo) VALUES ('担当有野', 2, '2024-06-30', 'arino', '00', 'どっか');
INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo) VALUES ('担当重松', 2, '2024-07-20', 'shige', '50', '板');
INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo) VALUES ('担当青木', 2, '2024-06-30', 'aoki', '55', '初代');

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

INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (1, 'comsize', 'テストコメント');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (1, 'tanaka', 'hi');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (1, 'arino', 'SO RE NA');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (1, 'shige', 'テストコメント');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (1, 'aoki', 'hi');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (2, 'comsize', 'テストコメント');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (2, 'tanaka', 'hi');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (2, 'arino', 'SO RE NA');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (2, 'shige', 'テストコメント');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (2, 'aoki', 'hi');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (3, 'comsize', 'テストコメント');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (3, 'tanaka', 'hi');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (3, 'arino', 'SO RE NA');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (3, 'arino', 'SO RE NA!');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (3, 'arino', 'SO RE NA!!');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (3, 'shige', 'テストコメント');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (3, 'aoki', 'hi');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (4, 'comsize', 'テストコメント');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (4, 'tanaka', 'hi');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (4, 'arino', 'SO RE NA');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (4, 'shige', 'テストコメント');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (4, 'shige', 'テストコメント!');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (4, 'shige', 'テストコメント!!');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (4, 'aoki', 'hi');INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (1, 'comsize', 'テストコメント');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (5, 'tanaka', 'hi');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (5, 'arino', 'SO RE NA');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (5, 'shige', 'テストコメント');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (5, 'aoki', 'hi');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (5, 'aoki', 'hi!');
INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (5, 'aoki', 'hi!!');