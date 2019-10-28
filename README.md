## 平台简介
基于RuoYi 3.4版修改

### 已完成功能：

1、加入lombok插件

2、logback日志设置成彩色高亮打印

3、修改登录界面，优化登录界面效果

4、修改登录进入首页后的主页图

5、新增微信公众号开发基础框架模块

6、引入依赖weixin-java-mp3.5.0

7、application.yml新增微信开发配置参数

### 待完成功能：
1、微信界面化配置：appId、secret、token、aesKey

2、微信粉丝基础信息

3、微信常见功能等等。。。。。。。


## 内置功能

1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7.  参数管理：对系统动态配置常用参数。
8.  通知公告：系统通知公告信息发布维护。
9.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
10. 登录日志：系统登录日志记录查询包含登录异常。
11. 在线用户：当前系统中活跃用户状态监控。
12. 定时任务：在线（添加、修改、删除)任务调度包含执行结果日志。
13. 代码生成：前后端代码的生成（java、html、xml、sql)支持CRUD下载 。
14. 系统接口：根据业务代码自动生成相关的api接口文档。
15. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
16. 在线构建器：拖动表单元素生成相应的HTML代码。
17. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。

## 项目配置
1、本仓库下载页面(https://github.com/Thinkingcao/silence-boot)下载解压到工作目录

2、导入到Eclipse，菜单 File -> Import，然后选择 Maven -> Existing Maven Projects，点击 Next> 按钮，选择工作目录，然后点击 Finish 按钮，即可成功导入Eclipse会自动加载Maven依赖包，初次加载会比较慢（根据自身网络情况而定）

3、创建数据库ry并导入数据脚本silence(200190905).sql(请导入这个脚本，包含了定时任务等sql,最全的)

4、打开运行com.silence.SilenceApplication.java

5、打开浏览器，输入：http://127.0.0.1:8888/silence/login（默认账户 admin/admin123）
若能正确展示登录页面，并能成功登录，菜单及页面展示正常，则表明环境搭建成功


# 必要配置
1、修改数据库连接
编辑resources目录下的application-druid.yml

url: 服务器地址

username: 账号

password: 密码


2、开发环境配置
编辑resources目录下的application.yml

port: 端口

context-path: 部署路径 


## 在线体验
admin/admin123

演示地址：http://ruoyi.vip

文档地址：http://doc.ruoyi.vip

## 演示图
![Image](https://github.com/Thinkingcao/silence-boot/blob/master/doc/screenshot/login.png)

![Image](https://github.com/Thinkingcao/silence-boot/blob/master/doc/screenshot/wechat.png)
