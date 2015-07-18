create database if not exists testdb; -- create db
-- drop database if exists testdb; -- delete db
-- show databases; -- print list of db
use testdb; -- set db as current

drop table if exists stable;
drop table if exists ftable;
-- create table with entered columns
CREATE TABLE IF NOT EXISTS ftable (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL DEFAULT 'default',
    f FLOAT NOT NULL,
    PRIMARY KEY (id)
);
describe ftable;
-- create table stable like ftable;
-- or
-- CREATE TABLE stable SELECT * FROM ftable;

-- MODIFICATIONS
alter table ftable drop column f;
alter table ftable change name changed_name varchar(20);
alter table ftable modify changed_name char(10);
alter table ftable add column test_col varchar(5) not null  default 'test';
describe ftable;

-- INDEX
create index iname on ftable(changed_name);
-- drop index iname on a;
CREATE TABLE stable (
    id INT NOT NULL AUTO_INCREMENT,
    test FLOAT NOT NULL,
    fk INT NOT NULL,
    UNIQUE KEY (test),
    FOREIGN KEY (fk)
        REFERENCES ftable (id),
    PRIMARY KEY (id)
);

-- INSERT/DELETE/UPDATE
insert into ftable values
(null,'str1',0.1),
(null,'str2',0.2);
-- ---------------------------------
DELETE FROM ftable 
WHERE
    changed_name LIKE 'str%';
-- ---------------------------------
SELECT 
    *
FROM
    ftable;
-- ---------------------------------
UPDATE ftable 
SET 
    changed_name = 'newStr'
WHERE
    changed_name LIKE 'str%';
-- ---------------------------------
SELECT 
    *
FROM
    ftable;

-- ORDER BY
SELECT 
    *
FROM
    ftable
ORDER BY changed_name DESC;
-- ---------------------------------
SELECT 
    *
FROM
    ftable
ORDER BY test_col ASC;
-- ---------------------------------
SELECT 
    *
FROM
    ftable
ORDER BY changed_name ASC , test_col DESC;
-- ---------------------------------
SELECT 
    *
FROM
    ftable
ORDER BY (CASE changed_name
    WHEN 'str1' THEN 1
    WHEN 'newStr' THEN 2
    WHEN 'str2' THEN 3
    ELSE 100
END), test_col desc;

-- GROUP BY
SELECT 
    id, changed_name, SUM(test_col)
FROM
    ftable
GROUP BY test_col;

-- VIEW
CREATE VIEW view_select_all
AS
SELECT
  *
FROM ftable
WHERE id > 2;

SELECT * FROM view_select_all WHERE test_col = 0.3;

-- JOINs
SELECT * FROM ftable INNER JOIN stable ON ftable.id = stable.fk;
SELECT * FROM ftable LEFT JOIN stable ON ftable.id = stable.fk;
SELECT * FROM ftable RIGHT JOIN stable ON ftable.id = stable.fk;
