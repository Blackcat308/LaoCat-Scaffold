# LaoCat-Scaffold

LaoCat Spring Cloud 脚手架

[![OSCS Status](https://www.oscs1024.com/platform/badge/Blackcat308/LaoCat-Scaffold.git.svg?size=small)](https://www.murphysec.com/dr/Dau7cMjPb7agKuLHhl)

# 相关问题提 Issues！！！！

### 项目启动需准备

* mysql 8.0
* jdk 1.8
* maven 3.8.1
* nacos 2.2.1
* redis 7.0.2

### 项目技术选型

* spring boot 2.6.13
* spring cloud 2021.0.5
* spring alibaba cloud 2021.0.5.0
* hutool 5.8.20
* mybatis 2.1.4
* knife4j 3.0.3
* JWT 0.9.0
* nacos 2.2.1
* xxl-job 2.3.0
* minio
* 七牛云

### 监控

* prometheus 2.39.1
* grafana 9.2.1

### 项目做完些什么？
1、spring security jwt \
2、网关集成knife4j接口文档 \
3、基于RBAC实现 用户 -> 角色 -> 权限 \
4、文件上传相关服务集成 \
5、监控相关集成


### 项目进度

DB

- 设计权限db &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 70%
- 新建权限相关db &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 70%

---

后台

- 新建项目引入maven结构 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 65%
- 构建项目基本架构 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 60%
- 引入网关 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 100%
- 引入security &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 100%
- 项目模块覆盖security &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 100%
- 处理feign上下文传递token、处理非网关访问。 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 60%
- 处理swagger ui 在微服务中模块兼容 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 100%
- 增加用户模块 权限相关 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 90%
- 全局异常处理、时间序列化处理 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 85%
- 引入xxl-job分布式任务调度 100%
- 新增文件服务 minio | 七牛云 100%

----
前台

- 前端模块构建 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 0%
- 管理系统页面处理 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 0%
- 处理登录授权流程 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 60%
- 接入权限系统 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 0%

----
监控

- 项目接入grafana+普罗米修斯 100%

# ❤ 感谢关注 ❤

![img_1.png](img_1.png)

- ❤ [CSND](https://blog.csdn.net/ProGram_BlackCat) ❤

- ❤ [博客园](https://www.cnblogs.com/LaoCat/) ❤

---
授权演示
![target.png](a-doc/演示1.gif)


---
[![OSCS Status](https://www.oscs1024.com/platform/badge/Blackcat308/LaoCat-Scaffold.git.svg?size=large)](https://www.murphysec.com/dr/Dau7cMjPb7agKuLHhl)

## 暂时没有合适的业务场景可开发，故只能维持框子，等待合适的业务场景，在进行相应的开发，泪目....
