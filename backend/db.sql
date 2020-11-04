-- 创建reatforum数据库
DROP DATABASE IF EXISTS unionbbs;
CREATE DATABASE unionbbs;

USE unionbbs;

-- 创建用户表
CREATE TABLE users(
    id BIGINT AUTO_INCREMENT NOT NULL,
    uname VARCHAR(20) NOT NULL,
    uaccount VARCHAR(20) NOT NULL,
    upassword VARCHAR(20) NOT NULL,
    uavatar VARCHAR(150) NOT NULL,
    usign VARCHAR(100),
    upermission INT NOT NULL,
    PRIMARY KEY (id)
)CHARSET=UTF8;

INSERT INTO users VALUES (1,"amtoaer","amtoaer","Liuwenxing1!","abc","ceshi",1);
INSERT INTO users VALUES (2,"test","test","TEST!","sdakljg","sldkgj",2);


-- 创建主题信息表
CREATE TABLE posts(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ptitle VARCHAR(50) NOT NULL,
    pdate DATE NOT NULL,
    pcomment INT NOT NULL,
    PRIMARY KEY(id)
)CHARSET=UTF8;

-- 创建评论信息表
CREATE TABLE comments(
    id BIGINT AUTO_INCREMENT NOT NULL,
    uid BIGINT NOT NULL,
    pid BIGINT NOT NULL,
    ccontent VARCHAR(300) NOT NULL,
    cdate DATE NOT NULL,
    cpre BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (uid) REFERENCES users(id),
    FOREIGN KEY (pid) REFERENCES posts(id)
)CHARSET=UTF8;