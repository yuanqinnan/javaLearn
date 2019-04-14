---
typora-root-url: img
typora-copy-images-to: img
---

## springboot学习笔记

### 一、springboot初识

#### 1.1 springboot是什么？

微服务，应该是近年来最火的概念，越来越多的公司开始使用微服务架构，面试中被问到的微服务的概率很高，不管对技术的追求，还是为了进更好的公司，微服务都是我们开发人员的必须要学习的知识。

那么微服务究竟是什么呢？

我们通俗的理解方式就是：微服务化的核心就是将传统的一站式应用，根据业务拆分成一个一个的服务，彻底
地去耦合,每一个微服务提供单个业务功能的服务，一个服务做一件事，从技术角度看就是一种小而独立的处理过程，类似进程概念，能够自行单独启动或销毁，拥有自己独立的数据库。

想要更好的了解微服务概念的同学可以去看看这篇论文：<https://martinfowler.com/articles/microservices.html>

**dubbo和springcloud**

目前比较成熟的微服务架构有两种：dubbo和springcloud，这两种技术各有优劣，他们最大的区别是：

**SpringCloud抛弃了Dubbo的RPC通信，采用的是基于HTTP的REST方式**

为何会选择springcloud，主要是从两个方面考虑：

1. spring全家桶：作为Spring的拳头项目，springcloud能够与Spring Framework、Spring Boot、Spring Data、Spring Batch等其他Spring项目完美融合，而且springcloud有一整套的微服务架构解决方案，你可以很放心的使用，大家都爱全家桶。
2. 社区支持与更新力度：DUBBO停止了5年左右的更新，虽然2017.7重启了，但是影响还是有一些的。

当然，这只是一种技术选择，不需要去争论。

讲了这么多，还没有说到主题，那springboot又是什么？**SpringBoot专注于快速方便的开发单个个体微服务**

SpringBoot可以离开SpringCloud独立使用开发项目，但是SpringCloud离不开SpringBoot，属于依赖的关系.

SpringBoot专注于快速、方便的开发单个微服务个体，SpringCloud关注全局的服务治理框架。

要学习springcloud，就必须先学习springboot

#### 1.2 springboot的优势

Spring Boot是来简化Spring应用开发，约定大于配置，去繁从简，just run就能创建一个独立的，产品级别的应用，多数Spring Boot应用需要很少的Spring配置。

用过spring开发就知道，spring配置繁多、开发效率低下、部署流程复杂、且第三方技术集成难度大。而使用springboot有如下优势：

– 快速创建独立运行的Spring项目以及与主流框架集成
– 使用嵌入式的Servlet容器，应用无需打成WAR包
– starters自动依赖与版本控制
– 大量的自动配置，简化开发，也可修改默认值
– 无需配置XML，无代码生成，开箱即用
– 准生产环境的运行时应用监控
– 与云计算的天然集成

学习spring是有一定前提的，spring，maven都必须先掌握

#### 1.3 hello world

相信大部分开发现在都是使用idea，关于idea的maven配置就不讲了，我们开始第一个springboot项目

新建项目：

![](/1553527065526.png)

下一步

![](/1553527366199.png)

下一步：

![](/1553527445312.png)

删除一些不必要的文件，最后结构如下：

![](/1553527480321.png)

新建一个controller

![1553528010467](/1553528010467.png)

代码：

```java
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
```

启动项目：![1553528305816](/1553528305816.png)

浏览器中输入：<http://localhost:8080/hello>

![1553528357008](/1553528357008.png)

结果就出来了，简直太方便了，springboot不仅开发简单，部署也非常容易,直接打包

![1553528808685](/1553528808685.png)

打包后的文件：

![1553528854416](/1553528854416.png)

复制到桌面，然后直接执行命令 java -jar

![1553528781087](/1553528781087.png)

![1553528941268](/1553528941268.png)

刷新刚刚的地址，成功显示，就已经部署完成

#### 1.4 原理探究

##### 1.4.1POM

我们来稍微理解下springboot的原理，首先看pom包

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.3.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

进入依赖，发现他还有一个父依赖

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.1.3.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
</parent>
```

在父依赖中我们可以看到里面对组件的版本号进行了写入

![1553609993949](/1553609993949.png)

我们默认是不需要写版本的，当然，没有在里面的依赖需要我们定义，这就是springboot的版本仲裁中心

再看另外的一个依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

这个依赖可以分为两个部门：spring-boot-starter：spring-boot场景启动器；后面的web会帮我们导入了web模块正常运行所依赖的组件，如：

![1553610688592](/1553610688592.png)



Spring Boot将所有的功能场景都抽取出来，做成一个个的starters（启动器），只需要在项目里面引入这些starter相关场景的所有依赖都会导入进来。要用什么功能就导入什么场景的启动器

##### 1.4.2 主程序类，主入口类

```java
@SpringBootApplication //来标注一个主程序类，说明这是一个Spring Boot应用
public class SpringbootLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLearnApplication.class, args);
    }

}
```

@**SpringBootApplication**:    Spring Boot应用标注在某个类上说明这个类是SpringBoot的主配置类，SpringBoot就应该运行这个类的main方法来启动SpringBoot应用；

进入其中

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
      @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
      @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
```

这里面有个@**SpringBootConfiguration**: 翻译过来是Spring Boot的配置类；标注在某个类上，表示这是一个Spring Boot的配置类；

我们在进入其中，又可以看到一个注解@**Configuration**:配置类上来标注这个注解；这个注解说明这是一个配置类，相当于一个配置文件，配置类也是容器中的一个组件；@Component。

还有一个@**EnableAutoConfiguration** 这个注解说明开启自动配置功能。这就是为什么我们不需要写任何配置，就可以起项目的原因，以前我们需要配置的东西，Spring Boot帮我们自动配置；@**EnableAutoConfiguration**告诉SpringBoot开启自动配置功能；这样自动配置才能生效。

我们不妨再进入到这个注解中：

```java
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
```

这里面@**AutoConfigurationPackage** ，翻译过来就是自动配置包，我们再到里面去看看，又有一个注解

```java
@Import({Registrar.class})
public @interface AutoConfigurationPackage {
}
```

这个注解我们就比较熟悉了，@**Import** 这个注解是给容器中导入一个组件；导入的组件由Registrar.class，这个		Registrar.class中的方法的就是将所在包及下面所有子包里面的所有组件扫描到Spring容器。

这个要注意必须是其子包，如果我们放在外面将不能被扫描，用个例子说明，假设我们在java路径下增加一个类

![1553612511042](/1553612511042.png)

```java
@Controller
public class HelloWorld {
    @ResponseBody
    @RequestMapping("/world")
    public String world(){
        return "hello world";
    }
}
```

再次启动，是找不到这个地址的，因为这个包没有扫入其中，这样我们就明白了包是怎么扫描进去的。

下面我们再到@**EnableAutoConfiguration** 找到@Import({AutoConfigurationImportSelector.class})，

**EnableAutoConfigurationImportSelector** 翻译过来是导入哪些组件的选择器，这个类的作用是：将所有需要导入的组件以全类名的方式返回，这些组件就会被添加到容器中。

```java
public String[] selectImports(AnnotationMetadata annotationMetadata) {
```

这个方法会给容器中导入非常多的自动配置类（xxxAutoConfiguration），就是给容器中导入这个场景需要的所有组件，并配置好这些组件。

这样我们就知道自动配置类是如何导入的，有了自动配置类，免去了我们手动编写配置注入功能组件等的工作。

![1553613542543](/1553613542543.png)

去看这个文件，发现里面有大量的注解配置类

![1553613667363](/1553613667363.png)	

Spring Boot在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作；==以前我们需要自己配置的东西，自动配置类都帮我们。

这样我们就对springboot的原理有个大致的了解，最重要的是springboot将我们之前要做的很多事情都做好了。

### 二、springboot配置

#### 2.1 引言

虽然springboot帮我们进行了自动配置，但配置还是不可避免的，比如最简单的端口号，数据库连接。但springboot的配置一般不用xml进行配置，而是yml和properties，选择这两种方式当然是因为他们都有自己的优势。

YAML（YAML Ain't Markup Language）,ymal是以**数据为中心**的，所有比xml和json更适合做配置文件，我们看一个简单例子：配端口号

yml的写法是：

```yaml
server:
  port: 8089
```

xml的写法是：

```xml
<server>
	<port>8081</port>
</server>
```

直观上我们就能看到yml的写法更简练，xml用了很大的空间放在标签的开闭上，而yml不需要，下面我们来细讲yml的语法。

#### 2.2 YAML语法

yml的基本语法是 **k:(空格)v：**表示一对键值对（空格必须有），以**空格**的缩进来控制层级关系，只要是左对齐的一列数据，都是同一个层级的。且属性和值都是大小写敏感的。如：

```yaml
server:
  port: 8081
  path: /hello
```

我们重点看看值的写法，值的写法是指可以用哪些写法来写值，一般有：**字面量，对象、Map，数组**。

**字面量**：就是指普通的值（数字，字符串，布尔），这个写法就是直接k: v：方式，字符串默认不用加上单引号或者双引号，如果加了，那双引号和单引号是有区别的，区别在于是不是转义特殊字符，双引号会转义特殊字符，而单引号不会。如：

```
name: "yuanqinnan \n yuanyuan" 输出；yuanqinnan 换行  yuanyuan
name: ‘yuanqinnan \n yuanyuan’：输出；yuanqinnan \n  yuanyuan
```

**对象、Map**的写法也是普通的k: v 写法，在下一行来写对象的属性和值的关系。如

```yaml
friends:
  lastName: zhangsan
  age: 20
```

还可以在一行完成：

```yaml
friends: {lastName: zhangsan,age: 18}
```

**数组（List、Set）**

用- 值表示数组中的一个元素

```yaml
pets:
 - cat
 - dog
 - pig
```

记得- 后要打空格，这也有一行写法

```yaml
pets: [cat,dog,pig]
```

配置文件写完，那么我们就要来使用了，下面讲配置文件值注入。

第一种方式@**ConfigurationProperties**

新建一个person类

```java
/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *      prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
 *
 */
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person  {
    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
}
```

这里为了少些一些代码，引入lombok，增加依赖，这个不清楚的同学自行百度了。

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.2</version>
</dependency>
```

再建一个dog类

```java
@Data
public class Dog {
    private String name;
    private Integer age;
}
```

然后在配置文件中加入以下配置，

```yaml
server:
  port: 8089
person:
    lastName: hello
    age: 18
    boss: false
    birth: 2017/12/12
    maps: {k1: v1,k2: 12}
    lists:
      - lisi
      - zhaoliu
    dog:
      name: 小狗
      age: 12
```

这里基本包含了我们需要的各种类型，直接在SpringbootLearnApplicationTests测试，

![1553700240754](/1553700240754.png)

测试结果：![1553700265487](/1553700265487.png)

下面再看另一种注入方式**@Value** ,@Value支持字面量，${key}从环境变量、配置文件中获取值,支持#{Spel}表达式,怎么写呢？例子：

```java
//支持${}
@Value("${person.lastName}")
private String lastName;
//支持#{SpEL}
@Value("#{10*2}")
private Integer age;
//支持字面量
@Value("true")
```

两者的区别：

|                      | @ConfigurationProperties | @Value     |
| -------------------- | ------------------------ | ---------- |
| 功能                 | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定（松散语法） | 支持                     | 不支持     |
| SpEL                 | 不支持                   | 支持       |
| JSR303数据校验       | 支持                     | 不支持     |
| 复杂类型封装         | 支持                     | 不支持     |

**@ConfigurationProperties**默认从主配置文件中获取值，如果需要专门的配置文件则需要使用@**PropertySource** ，写法如下：

```java
@PropertySource(value = {"XXX"})
```

怎么选择：

如果说，我们只是在某个业务逻辑中需要获取一下配置文件中的某项值，使用@Value。

如果说，我们专门编写了一个javaBean来和配置文件进行映射，我们就直接使用@ConfigurationProperties。

另外Spring Boot里面没有Spring的配置文件，我们自己编写的配置文件，也不能自动识别，什么意思呢？

如我们新建一个xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<bean id="helloService" class="com.yuanqinnan.service.HelloService"></bean>
</beans>
```

按照路径添加HelloService类，然后我们直接去获取bean,看看能不能取出来

测试方法：

```java
@Autowired
ApplicationContext ioc;
@Test
public void beanTest(){
    boolean b = ioc.containsBean("helloService");
    System.out.println(b);
}
```

结果是false,说明没有注入成功，这个时候我们需要使用@**ImportResource**在启动类上加上

```java
@ImportResource(locations = {"classpath:beans.xml"})
```

这样才能注入进来，因为springboot是不推荐使用xml文件而使用注解方式：

```java
@Configuration
public class MyAppConfig {

    //将方法的返回值添加到容器中；容器中这个组件默认的id就是方法名
    @Bean
    public HelloService helloService2(){
        System.out.println("配置类@Bean给容器中添加组件了...");
        return new HelloService();
    }
}
```

这样helloService2就注入进来了

```java
@Test
public void beanTest2(){
    boolean b = ioc.containsBean("helloService2");
    System.out.println(b);
}
```

测试结果为true

**2.3 多个文件**

在实际开发中，我们可能会有多个配置文件，比如本地的配置，测试的配置，正式的配置，这个时候我们就可以使用，一般我们会命名为：application-{profile}.properties/yml，那我们新建2个配置文件

![1553783481830](/1553783481830.png)

然后在application.yml加上

```yaml
spring:
  profiles:
    active: dev
```

这样我们可以方便的切换环境配置，那上线的时候我们想改动配置文件怎么办呢？带参数命令就可以实现

java -jar springboot-learn-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev；

我们可以用多个文件来进行配置，那么就会有优先级的问题，springboot是怎么做的呢？

springboot 启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置文件，并按照以下顺序进行扫描：

1、根目录下:./config/

2、根目录下:./

3、类路径下:/config/

4、类路径下classpath:/

优先级由高到底，如果后面的文件有相同配置则不起作用，这些配置文件都是互补配置，我们可以将需要的配置文件分类进行写入。

这里我们想想为啥springboot能直接启动呢，是因为spingboot有很多自动配置类，并且为这些自动配置类提供了默认的properties，如数据库连接配置，

![1553785201088](/1553785201088.png)

我们根据这个properties，再进行相应的属性配置：

![1553785327132](/1553785327132.png)

**1.3 总结**

SpringBoot启动会加载大量的自动配置类，我们看我们需要的功能有没有SpringBoot默认写好的自动配置类，我们再来看这个自动配置类中到底配置了哪些组件，（只要我们要用的组件有，我们就不需要再来配置了）给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们就可以在配置文件中指定这些属性的值。

### 三、日志

#### 3.1 引言

日志对于一个系统的重要性不言而喻，日志能帮我们快速定位线上问题，市场上存在非常多的日志框架，比较常见的有 JUL，JCL，Log4j，Log4j2，Logback、SLF4j、jboss-logging等。

spring-boot-starter-logging采用了slf4j+logback的形式，SLF4j（Simple Logging Facade for Java）是日志门面（日志抽象接口）,logback是日志实现。我们开发的时候，日志记录方法的调用，不应该来直接调用日志的实现类，而是调用日志抽象层里面的方法。

这里我们想到一个问题，我们的系统也会依赖其他框架，比如Spring、Hibernate, 这些框架本身也存在自己的日志框架，但我们需要做到使用slf4j进行输出，这个可以通过适配器模式来实现的，首先我们排除原先框架使用的日志，然后用中间包来替换原有的日志框架，这个中间包去实现原先日志框架的API，这样我们只需要导入slf4j转换包的依赖就可以解决。如图

![1554299003853](/1554299003853.png)

#### 3.2 日志使用

日志的使用非常简单，只要用LogFactory.getLog(getClass()) 获取日志，然后记录信息。

```java
Log log = LogFactory.getLog(getClass());
log.trace("这是trace日志");
log.debug("这是debug日志");
log.info("这是info日志");
log.warn("这是warn日志");
log.error("这是error日志");
```

日志级别由低到高，并且可以调整日志级别，日志就只会在这个级别以上（包括自己）的日志生效。springboot的默认级别是info, 我们可以针对包进行调整

```java
logging.level.com.yuanqinnan=trace
```

除了级别配置外，还有两个比较重要的配置，**路径**和**格式**

路径有两个配置方式：

```properties
#当前项目下生成springlog.log
#logging.file=springboot.log
#完整的路径名称
logging.file=G:/springboot.log
#创建路径，由springboot生成默认的文件
logging.path=G:/springlog
```

这两个是冲突配置，两个都写的话以logging.file为主。

我们可以对日志的输出进行格式配置：

```yaml
#在控制台输出的日志的格式
logging.pattern.console=%d{yyyy-MM-dd} [%thread] %-5level %logger{50} - %msg%n
# 指定文件中日志输出的格式
logging.pattern.file=%d{yyyy-MM-dd} === [%thread] === %-5level === %logger{50} ==== %msg%n
```

具体参数：

```yaml
	%d表示日期时间，
	%thread表示线程名，
	%-5level：级别从左显示5个字符宽度
	%logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
	%msg：日志消息，
	%n是换行符
```
springboot的日志都有自己的默认文件，如果需要用自己的日志配置，只要增加一个相同文件，那么就会使用我们自己配置的文件

| Logging System          | Customization                                                |
| ----------------------- | ------------------------------------------------------------ |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml` or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`                          |
| JDK (Java Util Logging) | `logging.properties`                                         |

springboot推荐使用`logback-spring.xml`来进行配置。

SpringBoot的有高级Profile功能，如

```yaml
<springProfile name="staging">
    <!-- configuration to be enabled when the "staging" profile is active -->
  	可以指定某段配置只在某个环境下生效
</springProfile>
```

日志的基本内容就是这些，另附上日志的一些常用配置供参考 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
scan：当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
scanPeriod：设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒当scan为true时，此属性生效。默认的时间间隔为1分钟。
debug：当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
-->
<configuration scan="false" scanPeriod="60 seconds" debug="false">
    <!-- 定义日志的根目录 -->
    <property name="LOG_HOME" value="/app/log" />
    <!-- 定义日志文件名称 -->
    <property name="appName" value="appName"></property>
    <!-- ch.qos.logback.core.ConsoleAppender 表示控制台输出 -->
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!--
        日志输出格式：
			%d表示日期时间，
			%thread表示线程名，
			%-5level：级别从左显示5个字符宽度
			%logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
			%msg：日志消息，
			%n是换行符
        -->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </layout>
    </appender>

    <!-- 滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将日志记录到其他文件 -->  
    <appender name="appLogAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 指定日志文件的名称 -->
        <file>${LOG_HOME}/${appName}.log</file>
        <!--
        当发生滚动时，决定 RollingFileAppender 的行为，涉及文件移动和重命名
        TimeBasedRollingPolicy： 最常用的滚动策略，它根据时间来制定滚动策略，既负责滚动也负责出发滚动。
        -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--
            滚动时产生的文件的存放位置及文件名称 %d{yyyy-MM-dd}：按天进行日志滚动 
            %i：当文件大小超过maxFileSize时，按照i进行文件滚动
            -->
            <fileNamePattern>${LOG_HOME}/${appName}-%d{yyyy-MM-dd}-%i.log</fileNamePattern>
            <!-- 
            可选节点，控制保留的归档文件的最大数量，超出数量就删除旧文件。假设设置每天滚动，
            且maxHistory是365，则只保存最近365天的文件，删除之前的旧文件。注意，删除旧文件是，
            那些为了归档而创建的目录也会被删除。
            -->
            <MaxHistory>365</MaxHistory>
            <!-- 
            当日志文件超过maxFileSize指定的大小是，根据上面提到的%i进行日志文件滚动 注意此处配置SizeBasedTriggeringPolicy是无法实现按文件大小进行滚动的，必须配置timeBasedFileNamingAndTriggeringPolicy
            -->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>100MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <!-- 日志输出格式： -->     
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [ %thread ] - [ %-5level ] [ %logger{50} : %line ] - %msg%n</pattern>
        </layout>
    </appender>

    <!-- 
		logger主要用于存放日志对象，也可以定义日志类型、级别
		name：表示匹配的logger类型前缀，也就是包的前半部分
		level：要记录的日志级别，包括 TRACE < DEBUG < INFO < WARN < ERROR
		additivity：作用在于children-logger是否使用 rootLogger配置的appender进行输出，
		false：表示只用当前logger的appender-ref，true：
		表示当前logger的appender-ref和rootLogger的appender-ref都有效
    -->
    <!-- hibernate logger -->
    <logger name="com.yuanqinnan" level="debug" />
    <!-- Spring framework logger -->
    <logger name="org.springframework" level="debug" additivity="false"></logger>



    <!-- 
    root与logger是父子关系，没有特别定义则默认为root，任何一个类只会和一个logger对应，
    要么是定义的logger，要么是root，判断的关键在于找到这个logger，然后判断这个logger的appender和level。 
    -->
    <root level="info">
        <appender-ref ref="stdout" />
        <appender-ref ref="appLogAppender" />
    </root>
</configuration> 
```

### 四、web开发

#### 4.1 引言

有了自动配置，springboot使web开发变得简单，这个在springboot之旅中的第一篇中就有体现，实际的开发中当然不会这么简单，很多时候我们都需要自己去定制一些东西。web开发的东西比较多， 我们先掌握一些必要知识点，剩下的就是CRUD开发。

快速的创建一个springboot web项目在第一篇总结中有讲：<https://www.cnblogs.com/yuanqinnan/p/10604761.html>

#### 4.2 静态资源的映射规则

现在大部分公司都是前后端分离的开发模式，一般作为后台开发不用关心前端，只需要提供相应接口，但是有关前端的知识我们最好还是能基本掌握一些。我们先了一套bootstrap框架，然后开始进行开发。

在之前的web开发中，在main目录下面会有webapp文件夹，我们将所有的静态资源放在里面，但是springboot的默认生成中并没有这个文件夹，那么springboot是怎么映射静态资源。

ctrl+N快捷键，找到**WebMvcAutoConfiguration**类，再找到里面的addResourceHandlers 方法

```java
public void addResourceHandlers(ResourceHandlerRegistry registry) {
   if (!this.resourceProperties.isAddMappings()) {
      logger.debug("Default resource handling disabled");
      return;
   }
   Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
   CacheControl cacheControl = this.resourceProperties.getCache()
         .getCachecontrol().toHttpCacheControl();
   //webjar形式
   if (!registry.hasMappingForPattern("/webjars/**")) {
      customizeResourceHandlerRegistration(registry
            .addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/")
            .setCachePeriod(getSeconds(cachePeriod))
            .setCacheControl(cacheControl));
   }
    //匹配/**
   String staticPathPattern = this.mvcProperties.getStaticPathPattern();
   if (!registry.hasMappingForPattern(staticPathPattern)) {
      customizeResourceHandlerRegistration(
            registry.addResourceHandler(staticPathPattern)
                  .addResourceLocations(getResourceLocations(
                      //映射的资源文件夹
                        this.resourceProperties.getStaticLocations()))
                  .setCachePeriod(getSeconds(cachePeriod))
                  .setCacheControl(cacheControl));
   }
}
```

##### 4.2.1 webjars

这里的代码告诉我们：如果是访问/webjars/**下的请求 ，都去 classpath:/META-INF/resources/webjars/ 找资源。webjars是指以jar包的方式引入静态资源。打开<https://www.webjars.org/> ，可以找到我们前端开发常用的一些组件，我们选择相应的版本，例：

```xml
<dependency>
  <groupId>org.webjars</groupId>
  <artifactId>jquery</artifactId>
  <version>3.3.1-1</version>
</dependency>
```

引入后可以看到jquer文件被引入了

![1554639624050](/1554639624050.png)

如果顺利的话，此时访问<http://localhost:8080/webjars/jquery/3.3.1-1/jquery.js>可以得到文件，结果如下：

![1554640197586](/1554640197586.png)

##### 4.2.2 自己的静态文件

另外当访问当前项目的任何资源，都去（静态资源的文件夹）找映射，资源文件夹是一个数组，包括：

"classpath:/META-INF/resources/", "classpath:/resources/","classpath:/static/", "classpath:/public/" ，

"/"：当前项目的根路径。只要将静态文件放入其中，那么springboot就能找到。

##### 4.2.3 首页

 在访问"/**",会去找静态资源文件夹下的所有index.html页面。

##### 4.2.4 图标

所有的 **/访问都是静态资源文件下找favicon.ico。

我们将一些静态文件放在static下，并将index.html放入public文件夹下，如图：

![1554648730496](/1554648730496.png)

访问<http://localhost:8080/index.html> ,可得到正确返回

![1554648795025](/1554648795025.png)

#### 4.3 模板引擎

模板引擎有很多，如JSP、Velocity、Freemarker、Thymeleaf，springboot推荐的是Thymeleaf，那我们就来简单看看Thymeleaf语法。导入starter:

```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-thymeleaf</artifactId>
	</dependency>
```
进入之后可以看到默认版本，我们也可以改成自己需要的版本。

```xml
<thymeleaf.version>3.0.9.RELEASE</thymeleaf.version>
<!-- 布局功能的支持程序  thymeleaf3主程序  layout2以上版本 -->
<!-- thymeleaf2   layout1-->
<thymeleaf-layout-dialect.version>2.2.2</thymeleaf-layout-dialect.version>
```

##### 4.3.1 Thymeleaf使用

通过源码我们知道，只要我们把HTML页面放在classpath:/templates/，thymeleaf就能自动渲染。

```java
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {
private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");
private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");
public static final String DEFAULT_PREFIX = "classpath:/templates/";
public static final String DEFAULT_SUFFIX = ".html";
```
我们可以去官网查看教程，这里只是简单的进行介绍，主要步骤

第一步：导入命名空间，导入之后会有相应提示

```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```

第二步：使用语法

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>成功！</h1>
  <!--th:text 将div里面的文本内容设置为 -->
<div><th th:text="${hello}"></th>这是显示欢迎信息</div>
</body>
</html>
```

更具体的使用方法，可以去查看官网教程，这种如果没有使用到的话不建议花太多时间去学，很多公司都是前后端分离，即使不是前后端分离，也有很多前端框架给我们使用。这些可以再我们使用的时候再去学习，速度也是很快的。

#### 4.4 SpringMVC自动配置

##### 4.4.1 自动配置

springboot默认将为我们配置如下一些SpringMvc的必要组件：

1. 必要的ViewResolver（视图解析器：根据方法的返回值得到视图对象（View）），如ContentNegotiatingViewResolver和`BeanNameViewResolver`。
2. 将必要的`Converter`, `GenericConverter`, `Formatter` 等bean注册到ioc容器中。
3. 添加了一系列的`HttpMessageConverters`以便支持对web请求和相应的类型转换。
4. 自动配置和注册`MessageCodesResolver`

任何时候，我们对默认提供的组件设定不满意，都可以注册新的同类型的bean定义来替换，web的所有自动场景都在**org.springframework.boot.autoconfigure.web**包中，我们可以参照进行配置。

当然完全靠自动配置在实际开发时不够的，我们经常需要自己配置一些东西，比如拦截器，视图映射规则。

##### 4.4.2  扩展配置

在sprinboot2.0之前 配置类继承WebMvcConfigurerAdapter，但是现在这个方法已经过时，现在可以使用两种方式，继承WebMvcConfigurer接口或者继承WebMvcConfigurationSupport类，推荐使用的是WebMvcConfigurationSupport。

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/yuan").setViewName("success");
    }
}
```

这段代码就实现了自定义的视图映射。上面这种写法使SpringMVC的自动配置和我们的扩展配置都会起作用

我们甚至可以全面接管springmvc,只要在配置类中增加@EnableWebMv注解，这样所有的SpringMVC的自动配置都失效了。当然，一般情况下我们不会这么做。

#### 4.5 登录

web系统一般少不了登录页面，我们先设定默认页面为登录页。

##### 4.5.1 登录方法

```java
registry.addViewController("/").setViewName("login");
registry.addViewController("/index.html").setViewName("login");
```

 具体登录html的代码就不贴了，可以下载源码查看，新建controller

```java
@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession httpSession){
        if(!StringUtils.isEmpty(username)&&
        "123456".equals(password)){
             //设置session
             httpSession.setAttribute("loginUser",username);
            //重定向到主页
            return "redirect:/main.html";
        }else {
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
```

##### 4.5.2 登录拦截器

登录操作完成之后，为了对每个页面进行登录验证，我们还需要设置登录拦截器。先创建登录拦截器

```java
@Configuration
public class LoginHandlerInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //未登陆，返回登陆页面
            request.setAttribute("msg","没有权限请先登陆");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            //已登陆，放行请求
            return true;
        }
    }
}
```

然后在加入配置

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;
    
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login");
    }
 }
```

这样在访问其他页面时都会进行登录拦截操作

#### 4.6 错误处理机制

在进行开发时，错误处理是非常重要的，不管是直接显示给用户，或者返回给前端，都需要尽量友好和清晰。

##### 4.6.1 默认的错误处理机制

springboot有自身的默认错误处理机制，分为两种

第一种：浏览器，浏览器会返回一个默认的错误页面，如：

![1555248704379](/1555248704379.png)

第二种：客户端，客户端默认返回的是一个响应一个json数据

如果我们用postman访问，则返回

![1555251214346](/1555251214346.png)

##### 4.6.2 定制错误响应

定制错误响应也分为两种，一种是定制错误页面，第二种是定制错误json数据

###### 4.6.2.1 定制错误页面

如果我们想要展示更加详细的信息，就将页面放在模板引擎文件夹下，路径名为 error/状态码，【将错误页面命名为错误状态码.html 放在模板引擎文件夹里面的 error文件夹下】，发生此状态码的错误就会来到 对应的页面。在这个页面我们可以获取到一些错误信息，如：

-  timestamp：时间戳
- status：状态码
- error：错误提示
- exception：异常对象
- message：异常消息
- errors：JSR303数据校验的错误都在这里

我们可以根据这些错误信息来展示错误，一般不需要这么做，抛出的错误不应该让用户去分析，我们只需要返回静态页面即可，返回错误静态页面是做法也是一样的，只是我们不用将文件放在模板引擎文件夹下

![1555250298240](/1555250298240.png)

###### 4.6.2.2 定制错误的json数据

在实际的开发中我们会对我们的错误码进行规范处理，根据错误会返回相应的错误码，所以我们会自己进行json数据包装处理。

```java
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = RequestException.class)
    public String requestExceptionHandler(RequestException e){
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码  4xx 5xx，否则就不会进入定制错误页面的解析流程
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        //转发到/error
        return "forward:/error";
    }
}
```









##### 







