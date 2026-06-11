# 🎲 棋牌室预约管理系统

基于 Spring Boot + MyBatis Plus 的棋牌室预约管理后端系统。

## ✨ 功能特性

- 🔐 用户登录/注册（手机号+密码）
- 🏠 包厢查询（全部包厢/空闲包厢）
- 📅 在线预约（支持时间冲突检测）
- 📋 我的预约（查看历史预约、取消预约）
- 📊 统一响应结构 Result
- 🛡️ 全局异常处理

## 🛠️ 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 2.7.18 | 项目基础框架 |
| MyBatis Plus | 3.5.3.1 | ORM框架，简化数据库操作 |
| MySQL | 8.0 | 数据库 |
| Lombok | - | 简化实体类代码 |
| Maven | - | 项目构建工具 |

## 📁 项目结构
```
chess-reservation/
├── src/main/java/com/chess/
│   ├── ChessApplication.java      # 启动类
│   ├── common/                    # 公共类（Result、异常处理）
│   ├── controller/                # 控制器层
│   ├── service/                   # 业务逻辑层
│   ├── mapper/                    # 数据访问层
│   └── entity/                    # 实体类
└── src/main/resources/
    └── application.yml            # 配置文件
```
## 🚀 快速开始

### 1. 环境要求
- JDK 8+
- MySQL 8.0
- Maven 3.6+

### 2. 数据库初始化

执行以下SQL脚本：
```sql
CREATE DATABASE chess_room;
USE chess_room;

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    phone VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) DEFAULT '新用户',
    level VARCHAR(20) DEFAULT '普通会员',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE room (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    price INT NOT NULL,
    status VARCHAR(20) DEFAULT '空闲',
    type VARCHAR(50) DEFAULT '普通包厢'
);

CREATE TABLE reservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    status VARCHAR(20) DEFAULT '已预约',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (room_id) REFERENCES room(id)
);

INSERT INTO user (phone, password, name, level) VALUES 
('13812345678', '123456', '陈冬怡', '黄金会员');

INSERT INTO room (name, capacity, price, status, type) VALUES 
('牡丹厅', 4, 88, '空闲', '小包厢'),
('兰花厅', 6, 128, '空闲', '中包厢'),
('竹韵厅', 8, 168, '空闲', '大包厢'),
('菊韵厅', 10, 198, '空闲', 'VIP包厢');
```

### 3. 修改配置

编辑 src/main/resources/application.yml，修改数据库密码。

### 4. 启动项目

mvn spring-boot:run

### 5. 测试接口

访问 http://localhost:8080/api/rooms，看到返回JSON数据即成功。

## 📬 接口文档

| 接口 | 方法 | 说明 |
|------|------|------|
| /api/user/login | POST | 用户登录 |
| /api/user/register | POST | 用户注册 |
| /api/rooms | GET | 查询所有包厢 |
| /api/rooms/available | GET | 查询空闲包厢 |
| /api/reservation/create | POST | 创建预约 |
| /api/reservation/my | GET | 我的预约 |
| /api/reservation/cancel/{id} | POST | 取消预约 |

## 👨‍💻 作者

陈冬怡 - GitHub: https://github.com/Minsanchendongyi

## 📄 许可证

仅供学习使用
