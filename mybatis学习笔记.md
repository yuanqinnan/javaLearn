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

### 二、Mapper动态代理方式

#### 2.1 引言

通过上一篇mybatis的入门学习，我们已经会使用mybatis实现简单的增删改查，但是我们也发现了用原始Dao开发的一些问题：

1. Dao方法体存在重复代码：通过SqlSessionFactory创建SqlSession，调用SqlSession的数据库操作方法
2. 调用sqlSession的数据库操作方法需要指定statement的id，这里存在硬编码，不得于开发维护。

为了解决这些问题，我们采用**Mapper动态代理方法**来进行开发：程序员编写Mapper接口（相当于Dao接口），由Mybatis框架根据接口定义创建接口的动态代理对象，代理对象的方法体同上边Dao接口实现类方法。

#### 2.2 **开发规范**

Mapper接口开发需要遵循以下规范：

1、 Mapper.xml文件中的namespace与mapper接口的类路径相同。

2、 Mapper接口方法名和Mapper.xml中定义的每个statement的id相同 

3、 Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同

4、Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同

#### 2.3 改造

**第一步：**Mapper.xml(映射文件)

定义mapper映射文件UserMapper.xml，将UserMapper.xml放在config下mapper目录下，效果如下：

![1552791556999](img\mp.png)

文件内容如下：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace：命名空间，用于隔离sql，还有一个很重要的作用，后面会讲 -->
<mapper namespace="com.yuanqinnan.mapper.UserMapper">
    <select id="queryUserById" parameterType="int" resultType="com.yuanqinnan.model.User">
    SELECT * FROM `user`where id=#{id}
   </select>
    <!-- 查询 user 表的所有数据-->
    <select id="selectUserAll" resultType="com.yuanqinnan.model.User">
        select * from user
    </select>
    <!--
            1、${value}里面必须要写value，不然会报错
            2、${}表示拼接 sql 字符串，将接收到的参数不加任何修饰拼接在sql语句中
            3、使用${}会造成 sql 注入
    -->
    <select id="selectLikeUserName" resultType="com.yuanqinnan.model.User" parameterType="String">
        select * from user where username like '%${value}%'
    </select>
    <!--#{}实现-->
    <select id="selectLikeUserName2" resultType="com.yuanqinnan.model.User" parameterType="String">
        select * from user where username like #{username}
    </select>
    <!-- 向 user 表插入一条数据 -->
    <insert id="insertUser" parameterType="com.yuanqinnan.model.User">
        insert into user(id,username,sex,birthday,address)
            value(#{id},#{username},#{sex},#{birthday},#{address})
    </insert>
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
    <!-- 根据 id 更新 user 表的数据 -->
    <update id="updateUserById" parameterType="com.yuanqinnan.model.User">
        update user set username=#{username} where id=#{id}
    </update>

    <!-- 根据 id 删除 user 表的数据 -->
    <delete id="deleteUserById" parameterType="int">
        delete from user where id=#{id}
    </delete>
</mapper>
```

其他地方未有改动，主要是namespace="com.yuanqinnan.mapper.UserMapper"的修改，现在我们实现这个接口

**第二步：**UserMapper(接口文件)

新建mapper包，新增接口UserMapper

![1552791940384](img/1552791940384-1552791942462.png)

内容：

```java
public interface UserMapper {

    //查询用户
    User queryUserById(int id);
    //查询用户列表
    List<User> selectUserAll();

    //模糊查询
    List<User> selectLikeUserName(String username);

    //新增
    void saveUser(User user);
    
}
```

**第三步：**加载UserMapper.xml文件

```xml
<mappers>
    <!-- 映射文件方式1，一个一个的配置-->
    <mapper resource="config/sqlmap/User.xml"/>
    <mapper resource="config/mapper/UserMapper.xml"/>
</mappers>
```

测试：

```java
public class MapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws Exception {
        // 创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 加载SqlMapConfig.xml配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
        // 创建SqlsessionFactory
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void testQueryUserById() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 执行查询方法
        User user = userMapper.queryUserById(1);
        System.out.println(user);

        // 和spring整合后由spring管理
        sqlSession.close();
    }

    @Test
    public void testQueryUserByUsername() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 执行查询方法
        List<User> list = userMapper.selectLikeUserName("张");
        for (User user : list) {
            System.out.println(user);
        }

        // 和spring整合后由spring管理
        sqlSession.close();
    }

    @Test
    public void testSaveUser() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 创建保存对象
        User user = new User();
        user.setUsername("刘备");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("蜀国");
        // 执行查询方法
        userMapper.saveUser(user);
        System.out.println(user);


        // 和spring整合后由spring管理
        sqlSession.commit();
        sqlSession.close();
    }
}
```

测试结果与上一篇相同

#### 2.4 总结

selectOne和selectList

动态代理对象调用sqlSession.selectOne()和sqlSession.selectList()是根据mapper接口方法的返回值决定，如果返回list则调用selectList方法，如果返回单个对象则调用selectOne方法。

 namespace

mybatis官方推荐使用mapper代理方法开发mapper接口，程序员不用编写mapper接口实现类，使用mapper代理方法时，输入参数可以使用pojo包装对象或map对象，保证dao的通用性。

### 三、SqlMapConfig.xml全局配置文件解析

经过前两篇的总结，已经基本掌握了mybatis的开发模式，这篇主要是总结SqlMapConfig.xml文件的配置

SqlMapConfig.xml中配置的内容和顺序如下：

| 配置内容       | 作用                  |
| -------------- | --------------------- |
| **properties** | 用来加载属性文件      |
| settings       | 用来设置全局参数      |
| typeAliases    | 用来设置类型的别名    |
| typeHandlers   | 用来设置类型处理器    |
| objectFactory  | 用来设置对象工厂      |
| plugins        | 用来设置插件          |
| environments   | 用来设置mybatis的环境 |
| mappers        | 用来配置映射文件      |

下面依次来介绍各个配置

#### 3.1 properties（属性）

SqlMapConfig.xml可以引用java属性文件中的配置信息，如数据库配置，在config下新建db.properties如图：

![1553092550880](img/1553092550880.png)

内容如下：

```xml
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8
jdbc.username=root
jdbc.password=123456
```

SqlMapConfig,xml 中修改：

```xml
<properties resource="config/db.properties">
        <!-- properties中还可以配置一些属性名和属性值,此处的优先加载
        <property name="" value=""/>
        <property name="jdbc.username" value=""/>
        <property name="jdbc.password" value=""/>
    -->
    </properties>

    <!-- 和spring整合后 environments配置将废除 -->
    <environments default="development">
        <environment id="development">
            <!-- 使用jdbc事务管理 -->
            <transactionManager type="JDBC" />
            <!-- 数据库连接池 -->
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}" />
                <property name="url"
                          value="${jdbc.url}" />
                <property name="username" value="${jdbc.username}" />
                <property name="password" value="${jdbc.password}" />
            </dataSource>
        </environment>
    </environments>
```

注意： MyBatis 将按照下面的顺序来加载属性：在 properties 元素体内定义的属性首先被读取。 然后会读取properties 元素中resource或 url 加载的属性，它会覆盖已读取的同名属性

#### 3.2 settings全局参数设置

mybatis框架运行时可以调整一些运行参数。比如，开启二级缓存，开启延迟加载等等。全局参数会影响mybatis的运行行为

| setting(设置)             | Description(描述)                                            | valid　Values(验证值组)                                      | Default(默认值)                                              |
| ------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| cacheEnabled              | 在全局范围内启用或禁用缓存配置 任何映射器在此配置下。        | true \| false                                                | TRUE                                                         |
| lazyLoadingEnabled        | 在全局范围内启用或禁用延迟加载。禁用时，所有相关联的将热加载。 | true \| false                                                | TRUE                                                         |
| aggressiveLazyLoading     | 启用时，有延迟加载属性的对象将被完全加载后调用懒惰的任何属性。否则，每一个属性是按需加载。 | true \| false                                                | TRUE                                                         |
| multipleResultSetsEnabled | 允许或不允许从一个单独的语句（需要兼容的驱动程序）要返回多个结果集。 | true \| false                                                | TRUE                                                         |
| useColumnLabel            | 使用列标签，而不是列名。在这方面，不同的驱动有不同的行为。参考驱动文档或测试两种方法来决定你的驱动程序的行为如何。 | true \| false                                                | TRUE                                                         |
| useGeneratedKeys          | 允许JDBC支持生成的密钥。兼容的驱动程序是必需的。此设置强制生成的键被使用，如果设置为true，一些驱动会不兼容性，但仍然可以工作。 | true \| false                                                | FALSE                                                        |
| autoMappingBehavior       | 指定MyBatis的应如何自动映射列到字段/属性。NONE自动映射。 PARTIAL只会自动映射结果没有嵌套结果映射定义里面。 FULL会自动映射的结果映射任何复杂的（包含嵌套或其他）。 | NONE,PARTIAL,FULL                                            | PARTIAL                                                      |
| defaultExecutorType       | 配置默认执行人。SIMPLE执行人确实没有什么特别的。 REUSE执行器重用准备好的语句。 BATCH执行器重用语句和批处理更新。 | SIMPLE,REUSE,BATCH                                           | SIMPLE                                                       |
| safeRowBoundsEnabled      | 允许使用嵌套的语句RowBounds。                                | true \| false                                                | FALSE                                                        |
| mapUnderscoreToCamelCase  | 从经典的数据库列名A_COLUMN启用自动映射到骆驼标识的经典的Java属性名aColumn。 | true \| false                                                | FALSE                                                        |
| localCacheScope           | MyBatis的使用本地缓存，以防止循环引用，并加快反复嵌套查询。默认情况下（SESSION）会话期间执行的所有查询缓存。如果localCacheScope=STATMENT本地会话将被用于语句的执行，只是没有将数据共享之间的两个不同的调用相同的SqlSession。 | SESSIONSTATEMENT                                             | SESSION                                                      |
| dbcTypeForNull            | 指定为空值时，没有特定的JDBC类型的参数的JDBC类型。有些驱动需要指定列的JDBC类型，但其他像NULL，VARCHAR或OTHER的工作与通用值。 | JdbcType enumeration. Most common are: NULL, VARCHAR and OTHER | OTHER                                                        |
| lazyLoadTriggerMethods    | 指定触发延迟加载的对象的方法。                               | A method name list separated by commas                       | equals,clone,hashCode,toString                               |
| defaultScriptingLanguage  | 指定所使用的语言默认为动态SQL生成。                          | A type alias or fully qualified class name.                  | org.apache.ibatis.scripting.xmltags.XMLDynamicLanguageDriver |
| callSettersOnNulls        | 指定如果setter方法或地图的put方法时，将调用检索到的值是null。它是有用的，当你依靠Map.keySet（）或null初始化。注意原语（如整型，布尔等）不会被设置为null。 | true \| false                                                | FALSE                                                        |
| logPrefix                 | 指定的前缀字串，MyBatis将会增加记录器的名称。                | Any String                                                   | Not set                                                      |
| logImpl                   | 指定MyBatis的日志实现使用。如果此设置是不存在的记录的实施将自动查找。 | SLF4J \| LOG4J \| LOG4J2 \| JDK_LOGGING \| COMMONS_LOGGING \| STDOUT_LOGGING \| NO_LOGGING | Not set                                                      |
| proxyFactory              | 指定代理工具，MyBatis将会使用创建懒加载能力的对象。          | CGLIB \| JAVASSIST                                           | CGLIB                                                        |

默认的配置如下：

```x&#39;m
    <settings>
        <setting name="cacheEnabled" value="true"/>
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="multipleResultSetsEnabled" value="true"/>
        <setting name="useColumnLabel" value="true"/>
        <setting name="useGeneratedKeys" value="false"/>
        <setting name="autoMappingBehavior" value="PARTIAL"/>
        <setting name="defaultExecutorType" value="SIMPLE"/>
        <setting name="defaultStatementTimeout" value="25"/>
        <setting name="safeRowBoundsEnabled" value="false"/>
        <setting name="mapUnderscoreToCamelCase" value="false"/>
        <setting name="localCacheScope" value="SESSION"/>
        <setting name="jdbcTypeForNull" value="OTHER"/>
        <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
    </settings>
```

#### 3.3 typeAliases(类型别名)

在mapper.xml中，定义很多的statement，statement需要parameterType指定输入参数的类型、需要resultType指定输出结果的映射类型。如果在指定类型时输入类型全路径，有时候会很长，不方便进行开发，那么我们就可以针对parameterType或resultType指定的类型定义一些别名，在mapper.xml中通过别名`<typeAliases>来`定义，方便开发，下面是默认的别名：

| **别名**   | **映射的类型** |
| ---------- | -------------- |
| _byte      | byte           |
| _long      | long           |
| _short     | short          |
| _int       | int            |
| _integer   | int            |
| _double    | double         |
| _float     | float          |
| _boolean   | boolean        |
| string     | String         |
| byte       | Byte           |
| long       | Long           |
| short      | Short          |
| int        | Integer        |
| integer    | Integer        |
| double     | Double         |
| float      | Float          |
| boolean    | Boolean        |
| date       | Date           |
| decimal    | BigDecimal     |
| bigdecimal | BigDecimal     |

除了这些默认别名，我们还可以自己定义别名

```xml
<typeAliases>
    <!-- 单个别名定义 -->
    <typeAlias alias="user" type="com.yuanqinnan.model.User" />
    <!-- 批量别名定义，扫描整个包下的类，别名为类名（大小写不敏感） -->
    <package name="com.yuanqinnan.model" />
    <package name="其它包" />
</typeAliases>
```

#### 3.4 mappers（映射器）

Mapper配置的几种方法：

**通过resource加载单个映射文件**

```xml
<mappers>
    <!-- 映射文件方式1，一个一个的配置-->
    <mapper resource="config/sqlmap/User.xml"/>
    <mapper resource="config/mapper/UserMapper.xml"/>
</mappers>
```

**使用mapper接口类路径**

```xml
  <!-- 通过mapper接口加载单个映射配置文件遵循一定的规范：需要将mapper接口类名和mapper.xml映射文件名称保持一致，且在一个目录中；
上边规范的前提是：使用的是mapper代理方法; -->
  <mapper class="com.yuanqinnan.mapper.UserMapper"/>
```

**批量加载mapper（推荐使用)**

```xml
<!--此种方法要求mapper接口名称和mapper映射文件名称相同，且放在同一个目录中-->
<mapper class="com.yuanqinnan.mapper"/>
```

### 四、输入输出映射

在日常开发用到mybatis时，因为实际的开发业务场景很复杂，不论是输入的查询条件，还是返回的结果，经常是需要根据业务来定制，这个时候我们就需要自己来定义一些输入和输出映射

#### 4.1 输入映射

 输入映射是在映射文件中通过parameterType指定输入参数的类型，类型可以是简单类型、hashmap、pojo的包装类型，当我们去查询用户时，有些字段基本不会用作查询条件，还有一些时候我们需要连表查询，那么这个时候我们可以用到包装类。

新建pojo包，定义包装类：

```java
public class QueryVo {
    //pojo
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
```

将UserMapper.xml文件移至com.yuanqinnan.mapper包中，并增加一个查询方法

```xml
<select id="queryByQo" parameterType="com.yuanqinnan.pojo.QueryVo" resultType="com.yuanqinnan.model.User">
    SELECT * from user  where username like '%${user.username}%'
</select>
```

UserMapper中增加接口：

```java
List<User> queryByQo(QueryVo queryVo);
```

结果如图：

![1553268644089](img/1553268644089.png)

将SqlMapConfig.xml 中其他的配置恢复原先配置，引入mapper方式进行修改

```xml
<mappers>
    <package name="com.yuanqinnan.mapper"/>
</mappers>
```

增加测试方法：

```java
@Test
public void testQueryUserByUsername2() {
    // 获取sqlSession，和spring整合后由spring管理
    SqlSession sqlSession = this.sqlSessionFactory.openSession();

    // 从sqlSession中获取Mapper接口的代理对象
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    // 执行查询方法
    QueryVo queryVo=new QueryVo();
    User user=new User();
    user.setUsername("张");
    queryVo.setUser(user);
    List<User> list = userMapper.queryByQo(queryVo);
    for (User user2 : list) {
        System.out.println(user2);
    }

    // 和spring整合后由spring管理
    sqlSession.close();
}
```

原以为会很顺利的出现结果，结果一直报错：**invalid bound statement (not found)**，这个错误是找不到相应sql,可是明明路径和sql都是对的，最后竟然发现是需要在pom.xml文件中配置resource，不然mapper.xml文件就会被漏掉，这种错误真是太恼火了，加上配置

```xml
<build>
  <resources>
    <resource>
      <directory>src/main/java</directory>
      <includes>
        <include>**/*.properties</include>
        <include>**/*.xml</include>
      </includes>
      <filtering>false</filtering>
    </resource>
  </resources>
</build>
```

得到测试结果：

![1553269096014](img/1553269096014.png)

输入映射比较简单，一般也不会使用包装类，而是根据需要的条件去设置字段比较好

#### 4.2  resultType(输出类型)

输出类型有简单类型，pojo类，pojo列表，pojol类和列表在前面的例子中都有演示，下面看一个简单类型的

新增方法:

```xml
<select id="queryUserCount" resultType="int">
    select count(*) from user
</select>
```

UserMapper中增加接口：

```java
int queryUserCount();
```

测试：

