## mybatis学习笔记

### 一、mybatis初识

#### 1.1mybatis介绍

​        MyBatis 本是apache的一个开源项目iBatis, 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。2013年11月迁移到Github。

​        MyBatis是一个优秀的持久层框架，它对jdbc的操作数据库的过程进行封装，使开发者只需要关注 SQL 本身，而不需要花费精力去处理例如注册驱动、创建connection、创建statement、手动设置参数、结果集检索等jdbc繁杂的过程代码。

​       Mybatis通过xml或注解的方式将要执行的各种statement（statement、preparedStatemnt、CallableStatement）配置起来，并通过java对象和statement中的sql进行映射生成最终执行的sql语句，最后由mybatis框架执行sql并将结果映射成java对象并返回。​    

#### 1.2 JDBC的问题

为什么我们要使用mybatis，是因为JDBC存在以下问题

1、 数据库连接创建、释放频繁造成系统资源浪费，从而影响系统性能。如果使用数据库连接池可解决此问题。

2、 Sql语句在代码中硬编码，造成代码不易维护，实际应用中sql变化的可能较大，sql变动需要改变java代码。

3、 使用preparedStatement向占有位符号传参数存在硬编码，因为sql语句的where条件不一定，可能多也可能少，修改sql还要修改代码，系统不易维护。

4、 对结果集解析存在硬编码（查询列名），sql变化导致解析代码变化，系统不易维护，如果能将数据库记录封装成pojo对象解析比较方便。

#### 1.3 Mybatis架构

![1552397530778](img\架构.png)

1.  mybatis配置


​       SqlMapConfig.xml，此文件作为mybatis的全局配置文件，配置了mybatis的运行环境等信息。mapper.xml   文件即sql映射文件，文件中配置了操作数据库的sql语句。此文件需要在SqlMapConfig.xml中加载。

2. 通过mybatis环境等配置信息构造SqlSessionFactory即会话工厂
3. 由会话工厂创建sqlSession即会话，操作数据库需要通过sqlSession进行。
4. mybatis底层自定义了Executor执行器接口操作数据库，Executor接口有两个实现，一个是基本执行器、一个是缓存执行器。
5. Mapped Statement也是mybatis一个底层封装对象，它包装了mybatis配置信息及sql映射信息等。mapper.xml文件中一个sql对应一个Mapped Statement对象，sql的id即是Mapped statement的id
6. Mapped Statement对sql执行输入参数进行定义，包括HashMap、基本类型、pojo，Executor通过Mapped Statement在执行sql前将输入的java对象映射至sql中，输入参数映射就是jdbc编程中对preparedStatement设置参数。

Mapped Statement对sql执行输出结果进行定义，包括HashMap、基本类型、pojo，Executor通过Mapped Statement在执行sql后将输出结果映射至java对象中，输出结果映射过程相当于jdbc编程中对结果的解析处理过程。

#### 1.4 入门程序

使用mybatis进行简单的增删改查能够让我们先有个大体感受，话不多说，开始撸代码

##### **第一步：**新建一个maven项目

![1552400658768](img\新增.png)

增加依赖，POM文件内容：

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.yuanqinnan</groupId>
  <artifactId>mybatis-first</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>mybatis-first</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.4.1</version>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>6.0.6</version>
    </dependency>
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.25</version>
    </dependency>
  </dependencies>

</project>
```

##### **第二步：**添加配置文件

创建资源文件夹config，加入log4j.properties和SqlMapConfig.xml配置文件，log4j.properties为日志，暂且不管在config下创建SqlMapConfig.xml，如下：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 和spring整合后 environments配置将废除 -->
    <environments default="development">
        <environment id="development">
            <!-- 使用jdbc事务管理 -->
            <transactionManager type="JDBC" />
            <!-- 数据库连接池 -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver" />
                <property name="url"
                          value="jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8" />
                <property name="username" value="root" />
                <property name="password" value="123456" />
            </dataSource>
        </environment>
    </environments>
</configuration>
```

##### **第三步：**创建数据库并新建实体

```sql
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名称',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '王五', null, '2', null);
INSERT INTO `user` VALUES ('10', '张三', '2014-07-10', '1', '北京市');
INSERT INTO `user` VALUES ('16', '张小明', null, '1', '河南郑州');
INSERT INTO `user` VALUES ('22', '陈小明', null, '1', '河南郑州');
INSERT INTO `user` VALUES ('24', '张三丰', null, '1', '河南郑州');
INSERT INTO `user` VALUES ('25', '陈小明', null, '1', '河南郑州');
INSERT INTO `user` VALUES ('26', '王五', null, null, null);
```

```java
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;// 用户姓名
    private String sex;// 性别
    private Date birthday;// 生日
    private String address;// 地址


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", sex=" + sex
                + ", birthday=" + birthday + ", address=" + address + "]";
    }
}
```

##### 第四步：sql映射文件

先新增一个查询方法

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace：命名空间，用于隔离sql，还有一个很重要的作用，后面会讲 -->
<mapper namespace="test">
<select id="queryUserById" parameterType="int" resultType="com.yuanqinnan.model.User">
    SELECT * FROM `user`where id=#{id}
</select>
</mapper>
```

##### 第五步：加载映射文件

在SqlMapConfig.xml中增加代码段

```xml
<mappers>
    <!-- 映射文件方式1，一个一个的配置-->
    <mapper resource="config/sqlmap/User.xml"/>
</mappers>
```

整体结构如下：

![1552404114688](img\结构.png)

##### 第六步：测试

```java
public class CRUDTest {
    //定义 SqlSession
    SqlSession session =null;

    @Before
    public void init(){
        //定义mybatis全局配置文件
        String resource = "config/SqlMapConfig.xml";
        //加载 mybatis 全局配置文件
        InputStream inputStream = CRUDTest.class.getClassLoader()
                .getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //根据 sqlSessionFactory 产生 session
        session = sessionFactory.openSession();
    }

    //根据id查询user表数据
    @Test
    public void testSelectUserById(){
        String statement = "queryUserById";
        User user = session.selectOne(statement, 1);
        System.out.println(user);
        session.close();
    }
}
```

测试结果：User [id=1, username=王五, sex=2, birthday=null, address=null]

至此，mybatis的功能已经实现，我们按照此例继续其他的操作

#### 1.5 其他操作

##### 1.5.1 查询列表

user.xml 增加查询

```xml
<!-- 查询 user 表的所有数据-->
<select id="selectUserAll" resultType="com.yuanqinnan.model.User">
    select * from user
</select>
```

测试：

```java
//查询所有user表所有数据
@Test
public void testSelectUserAll(){
    String statement = "selectUserAll";
    List<User> listUser = session.selectList(statement);
    for(User user : listUser){
        System.out.println(user);
    }
    session.close();
}
```

结果：

![1552487750669](img\查询结果.png)

##### 1.5.2 模糊查询(用${}实现)

```xml
    <!--
            1、${value}里面必须要写value，不然会报错
            2、${}表示拼接 sql 字符串，将接收到的参数不加任何修饰拼接在sql语句中
            3、使用${}会造成 sql 注入
    -->
    <select id="selectLikeUserName" resultType="com.yuanqinnan.model.User" parameterType="String">
        select * from user where username like '%${value}%'
        <!-- select * from user where username like #{username} -->
    </select>
```

测试：

```java
//模糊查询：根据 user 表的username字段(用${}实现)
@Test
public void testSelectLikeUserName(){
    String statement = "selectLikeUserName";
    List<User> listUser = session.selectList(statement, "三");
    for(User user : listUser){
        System.out.println(user);
    }
    session.close();

}
```

结果：![1552488276835](img\模糊查询1.png)

##### 1.5.3 模糊查询(用#{}实现)

```xml
<!--#{}实现-->
<select id="selectLikeUserName2" resultType="com.yuanqinnan.model.User" parameterType="String">
    select * from user where username like #{username}
</select>
```

测试：

```xml
//模糊查询：根据 user 表的username字段(用#{}实现)
@Test
public void testSelectLikeUserName2(){
    String statement = "selectLikeUserName2";
    List<User> listUser = session.selectList(statement, "%三%");
    for(User user : listUser){
        System.out.println(user);
    }
    session.close();

}
```

结果与上面相同

##### 1.5.4 新增用户

```xml
<!-- 向 user 表插入一条数据 -->
<insert id="insertUser" parameterType="com.yuanqinnan.model.User">
    insert into user(id,username,sex,birthday,address)
        value(#{id},#{username},#{sex},#{birthday},#{address})
</insert>
```

测试：

```java
//向 user 表中插入一条数据
@Test
public void testInsertUser(){
    String statement = "insertUser";
    User user = new User();
    user.setUsername("袁帅");
    user.setSex("1");
    session.insert(statement, user);
    //提交插入的数据
    session.commit();
    session.close();
}
```

结果：![1552489262768](E:\学习笔记\img\新增结果.png)

如果我们想要返回当前新增的ID，则需要先获取自增ID

```xml
<!-- 保存用户 -->
<insert id="saveUser" parameterType="com.yuanqinnan.model.User">
    <!-- selectKey 标签实现主键返回 -->
    <!-- keyColumn:主键对应的表中的哪一列 -->
    <!-- keyProperty：主键对应的pojo中的哪一个属性 -->
    <!-- order：设置在执行insert语句前执行查询id的sql，在执行insert语句之后执行查询id的sql -->
    <!-- resultType：设置返回的id的类型 -->
    <selectKey keyColumn="id" keyProperty="id" order="AFTER"
               resultType="int">
        SELECT LAST_INSERT_ID()
    </selectKey>
    INSERT INTO `user`
    (username,birthday,sex,address) VALUES
    (#{username},#{birthday},#{sex},#{address})
</insert>
```

```java
@Test
public void testInsertUser2(){
    String statement = "saveUser";
    User user = new User();
    user.setUsername("袁大帅");
    user.setSex("1");
    session.insert(statement, user);
    System.out.println(user);
    //提交插入的数据
    session.commit();
    session.close();
}
```

结果：User [id=29, username=袁大帅, sex=1, birthday=null, address=null]

##### 1.5.5  更新用户

```xml
<!-- 根据 id 更新 user 表的数据 -->
<update id="updateUserById" parameterType="com.yuanqinnan.model.User">
    update user set username=#{username} where id=#{id}
</update>
```

测试：

```java
//根据 id 更新 user 表的数据
@Test
public void testUpdateUserById(){
    String statement = "updateUserById";
    //如果设置的 id不存在，那么数据库没有数据更改
    User user = new User();
    user.setId(29);
    user.setUsername("袁不帅");
    session.update(statement, user);
    session.commit();
    session.close();
}
```

结果：![1552489967809](img\修改.png)

1.5.6 删除用户

```xml
<!-- 根据 id 删除 user 表的数据 -->
<delete id="deleteUserById" parameterType="int">
    delete from user where id=#{id}
</delete>
```

测试：

```java
//根据 id 删除 user 表的数据
@Test
public void testDeleteUserById(){
    String statement = "deleteUserById";
    session.delete(statement,29);
    session.commit();
    session.close();
}
```

结果：删除成功

#### 1.6 总结

**#{}和${}**

\#{}表示一个占位符号，通过#{}可以实现preparedStatement向占位符中设置值，自动进行java类型和jdbc类型转换。#{}可以有效防止sql注入。 #{}可以接收简单类型值或pojo属性值。 如果parameterType传输单个简单类型值，#{}括号中可以是value或其它名称。

 ${}表示拼接sql串，通过${}可以将parameterType 传入的内容拼接在sql中且不进行jdbc类型转换， ${}可以接收简单类型值或pojo属性值，如果parameterType传输单个简单类型值，${}括号中只能是value。

 **parameterType和resultType**

parameterType：指定输入参数类型，mybatis通过ognl从输入对象中获取参数值拼接在sql中。

resultType：指定输出结果类型，mybatis将sql查询结果的一行记录数据映射为resultType指定类型的对象。如果有多条数据，则分别进行映射，并把对象放到容器List中

 **selectOne和selectList**

selectOne查询一条记录，如果使用selectOne查询多条记录则抛出异常：

org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 3

at org.apache.ibatis.session.defaults.DefaultSqlSession.selectOne(DefaultSqlSession.java:70)

selectList可以查询一条或多条记录。

**Mybatis解决jdbc编程的问题**

1、 数据库连接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库连接池可解决此问题。

解决：在SqlMapConfig.xml中配置数据连接池，使用连接池管理数据库链接。

2、 Sql语句写在代码中造成代码不易维护，实际应用sql变化的可能较大，sql变动需要改变java代码。

解决：将Sql语句配置在XXXXmapper.xml文件中与java代码分离。

3、 向sql语句传参数麻烦，因为sql语句的where条件不一定，可能多也可能少，占位符需要和参数一一对应。

解决：Mybatis自动将java对象映射至sql语句，通过statement中的parameterType定义输入参数的类型。

4、 对结果集解析麻烦，sql变化导致解析代码变化，且解析前需要遍历，如果能将数据库记录封装成pojo对象解析比较方便。

解决：Mybatis自动将sql执行

**mybatis与hibernate不同**

Mybatis和hibernate不同，它不完全是一个ORM框架，因为MyBatis需要程序员自己编写Sql语句。mybatis可以通过XML或注解方式灵活配置要运行的sql语句，并将java对象和sql语句映射生成最终执行的sql，最后将sql执行的结果再映射生成java对象。

Mybatis学习门槛低，简单易学，程序员直接编写原生态sql，可严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发，例如互联网软件、企业运营类软件等，因为这类软件需求变化频繁，一但需求变化要求成果输出迅速。但是灵活的前提是mybatis无法做到数据库无关性，如果需要实现支持多种数据库的软件则需要自定义多套sql映射文件，工作量大。

Hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件（例如需求固定的定制化软件）如果用hibernate开发可以节省很多代码，提高效率。但是Hibernate的学习门槛高，要精通门槛更高，而且怎么设计O/R映射，在性能和对象模型之间如何权衡，以及怎样用好Hibernate需要具有很强的经验和能力才行。

总之，按照用户的需求在有限的资源环境下只要能做出维护性、扩展性良好的软件架构都是好架构，所以框架只有适合才是最好。