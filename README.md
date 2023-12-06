# DB-homework
## 数据库大作业

### 项目简介：

本项目采用前后端分离开发，实现了一个简单的B/S架构的西工大图书商城。后端使用java语言进行开发，使用jsp、jdbc等技术，数据库使用mysql8.0，前端使用html、css、javascript，使用简单的vue框架进行美化。

### 技术栈：

**后端：java+jsp+jdbc+mysql**

**前端：html+css+js+vue**

### 环境搭建：

* **操作系统：windows11**
* **JDK版本：JDK17（JDK8也行，测试过也能跑）**
* **mysql版本：8.0.26**
* **Tomcat服务器**：**8.5.27**（具体配置流程参考其他网上教程）
* **IDE：IDEA2023**
* **数据库名称：book**

### **操作流程：**

* 首先配置project，注意下图中SDK需要和JDK的版本匹配，编译器输出选择当前项目的根路径，最后加一个out。

![image-20231206233218536](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231206233218536.png)

* 配置模块

![image-20231207003330061](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231207003330061.png)

* 添加facts

![image-20231207003450263](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231207003450263.png)

* 添加工件

![image-20231207003733515](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231207003733515.png)

* 配置tomcat服务器

![image-20231201001241854](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201001241854.png)


![image-20231201001159579](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201001159579.png)



* 修改src目录下的druid.properties文件

![image-20231201000816544](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201000816544.png)


​	book为本机中该项目的数据库名，需要在本地新建一个book数据库，其余安装自己本地mysql连接进行修改即可。

​	需要运行sqldatabase的sql脚本，插入数据

做完环境配置，不出意外即可在IDEA中运行该项目

### 项目文件目录说明：

* apache-tomcat-8.5.27——项目所需的tomcat服务器环境，需要自行配置环境变量

*	demo——项目运行结果展示视频

* libs——java运行所需要的一些包
* out——输出文件

* sqldatabase——数据库脚本，用于插入数据

* src——源文件目录，主要是后端代码和操作数据库的
* web——前端

### 运行结果展示：

主页面

![image-20231201001327196](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201001327196.png)
登录页面

![image-20231201001344757](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201001344757.png)

注册界面

![image-20231201001404148](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201001404148.png)

登录成功提示跳转页面

![image-20231201001455825](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201001455825.png)
购物车页面

![image-20231201001522254](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201001522254.png)

结账成功页面

![image-20231201001534959](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201001534959.png)

显示订单情况页面

![image-20231201001612042](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201001612042.png)
后台管理添加顶单页面

![image-20231201001643662](C:\Users\hu\AppData\Roaming\Typora\typora-user-images\image-20231201001643662.png)
