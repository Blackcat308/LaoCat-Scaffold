## nacos

### 文件已放到a-software

- 1.解压
- 2.找到conf文件夹
- 3.配置文件修改,箭头指向都需要进行更改和解注释
  ![img.png](img.png)
- 4.新建laocat_nacos数据库。字符集utf8mb4 排序规则utf8mb4_0900_ai_ci
- 5.执行laocat_nacos.sql。
- 6.windows启动、找到bin目录、在bin目录下执行。
  ![img_1.png](img_1.png)

```shell
.\startup.cmd -m standalone
```