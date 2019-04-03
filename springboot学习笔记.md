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