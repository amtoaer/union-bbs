<h1 align="center">Union BBS</h1>
<p align="center">
<img src="https://forthebadge.com/images/badges/made-with-java.svg"></img>
<img src="https://forthebadge.com/images/badges/made-with-vue.svg"></img>
</p>

## 项目介绍

Union BBS 是一个论坛系统。

由于开发者水平和时间的限制，其完成度较低，仅仅停留在可用阶段（~~不过作为 Java Web 作业似乎是够用了~~）。

### 前端

前端使用`vue 2`构建，使用`yarn`进行依赖管理，使用的 UI 库是`vuetify`。

### 后端

后端使用`Spring Boot (Java 14)`作为启动配置（~~虽然实际使用的是 Java 15~~），使用`maven`进行依赖管理。

### 鉴权

使用`Jwt`进行前后端分离的鉴权。首先前端请求登录/注册得到`token`，将其存储到`localStorage`中；在后续请求中，前端需要将`token`放到请求头中，后端通过`token`进行验证。

## 开发环境

本机的开发环境：

- OS : Arch Linux
- DB : MariaDB 10.5.8
- Java : 15

## 部署环境

在线实例采用的部署环境

- OS : Ubuntu Server 18.04
- DB : MySQL 5.7.28
- Java : 15

## 部署配置

以部署在`Nginx`上的在线实例为例，配置文件如下：

```nginx
# union bbs
server {
        server_name bbs.allwens.work;

        # 该目录下放置前端编译后的静态文件
        root /home/admin/website;

        index index.html;

        # vue开启history模式所必须的配置
        location /{
                try_files $uri $uri/ /index.html;
        }

        # 通过反向代理访问api
        location /api{
                proxy_pass http://localhost:9876;
        }

        # 强制跳转https
        error_page 497 =301 https://$http_host$request_uri;

        # 监听10000端口
        listen [::]:10000 ssl;
        listen 10000 ssl;

        # 指定证书
        ssl_certificate /etc/cert/allwens.work.cer;
        ssl_certificate_key /etc/cert/allwens.work.key;

}
```

## 目录结构

针对关键文件/目录作出如下说明：

```bash
./
├── backend
│   ├── db.sql # 数据库语句
│   ├── pom.xml # 后端依赖
│   └── src
│       └── main
│           ├── java
│           │   └── work
│           │       └── allwens
│           │           └── unionbbs
│           │               └── unionbbs
│           │                   ├── controller
│           │                   │   └── rest # api定义
│           │                   ├── dao # 数据访问层
│           │                   ├── entity # 数据实体
│           │                   ├── request # Post请求体
│           │                   └── security # Jwt验证和登陆过滤器
│           └── resources
│               └── application.yml # spring boot相关配置
├── frontend
│   ├── src
│   │   ├── App.vue
│   │   ├── main.js
│   │   ├── router
│   │   │   └── index.js # 路由配置
│   │   ├── store
│   │   │   └── index.js # vuex配置
│   │   ├── utils
│   │   │   ├── axios.js # 为axios自动添加请求头
│   │   │   └── request.js # 登录、注册功能
│   │   └── views
│   │       ├── Comments.vue # 评论页面
│   │       ├── Home.vue # 帖子列表
│   │       ├── Login.vue # 登陆页面
│   │       ├── Post.vue # 发帖页面
│   │       ├── Profile.vue # 个人信息页面
│   │       └── Register.vue # 注册页面
│   ├── vue.config.js
│   └── yarn.lock
└── README.md
```

## 程序打包

### 前端

```bash
yarn && yarn build
```

### 后端

```bash
mvn package
```
