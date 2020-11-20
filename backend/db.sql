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
    uavatar VARCHAR(150) DEFAULT "https://gravatar.loli.net/avatar/d41d8cd98f00b204e9800998ecf8427e?s=40&d=mp" NOT NULL,
    usign VARCHAR(100) DEFAULT "" NOT NULL,
    upermission INT DEFAULT 2 NOT NULL,
    PRIMARY KEY (id)
) CHARSET = UTF8;
INSERT INTO users
VALUES (
        1,
        'amtoaer',
        'amtoaer',
        'Liuwenxing1!',
        'https://cdn.jsdelivr.net/gh/amtoaer/blogcdn@master/img/custom/amtoaer.png',
        '测试签名',
        1
    );
-- 创建主题信息表
CREATE TABLE posts(
    id BIGINT AUTO_INCREMENT NOT NULL,
    uid BIGINT NOT NULL,
    ptitle VARCHAR(50) NOT NULL,
    pdate DATE NOT NULL,
    pcomment INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (uid) REFERENCES users(id)
) CHARSET = UTF8;
INSERT INTO posts
VALUES (1, 1, '这是第一篇文章！', '2020-11-11', 0);
INSERT INTO posts
VALUES (2, 1, '这是我的第二篇文章', '2020-11-19', 0);
-- 创建评论信息表
CREATE TABLE comments(
    id BIGINT AUTO_INCREMENT NOT NULL,
    uid BIGINT NOT NULL,
    pid BIGINT NOT NULL,
    ccontent VARCHAR(300) NOT NULL,
    cdate DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (uid) REFERENCES users(id),
    FOREIGN KEY (pid) REFERENCES posts(id)
) CHARSET = UTF8;
INSERT INTO comments
VALUES (1, 1, 1, '这是第一篇文章的内容', '2020-11-11');
INSERT INTO comments
VALUES (2, 1, 1, '你咋回事啊', '2020-11-12');
INSERT INTO comments
VALUES (3, 1, 2, '这是第二篇帖子的内容', '2020-11-19');
INSERT INTO comments
VALUES (4, 1, 2, '这是第二篇帖子的评论', '2020-11-20');