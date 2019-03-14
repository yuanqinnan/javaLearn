---
typora-copy-images-to: img
---

# spring学习笔记

## 一、初识spring

### 1.1 概述

只要用框架开发java，一定躲不过spring，Spring是一个轻量级的Java开源框架，存在的目的是用于构建轻量级的J2EE应用。Spring的核心是控制反转(IOC)和面向切面编程(AOP)。spring有如下特点：

1. 轻量级：应用大小和应用开支，包括应用方式
2. DI/IoC：提供松耦合的一种实现技术
3. AOP：切面编程将业务逻辑从应用服务中分离
4. 容器：包含并管理应用对象的生命周期和配置
5. 框架：使用组件配置组合成复杂的应用，并提供很多基础功能

由于spring是一个容器型的框架，所以它不排斥其他框架，并且可以轻松的整合其他框架。

Spring以一种非侵入式的方式来管理你的代码，Spring提倡”最少侵入”，这也就意味着你可以适当的时候安装或卸载Spring 。

### 1.2 模块

Spring框架大约由20多个小模块组成，这些模块分为：核心容器，数据访问/集成，Web，面向切面编程(AOP)，消息和测试等等，如下图所示

![1547563640453](img\1547563640453.png)

下面逐一介绍这几大模块：

1.核心容器：核心容器包括spring-core, spring-beans,spring-context, spring-context-support, 和spring-express,其中spring-core和spring-beans模块提供框架的基础部分，包括控制反转和依赖注入。BeanFactory是一个复杂的工厂模式的实现。上下文(spring-context)模块建立在Core和Bean模块的基础之上：它提供了一种框架风格来访问对象，类似于JNDI注册表。Context模块继承了Bean模块的特点并增加了对国际化、事件传播、资源加载等的支持。ApplicationContext接口是Context模块的焦点。spring-context-support支持将第三方库集成到Spring应用中，例如缓存，JavaMail、模板引擎等。spring-expression模块为运行时查询和操作对象提供了强大的表达式语言，它是JSP2.1中的EL语言的扩展，这种语言支持设置和获取属性值，属性赋值，方法调用，访问数组，逻辑和算术运算符，还支持列表投影、选择和常见的聚合。

2.面向切面：Spring的AOP封装包提供了符合AOP Alliance规范的面向切面的编程实现，让你可以定义，例如方法拦截器和切点，从逻辑上讲，从而减弱代码的功能耦合，清晰的被分离开。

3.消息传送：Spring FrameWork 4包括一个spring-messaging模块，它是从Spring集成项目的关键抽象，如Message, MessageChannel,MessageHandle。该模块该包含一组注释映射消息的方法，类似Spring MVC基于注释的编程模型。

4.数据访问/集成：这一层包括JDBC、ORM、OXM、JMS和事务模块。spring-jdbc模块提供了一个JDBC抽象层从而消除了令人乏味的JDBC编程和解析数据库提供商特定的错误。spring-tx模块支持类的编程和声明式事务管理，实现特殊的接口和你的POJO(Plain Old Java Objects)。spring-orm模块提供了流行的对象-关系映射集成层API，包括JPA、JDO和Hibernate。使用spring-orm模块可以使这ORM框架结合Spring提供的其他特性，比如前面提到的简单的声明式事务管理。spring-oxm模块提供了一个抽象层，支持对象/XML映射的实现，如JAXB、 Castor、XMLBeans、 JiBX和XStream。

5.网络层：包括spring-web、spring-webmvc、spring-websocket和spring-webmvc-portlet模块。spring-web模块提供基础的针对web开发的集成特性，包括文件上传功能、利用Servlet侦听器进行IOC容器初始化和针对web的应用上下文。它还包括一个HTTP客户端和Spring的web部件的远程支持。spring-webmvc模块包括Spring的MVC模型和REST Web服务实现的web应用程序，Spring的MVC框架提供了一种清晰的分离模型，在领域模型代码和web form之间，并且还可以借助Spring框架的其他特性。

6.测试层：spring-test模块支持单元测试和包含Junit或者TestNG的集成测试，它提供了Spring的ApplicationContext一致性装载和这些上下文的缓存机制，它还提供了可用于测试代码隔离的模拟对象。

### 1.3动手操作

概念先讲到这，先上手创建项目找找感觉。使用的是idea开发工具。

创建项目开始（使用maven构建）：

![img/创建工程.png](img\创建工程.png)

在Groupid中填入项目的包名即可。Artifactid自定义即可，这里建议与项目名称一致。版本默认在Groupid中填入项目的包名即可。Artifactid自定义即可，这里建议与项目名称一致。版本默认.

![1547564872244](img\1547564872244.png)

另外的是maven相关设置，不懂得同学可以先去学学maven，项目中都是必学的

![1547565010677](img\1547565010677.png)

选择文件位置

![1547565055300](img\1547565055300.png)

创建完成，项目架构如图

![1547565242360](img\1547565242360.png)

在main下新建文件夹resources用于存放资源文件

![1547565426242](img\1547565426242.png)

在resources上右键，选择mark directory as 选中Resources Root

![1547566595862](E:img\1547566595862.png)

添加spring依赖包

```xml
 <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.2.6.RELEASE</version>
    </dependency>
```

pom.xml的完整配置如下：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.yuanqinnan</groupId>
  <artifactId>springLearn</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>springLearn</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.2.6.RELEASE</version>
    </dependency>
  </dependencies>

  <build>
</build>
</project>
```

在刚才新建的resources下新建META-INF包，在META-INF下新建applicationContext.xml.

![1547566887388](img\1547566887388.png)

xml文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
</beans>
```

新建test包和HelloWorld.java,Main.java代码如下：

![1547567032084](img\1547567032084.png)

代码如下：

```java
public class HelloWorld {

        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
}
public class Main {
    public static void main(String[] args)
    {
        //读取配置文件
        ApplicationContext ctx=new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
        //获取bean的实例
        HelloWorld t=(HelloWorld) ctx.getBean("hello");
        //调用方法
        System.out.println(t.getInfo());
    }
}
```

在applicationContext.xml中配置bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="hello" class="com.yuanqinnan.test.HelloWorld">
        <property name="info" value="Hello,This is my first Spring Application!"></property>
    </bean>
</beans>
```

右键运行Main

![1547567185548](img\1547567185548.png)

得到结果：

![1547567243444](img\1547567243444.png)

如此，一个spring项目便创建成功，后面再继续学习spring的其他知识

## 二、IOC 

### 2.1 IOC概念

上一篇已经了解了spring的相关概念，并且创建了一个spring项目。spring中有最重要的两个概念：**IOC**和**AOP**，我们先从IOC入手。

**IOC**全称Inversion of Control，中文通常翻译为“控制反转”，这其实不是一种技术，而是一种思想。

简单理解就是把原先我们代码里面需要实现的对象创建、依赖的代码，反转给容器来帮忙实现。

这里**分享Iteye的开涛对Ioc的精彩讲解**，以下内容都是来自原文

地址：https://jinnianshilongnian.iteye.com/blog/1413846

**IoC是什么**

　　**Ioc—Inversion of Control，即“控制反转”，不是什么技术，而是一种设计思想。**在Java开发中，**Ioc意味着将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。**如何理解好Ioc呢？理解好Ioc的关键是要明确“谁控制谁，控制什么，为何是反转（有反转就应该有正转了），哪些方面反转了”，那我们来深入分析一下：

　　●**谁控制谁，控制什么：**传统Java SE程序设计，我们直接在对象内部通过new进行创建对象，是程序主动去创建依赖对象；而IoC是有专门一个容器来创建这些对象，即由Ioc容器来控制对 象的创建；**谁控制谁？当然是IoC 容器控制了对象；控制什么？那就是主要控制了外部资源获取（不只是对象包括比如文件等）。**

　　●**为何是反转，哪些方面反转了：**有反转就有正转，传统应用程序是由我们自己在对象中主动控制去直接获取依赖对象，也就是正转；而反转则是由容器来帮忙创建及注入依赖对象；为何是反转？**因为由容器帮我们查找及注入依赖对象，对象只是被动的接受依赖对象，所以是反转；哪些方面反转了？依赖对象的获取被反转了。**

　用图例说明一下，传统程序设计如图1-1，都是主动去创建相关对象然后再组合起来：

![img](https://images2015.cnblogs.com/blog/249993/201610/249993-20161004152619832-169850656.jpg)

​            图1-1 传统应用程序示意图

当有了IoC/DI的容器后，在客户端类中不再主动去创建这些对象了，如图2-2所示:

![img](https://images2015.cnblogs.com/blog/249993/201610/249993-20161004152826535-1580521182.png)

​       图1-2有IoC/DI容器后程序结构示意图

**IoC能做什么**

​     IoC 不是一种技术，只是一种思想，一个重要的面向对象编程的法则，它能指导我们如何设计出松耦合、更优良的程序。传统应用程序都是由我们在类内部主动创建依赖对象，从而导致类与类之间高耦合，难于测试；有了IoC容器后，把创建和查找依赖对象的控制权交给了容器，由容器进行注入组合对象，所以对象与对象之间是 松散耦合，这样也方便测试，利于功能复用，更重要的是使得程序的整个体系结构变得非常灵活。

　　其实**IoC对编程带来的最大改变不是从代码上，而是从思想上，发生了“主从换位”的变化。应用程序原本是老大，要获取什么资源都是主动出击，但是在IoC/DI思想中，应用程序就变成被动的了，被动的等待IoC容器来创建并注入它所需要的资源了。**

　　**IoC很好的体现了面向对象设计法则之一—— 好莱坞法则：“别找我们，我们找你”；即由IoC容器帮对象找相应的依赖对象并注入，而不是由对象主动去找。**

**IoC和DI**

​      **DI—Dependency Injection，即“依赖注入”**：**组件之间依赖关系**由容器在运行期决定，形象的说，即**由容器动态的将某个依赖关系注入到组件之中**。**依赖注入的目的并非为软件系统带来更多功能，而是为了提升组件重用的频率，并为系统搭建一个灵活、可扩展的平台。**通过依赖注入机制，我们只需要通过简单的配置，而无需任何代码就可指定目标需要的资源，完成自身的业务逻辑，而不需要关心具体的资源来自何处，由谁实现。

　　理解DI的关键是：“谁依赖谁，为什么需要依赖，谁注入谁，注入了什么”，那我们来深入分析一下：

　　●**谁依赖于谁：**当然是**应用程序依赖于IoC容器**；

　　●**为什么需要依赖：****应用程序需要IoC容器来提供对象需要的外部资源**；

　　●**谁注入谁：**很明显是**IoC容器注入应用程序某个对象，应用程序依赖的对象**；

　　**●注入了什么：**就是**注入某个对象所需要的外部资源（包括对象、资源、常量数据）**。

　　**IoC和DI**由什么**关系**呢？其实它们**是同一个概念的不同角度描述**，由于控制反转概念比较含糊（可能只是理解为容器控制对象这一个层面，很难让人想到谁来维护对象关系），所以2004年大师级人物Martin Fowler又给出了一个新的名字：“依赖注入”，相对IoC 而言，**“****依赖注入”****明确描述了“被注入对象依赖IoC****容器配置依赖对象”。**

相信通过上面的文章，对IOC的理解会更深。下面讲讲三种依赖注入的方式

**构造方法注入**

顾名思义，构造方法注入，就是被注入对象可以通过在其构造方法中声明依赖对象的参数列表， 让外部（通常是IoC容器）知道它需要哪些依赖对象

```java
public classA(IinterfaceA a,IinterfaceB b){
    this.a=a;
    this.b=b;
}
```

构造方法注入方式比较直观，对象被构造完成后，即进入就绪状态，可以马上使用。

**setter 方法注入**

对于JavaBean对象来说，通常会通过setXXX()和getXXX()方法来访问对应属性。这些setXXX()方法统称为setter方法，getXXX()当然就称为getter方法。

```java
public class classB(){
    private IinterfaceA a;
    private IinterfaceB b;
    public IinterfaceA getIinterfaceA(){
        return a;
    }
    public void setIinterfaceA(IinterfaceA a){
        this.a=a;
    }
    public IinterfaceB getIinterfaceB(){
        return b;
    }
    public void setIinterfaceB(IinterfaceB b){
        this.b=b;
    }
}
```

**接口注入** 　

相对于前两种注入方式来说，接口注入没有那么简单明了。被注入对象如果想要IoC Service Provider为其注入依赖对象，就必须实现某个接口。这个接口提供一个方法，用来为其注入依赖对象。IoC Service Provider最终通过这些接口来了解应该为被注入对象注入什么依赖对象。

创建Person （被注入对象）要实现的接口 

```java
interface UserInject{
        void injectUser(User user);//这里必须 是被注入对象依赖的对象
    }
```

Person 对象实现接口 

```java
class Person implements UserInject{ 
    private User user; public Person(){} 
    @Override 
    public void injectUser(User user)
    {
        this.user = user;//实现注入方法，外部通过此方法给此对象注入User对象
    }
}

```

外部调injectUser方法为Persion对象注入User对象，此即接口注入 

**三种注入方式的比较**

- 接口注入。从注入方式的使用上来说，接口注入是现在不甚提倡的一种方式，基本处于“退役状态”。因为它强制被注入对象实现不必要的接口，带有侵入性。而构造方法注入和setter方法注入则不需要如此。

- 构造方法注入。这种注入方式的优点就是，对象在构造完成之后，即已进入就绪状态，可以 9马上使用。缺点就是，当依赖对象比较多的时候，构造方法的参数列表会比较长。而通过反射构造对象的时候，对相同类型的参数的处理会比较困难，维护和使用上也比较麻烦。而且在Java中，构造方法无法被继承，无法设置默认值。对于非必须的依赖处理，可能需要引入多个构造方法，而参数数量的变动可能造成维护上的不便。

- setter方法注入。因为方法可以命名，所以setter方法注入在描述性上要比构造方法注入好一些。 另外，setter方法可以被继承，允许设置默认值，而且有良好的IDE支持。缺点当然就是对象无法在构造完成后马上进入就绪状态。 

  综上所述，构造方法注入和setter方法注入因为其侵入性较弱，且易于理解和使用，所以是现在使用最多的注入方式；而接口注入因为侵入性较强，近年来已经不流行了。 


### 2.2源码探究

在学习spring的具体配置之前，先了解下源码的基本结构。上一篇的测试代码

```java
ApplicationContext ctx=new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
  //获取bean的实例
HelloWorld t=(HelloWorld) ctx.getBean("hello");
```

我们大致分析下过程：

1. 通过Resource对象加载配置文件
2. 解析配置文件,得到bean
3. 解析bean,id作为bean的名字,class用于反射得到bean的实例(Class.forName(className)); 
4. 调用getBean的时候,从容器中返回对象实例。

当然这只是简单的理解，IOC核心内容是beanFactory与ApplicationContext

**BeanFactory**

BeanFactory 是 Spring 的“心脏”。它就是 Spring IoC 容器的真面目。Spring 使用 BeanFactory 来实例化、配置和管理 Bean，BeanFactory有着庞大的继承、实现体系，有众多的子接口、实现类。

![1548075109023](img\1548075109023.png)

1. BeanFactory作为一个主接口不继承任何接口，暂且称为**一级接口**。
2. 有3个子接口继承了它，进行功能上的增强。这3个子接口称为**二级接口**。
3. ConfigurableBeanFactory可以被称为**三级接口**，对二级接口HierarchicalBeanFactory进行了再次增强，它还继承了另一个外来的接口SingletonBeanRegistry
4. ConfigurableListableBeanFactory是一个更强大的接口，继承了上述的所有接口，无所不包，称为**四级接口**。（这4级接口是BeanFactory的基本接口体系。继续，下面是继承关系的2个抽象类和2个实现类：）
5. AbstractBeanFactory作为一个抽象类，实现了三级接口ConfigurableBeanFactory大部分功能。
6. AbstractAutowireCapableBeanFactory同样是抽象类，继承自AbstractBeanFactory，并额外实现了二级接口AutowireCapableBeanFactory
7. DefaultListableBeanFactory继承自AbstractAutowireCapableBeanFactory，实现了最强大的四级接口ConfigurableListableBeanFactory，并实现了一个外来接口BeanDefinitionRegistry，它并非抽象类。
8. 最后是最强大的XmlBeanFactory，继承自DefaultListableBeanFactory，重写了一些功能，使自己更强大。

最基本的IOC容器接口BeanFactory

```java
public interface BeanFactory {

    /**
     * 用来引用一个实例，或把它和工厂产生的Bean区分开，就是说，如果一个FactoryBean的名字为a，那么，&a会得到那个Factory
     */
    String FACTORY_BEAN_PREFIX = "&";

    /*
     * 四个不同形式的getBean方法，获取实例
     */
    //根据bean的名字，获取在IOC容器中得到bean实例   
    Object getBean(String name) throws BeansException;
   //根据bean的名字和Class类型来得到bean实例，增加了类型安全验证机制。    
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
    
    <T> T getBean(Class<T> requiredType) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;
    // 是否存在
    boolean containsBean(String name); 
    // 是否为单实例
    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;
   // 是否为原型（多实例）
    boolean isPrototype(String name) throws NoSuchBeanDefinitionException;
    // 名称、类型是否匹配
    boolean isTypeMatch(String name, Class<?> targetType)
            throws NoSuchBeanDefinitionException;
    //得到bean实例的Class类型
    Class<?> getType(String name) throws NoSuchBeanDefinitionException; 

    String[] getAliases(String name);// 根据实例的名字获取实例的别名
```

BeanFactory接口只是做了最基本的定义，里面不管如何定义和加载，只关心如何得到对象，要知道如何得到对象，必须看具体的实现类，其中XmlBeanFactory就是针对最基本的ioc容器的实现。

我们看下源码：

```java
public class XmlBeanFactory extends DefaultListableBeanFactory {
    private final XmlBeanDefinitionReader reader;

    public XmlBeanFactory(Resource resource) throws BeansException {
        this(resource, (BeanFactory)null);
    }

    public XmlBeanFactory(Resource resource, BeanFactory parentBeanFactory) throws BeansException {
        super(parentBeanFactory);
        this.reader = new XmlBeanDefinitionReader(this);
        this.reader.loadBeanDefinitions(resource);
    }
}
```

我们用XmlBeanFactory 实现基本的ioc功能

```java
 //根据Xml配置文件创建Resource资源对象，该对象中包含了BeanDefinition的信息
 Resource resource = new ClassPathResource("META-INF/applicationContext.xml");
 //创建XmlBeanDefinitionReader读取器，用于载入BeanDefinition。之所以需要BeanFactory作为参数，是因为会将读取的信息回调配置给factory
 BeanFactory beanFactory = new XmlBeanFactory(resource);
 HelloWorld helloWorld = beanFactory.getBean("hello",HelloWorld.class);
 System.out.println(helloWorld.getInfo());
```

**ApplicationContext**

ApplicationContext是Spring提供的一个高级的IoC容器，它除了能够提供IoC容器的基本功能外，还为用户提供了以下的附加服务。

1. 支持信息源，可以实现国际化。（实现MessageSource接口）
2. 访问资源。(实现ResourcePatternResolver接口)
3. 支持应用事件。(实现ApplicationEventPublisher接口)

**两者的区别**

1. BeanFactroy采用的是延迟加载形式来注入Bean的，即只有在使用到某个Bean时(调用getBean())，才对该Bean进行加载实例化，这样，我们就不能发现一些存在的Spring的配置问题。而ApplicationContext则相反，它是在容器启动时，一次性创建了所有的Bean。这样，在容器启动时，我们就可以发现Spring中存在的配置错误。 相对于基本的BeanFactory，ApplicationContext 唯一的不足是占用内存空间。当应用程序配置Bean较多时，程序启动较慢。
2. BeanFacotry延迟加载,如果Bean的某一个属性没有注入，BeanFacotry加载后，直至第一次使用调用getBean方法才会抛出异常；而ApplicationContext则在初始化自身是检验，这样有利于检查所依赖属性是否注入；所以通常情况下我们选择使用 ApplicationContext。
   应用上下文则会在上下文启动后预载入所有的单实例Bean。通过预载入单实例bean ,确保当你需要的时候，你就不用等待，因为它们已经创建好了。
3. BeanFactory和ApplicationContext都支持BeanPostProcessor、BeanFactoryPostProcessor的使用，但两者之间的区别是：BeanFactory需要手动注册，而ApplicationContext则是自动注册。（Applicationcontext比 beanFactory 加入了一些更好使用的功能。而且 beanFactory 的许多功能需要通过编程实现而 Applicationcontext 可以通过配置实现。比如后处理 bean ， Applicationcontext 直接配置在配置文件即可而 beanFactory 这要在代码中显示的写出来才可以被容器识别。 ）
4. beanFactory主要是面对与 spring 框架的基础设施，面对 spring 自己。而 Applicationcontex 主要面对与 spring 使用的开发者。基本都会使用 Applicationcontex 并非 beanFactory 

spring 的IOC实现当然不止这些，这些以后再学，推荐一篇大牛写的博客：https://www.cnblogs.com/ITtangtang/p/3978349.html

看完这些相信对spring IOC概念及其实现会有了一些理性认识了，这里面参考了很多园子里大神的文字，下一篇开始学习spring的配置

## 三、spring配置详解

###  

上一篇学习了IOC的概念并初步分析了实现原理，这篇主要学习spring的配置，话不多说，让我们开始！

### 3.1 Bean元素

#### 3.1.1基本配置

看一个最基本的bean配置

```xml
<bean name="hello" class="com.yuanqinnan.test.HelloWorld"></bean>
```

上面的配置中，我们将bean交给spring管理，看其中属性字段：

- class：被管理对象的完整类名
- name：被管理对象的名称，可以通过名称获取改对象（可以重复，可以使用特殊字符）
- id：与name作用相同（不可以重复，不可以使用特殊字符）

#### 3.1.2 bean的进阶配置

**scope属性**

- singleton：定义bean的范围为每个Spring容器一个实例（默认值）
- prototype：定义bean可以被多次实例化（使用一次就创建一次）
- request：定义bean的范围是HTTP请求，只有再使用有web能力的spring上下文时有效。
- request：定义bean的范围是HTTP请求，只有再使用有web能力的spring上下文时有效。
- global-session：定义bean的范围是全局HTTP会话，只有再portlet上下文中有效。

**生命周期属性**

- init-method：配置一个方法作为生命周期初始化方法.spring会在对象创建之后立即调用.

- destory-method：配置一个方法作为生命周期的销毁方法.spring容器在关闭并销毁所有容器中的对象之前调用.

  代码如下

```xml
<bean id="hello"  init-method="getInfo" destroy-method="destory" class="com.yuanqinnan.test.HelloWorld">
</bean>
```

**注意，只有singleton的bean,destory-method才有效**

**实例化bean的方式**

1. 使用类构造器实例化(默认无参数)

```xml
<bean id="hello" class="com.yuanqinnan.test.HelloWorld">
</bean>
```

2.  使用静态工厂方法实例化(简单工厂模式)

```java
public class HellowWorldFactory {
    public static HelloWorld createHellowWorld(){
        return  new HelloWorld();
    }
}
```

```xml
 <bean name="hello2" 
          class="com.yuanqinnan.test.HellowWorldFactory"
          factory-method="createHellowWorld">
    </bean>
```

3. 使用实例工厂方法实例化(工厂方法模式)

```java
public class HellowWorldFactory2 {
    public  HelloWorld createHellowWorld(){
        return new HelloWorld();
    }
}
```

```xml
 <bean id="HellowWorldFactory2" class="com.yuanqinnan.test.HellowWorldFactory2"/>
    <bean id="hello3" factory-bean="HellowWorldFactory2" factory-method="createHellowWorld" />

```

**spring的分模块配置**

spring 允许多个文件进行配置，可以按照模块来进行配置

```xml
 <import resource="applicationContext2.xml"></import>
```

### 3.2  spring 属性注入

#### 3.2.1 set方法注入

<bean>元素的<property>子元素指明了使用它们的set方法来注入，可以使用property的value属性来注入简单类型

```xml
 <bean name="user" class="com.yuanqinnan.test.User">
        <!--值类型注入-->
        <property name="age" value="18"></property>
        <property name="name" value="tom"></property>
        <!-- 引用类型注入-->
        <property name="car" ref="car"></property>
    </bean>
    <bean name="car" class="com.yuanqinnan.test.Car">
        <property name="color" value="blue"></property>
    </bean>
```

user类与car类代码就不贴了，应该是可以看得懂。

#### 3.2.2 构造函数注入

使用constructor-arg来完成构造方法的注入，注入的方式和setter方式注入相同。
可以通过type属性，控制注入的类型
可以通过index属性，控制注入的顺序
可以通过name属性，控制注入的名字

```xml
 <bean name="user2" class="com.yuanqinnan.test.User">
        <constructor-arg name="name" index="0" type="java.lang.String" value="lucy"></constructor-arg>
        <constructor-arg name="age" index="1" type="java.lang.Integer" value="20"></constructor-arg>
        <constructor-arg name="car" ref="car"></constructor-arg>
    </bean>
```

#### 3.2.3 spel注入

```xml
<bean name="user3" class="com.yuanqinnan.test.User">
    <property name="name" value="#{user.name}"></property>
    <property name="age" value="#{user2.age}"></property>
    <!-- 引用类型注入 为car属性注入下方配置的car对象-->
    <property name="car" ref="car"></property>
</bean>
```

### 3.3 复杂类型注入

上面的注入类型都是简单类型，下面讲复杂类型的注入

#### 3.3.1 数组

```xml
<property name="arr">
    <array>
        <value>tom</value>
        <value>lucy</value>
        <ref bean="user2"></ref>
    </array>
</property>
```

#### 3.3.2 List

```xml
<property name="list">
    <list>
        <value>tom</value>
        <value>lucy</value>
        <ref bean="user2"></ref>
    </list>
</property>
```

#### 3.3.3 Set

```xml
<property name="set">
    <set>
        <value>com.jbdc.mysql.Driver</value>
        <value>root</value>
        <ref bean="user"/>
    </set>
</property>
```

#### 3.3.4 Map

```xml
<property name="map">
    <map>
        <entry key="url" value=""></entry>
        <entry key="user" value-ref="user"></entry>
        <entry key-ref="user3" value-ref="user2"></entry>
    </map>
</property>
```

#### 3.3.5 Properties

```xml
<property name="prop">
    <props>
        <prop key="url">com.jbdc.mysql.Driver</prop>
        <prop key="userName">root</prop>
        <prop key="password">1234</prop>
    </props>
</property>
```

总结：以上是spring的配置详解，都是通过XML装配，下一篇我们学习注解配置

## 四、spring注解配置详解

#### 4.1 引言

最近因为找工作，导致很长时间没有更新，找工作的时候你会明白浪费的时间后面都是要还的，现在的每一点努力，将来也会给你回报的，但行好事，莫问前程！努力总不会有错的。

上一篇spring的配置中有园友评论现在很少用xml类配置SpringBean了,都是用注解的方式来进行配置。那么这篇就来讲注解配置。

使用.xml文件来对bean进行注入的缺点很明显：文件会十分庞大，如果分多模块去配置，文件又特别的多，这些会导致可读性和可维护性变差。

为了解决这两个问题，Spring引入了注解，通过"@XXX"的方式，让注解与Java Bean紧密结合，既大大减少了配置文件的体积，又增加了Java Bean的可读性与内聚性。

#### 4.2 将对象注册到容器

注解如何使用呢，分为三步

**第一步：在 applicationContext.xml 中引入命名空间**

![1552139938756](img\1552139938756.png)

**第二步：在 applicationContext.xml 文件中引入注解扫描器**

```xml
 <context:component-scan base-package="com.yuanqinnan.test" ></context:component-scan>
```

base-package：表示含有注解类的包名

如果扫描多个包，则上面的代码书写多行，改变 base-package 里面的内容即可！

如果使用Idea引入注解扫描器，就会直接引入命名空间

**第三步：在 Car 类中添加注解@Component**

```java
@Component
public class Car {

    private String Color;

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Color='" + Color + '\'' +
                '}';
    }
}
```

**第四步：测试**

```java
          ApplicationContext ctx=new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
          //获取bean的实例
          Car car=(Car) ctx.getBean("car");
          System.out.println(car.toString());
```

结果：

![1552140379649](img\1552140379649.png)

结果显示已注入成功

为了方便区分层次，我们再多衍生几个注解

```java
@Repository
@Controller
@Service
```

#### 4.3 值类型注入

```java
@Value("yellow")
public void setColor(String color) {
    Color = color;
}
```

![1552142022135](img\1552142022135.png)

#### **4.4引用类型注入**

引用类型分为自动装配@Autowired和手动注入@Resource

##### 4.4.1 Autowired

顾名思义，就是自动装配，其作用是为了消除代码Java代码里面的getter/setter与bean属性中的property。当然，getter看个人需求，如果私有属性需要对外提供的话，应当予以保留。

@Autowired默认按类型匹配的方式，在容器查找匹配的Bean，当**有且仅有一个匹配的Bean**时，Spring将其注入@Autowired标注的变量中。

创建一个新类Boss

```java
@Component
public class Boss {
    //自动装配
    @Autowired
    private Car car;

    private String name;
    @Value("袁帅")
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car.getColor() +
                ", name='" + name + '\'' +
                '}';
    }
}
```

测试：

```java
Boss boss=(Boss)ctx.getBean("boss");
System.out.println(boss.toString());
```

结果：Boss{car=yellow, name='袁帅'}

这种方式存在匹配到多个类型一致的的情况，这种情况需要**Qualifier**来解决

定义一个Car接口：

```java
public interface ICar {
     String getCarName();
}
```

两个实现类BMWCar和BenzCar：

```java
@Component("benzCar")
public class BenzCar implements ICar {

    @Override
    public String getCarName() {
        return "奔驰";
    }
}
@Component("bmwCar")
public class BMWCar implements ICar {
    @Override
    public String getCarName() {
        return "宝马";
    }
}
```

工厂类：

```java
@Component("cf")
public class CarFactory {
    @Autowired
    private ICar iCar;

    public String toString(){
        return iCar.getCarName();
    }
}
```

很明显这种写法会报错，因为Spring并不知道应当引用哪个实现类，这个时候使用@Qualifie

```java
@Autowired
@Qualifier("bmwCar")
private ICar iCar;
```

##### **4.4.2 @Resource**

@Resource注解与@Autowired注解作用非常相似，这个就简单说了，看例子：

```java
@Resource(type = BenzCar.class)
private ICar iCar2;

@Resource(name = "benzCar")
private ICar iCar3;
```

#### 4.5 初始化和销毁方法

```java
@PostConstruct
public void init(){
    System.out.println("初始化方法");
}
@PreDestroy
public void destory(){
    System.out.println("销毁方法");
}
```

#### 4.6 常用注解

最后介绍常用的注解

@Configuration把一个类作为一个IoC容器，它的某个方法头上如果注册了@Bean，就会作为这个Spring容器中的Bean。
@Scope注解 作用域
@Lazy(true) 表示延迟初始化
@Service用于标注业务层组件、 
@Controller用于标注控制层组件（如struts中的action）
@Repository用于标注数据访问组件，即DAO组件。
@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
@Scope用于指定scope作用域的（用在类上）
@PostConstruct用于指定初始化方法（用在方法上）
@PreDestory用于指定销毁方法（用在方法上）
@DependsOn：定义Bean初始化及销毁时的顺序
@Primary：自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
@Autowired 默认按类型装配，如果我们想使用按名称装配，可以结合@Qualifier注解一起使用。如下：
@Autowired @Qualifier("personDaoBean") 存在多个实例配合使用
@Resource默认按名称装配，当找不到与名称匹配的bean才会按类型装配。
@PostConstruct 初始化注解
@PreDestroy 摧毁注解 默认 单例  启动就加载
@Async异步方法调用

### 五、AOP

#### 5.1 什么是AOP？

Aspect oritention programming(面向切面编程)，AOP是一种思想，高度概括的话是“横向重复，纵向抽取”，如何理解呢？举个例子：访问页面时需要权限认证，如果每个页面都去实现方法显然是不合适的，这个时候我们就可以利用切面编程。

每个页面都去实现这个方法就是横向的重复，我们直接从中切入，封装一个与主业务无关的权限验证的公共方法，这样可以减少系统的重复代码，降低模块之间的耦合度，简单的示意图如下：

![1552190692263](img\1552190692263.png)

#### 5.2 应用场景

AOP用来封装横切关注点，具体可以在下面的场景中使用:
Authentication 权限
Caching 缓存
Context passing 内容传递
Error handling 错误处理
Lazy loading　懒加载
Debugging　　调试
logging, tracing, profiling and monitoring　记录跟踪　优化　校准
Performance optimization　性能优化
Persistence　　持久化
Resource pooling　资源池
Synchronization　同步
Transactions 事务

#### 5.3 相关概念

**1.连接点（Joinpoint）**
​     所谓连接点是指那些可能被拦截到的方法。例如：所有可以增加的方法

**2.切点（Pointcut）**
​     已经被增强的连接点

**3.增强（Advice）**
​     增强的代码

**4.目标对象（Target）**
​    目标类，需要被代理的类

**5.织入（Weaving）**
  是指把增强advice应用到目标对象target来创建新的代理对象proxy的过程

**6.代理（Proxy）**
一个类被AOP织入增强后，就产生出了一个结果类，它是融合了原类和增强逻辑的代理类。

**7.切面（Aspect）**

切入点+通知

**通知类型：**

Spring按照通知Advice在目标类方法的连接点位置，可以分为5类

- 前置通知 （在目标方法执行前实施增强）
- 后置通知（在目标方法执行后实施增强）
- 环绕通知（在目标方法执行前后实施增加）
- 异常抛出通知（在方法跑出异常时通知）
- 引介通知（在目标类中添加一些新的方法和属性）

#### 5.4 实现原理

**AOP的实现关键在于AOP框架自动创建的AOP代理。AOP代理主要分为两大类：**

**静态代理：使用AOP框架提供的命令进行编译，从而在编译阶段就可以生成AOP代理类，因此也称为编译时增强；静态代理一Aspectj为代表。**

**动态代理：在运行时借助于JDK动态代理，CGLIB等在内存中临时生成AOP动态代理类，因此也被称为运行时增强，Spring AOP用的就是动态代理。**

##### 5.4.1 静态代理

在增加员工和删除员工时增加事务处理

```java
//员工类
public class Employee {
    private Integer uid;

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {

        return uid;
    }

    private Integer age;

    private String name;

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

员工接口

```java
//员工接口
public interface EmployeeService {
    //新增方法
    void addEmployee(Employee employee);
    //删除方法
    void deleteEmployee(Integer uid);
}
```

员工实现

```java
//员工方法实现
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public void addEmployee(Employee employee) {
        System.out.println("新增员工");
    }

    @Override
    public void deleteEmployee(Integer uid) {
        System.out.println("删除员工");

    }
}
```

事务类

```java
//事务类
public class MyTransaction {
    //开启事务
    public void before(){
        System.out.println("开启事务");
    }
    //提交事务
    public void after(){
        System.out.println("提交事务");
    }
}
```

代理类

```java
//代理类
public class ProxyEmployee implements EmployeeService {
    //
    private EmployeeService employeeService;

    private MyTransaction myTransaction;

    public ProxyEmployee(EmployeeService employeeService,MyTransaction myTransaction)
    {
       this.employeeService=employeeService;
       this.myTransaction=myTransaction;
    }
    @Override
    public void addEmployee(Employee employee) {
         myTransaction.before();
         employeeService.addEmployee(employee);
         myTransaction.after();
    }

    @Override
    public void deleteEmployee(Integer uid) {
         myTransaction.before();
         employeeService.deleteEmployee(uid);
         myTransaction.after();
    }
}
```

测试：

```java
@Test
    public void fun1(){
        MyTransaction transaction = new MyTransaction();
        EmployeeService EmployeeService = new EmployeeServiceImpl();
        //产生静态代理对象
        ProxyEmployee proxy = new ProxyEmployee(EmployeeService, transaction);
        proxy.addEmployee(null);
        proxy.deleteEmployee(0);
    }
```

结果：

![1552218999097](img\1552218999097.png)

这是静态代理的实现方式，静态代理有明显的缺点：

1、代理对象的一个接口只服务于一种类型的对象，如果要代理的方法很多，势必要为每一种方法都进行代理，静态代理在程序规模稍大时就无法胜任了。

2、如果接口增加一个方法，比如 EmployeeService增加修改 updateEmployee(）方法，则除了所有实现类需要实现这个方法外，所有代理类也需要实现此方法。增加了代码维护的复杂度。

##### 5.4.2 动态代理

动态代理就不要自己手动生成代理类了，我们去掉 ProxyEmployee.java 类，增加一个 ObjectInterceptor.java 类

```java
public class ObjectInterceptor implements InvocationHandler {

    //目标类
    private Object target;
    //切面类（这里指事务类）
    private MyTransaction transaction;

    //通过构造器赋值
    public ObjectInterceptor(Object target,MyTransaction transaction){
        this.target = target;
        this.transaction = transaction;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        this.transaction.before();
        method.invoke(target,args);
        this.transaction.after();
        return  null;
    }
}
```

测试：

```java
@Test
public void fun2(){
    //目标类
    Object target = new EmployeeServiceImpl ();
    //事务类
    MyTransaction transaction = new MyTransaction();
    ObjectInterceptor proxyObject = new ObjectInterceptor(target, transaction);
    /**
     * 三个参数的含义：
     * 1、目标类的类加载器
     * 2、目标类所有实现的接口
     * 3、拦截器
     */
    EmployeeService employeeService = (EmployeeService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
            target.getClass().getInterfaces(), proxyObject);
    employeeService.addEmployee(null);
    employeeService.deleteEmployee(0);
}
```

结果：

![1552220449992](img\1552220449992.png)

#### 5.5 spring的处理AOP的方式

spring 有两种方式实现AOP的：**一种是采用声明的方式来实现（基于XML），一种是采用注解的方式来实现（基于AspectJ）**

##### 5.5.1 xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context
         http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

     <!--目标类-->
     <bean name="employeeService" class="com.yuanqinnan.aop.EmployeeServiceImpl"></bean>
     <bean name="transaction" class="com.yuanqinnan.aop.MyTransaction"></bean>
     <aop:config>
          <aop:aspect ref="transaction">
               <aop:pointcut id="pointcut" expression="execution(* com.yuanqinnan.aop.EmployeeServiceImpl..*(..))"/>
               <!-- 配置前置通知，注意 method 的值要和 对应切面的类方法名称相同 -->
               <aop:before method="before" pointcut-ref="pointcut"></aop:before>
               <!--配置后置通知，注意 method 的值要和 对应切面的类方法名称相同-->
               <aop:after-returning method="after" pointcut-ref="pointcut"/>
          </aop:aspect>
     </aop:config>

</beans>
```

测试：

```
@Test
public void fun3(){
    ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
    EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
    employeeService.addEmployee(null);
}
```

结果：

![1552222592756](img\1552222592756.png)

**补充：**
1.<aop:pointcut>如果位于<aop:aspect>元素中，则命名切点只能被当前<aop:aspect>内定义的元素访问到，为了能被整个<aop:config>元素中定义的所有增强访问，则必须在<aop:config>下定义切点。
2.如果在<aop:config>元素下直接定义<aop:pointcut>，必须保证<aop:pointcut>在<aop:aspect>之前定义。<aop:config>下还可以定义<aop:advisor>，三者在<aop:config>中的配置有先后顺序的要求：首先必须是<aop:pointcut>，然后是<aop:advisor>，最后是<aop:aspect>。而在<aop:aspect>中定义的<aop:pointcut>则没有先后顺序的要求，可以在任何位置定义。
.<aop:pointcut>：用来定义切入点，该切入点可以重用；
.<aop:advisor>：用来定义只有一个通知和一个切入点的切面；
.<aop:aspect>：用来定义切面，该切面可以包含多个切入点和通知，而且标签内部的通知和切入点定义是无序的；和advisor的区别就在此，advisor只包含一个通知和一个切入点。
3.在使用spring框架配置AOP的时候，不管是通过XML配置文件还是注解的方式都需要定义pointcut"切入点"
例如定义切入点表达式 execution(* com.sample.service.impl..*.*(..))
execution()是最常用的切点函数，其语法如下所示：
整个表达式可以分为五个部分：
(1)、execution(): 表达式主体。
(2)、第一个*号：表示返回类型，*号表示所有的类型。
(3)、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
(4)、第二个*号：表示类名，*号表示所有的类。
(5)、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。

##### 5.5.2 注解配置

新建注解类：

```java
@Component
@Aspect
public class AopAspectJ {
    /**
     * 必须为final String类型的,注解里要使用的变量只能是静态常量类型的
     */
    public static final String EDP="execution(* com.yuanqinnan.aop.EmployeeServiceImpl..*(..))";

    /**
     * 切面的前置方法 即方法执行前拦截到的方法
     * 在目标方法执行之前的通知
     * @param jp
     */
    @Before(EDP)
    public void doBefore(JoinPoint jp){

        System.out.println("=========执行前置通知==========");
    }


    /**
     * 在方法正常执行通过之后执行的通知叫做返回通知
     * 可以返回到方法的返回值 在注解后加入returning
     * @param jp
     * @param result
     */
    @AfterReturning(value=EDP,returning="result")
    public void doAfterReturning(JoinPoint jp,String result){
        System.out.println("===========执行后置通知============");
    }

    /**
     * 最终通知：目标方法调用之后执行的通知（无论目标方法是否出现异常均执行）
     * @param jp
     */
    @After(value=EDP)
    public void doAfter(JoinPoint jp){
        System.out.println("===========执行最终通知============");
    }

    /**
     * 环绕通知：目标方法调用前后执行的通知，可以在方法调用前后完成自定义的行为。
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(EDP)
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{

        System.out.println("======执行环绕通知开始=========");
        // 调用方法的参数
        Object[] args = pjp.getArgs();
        // 调用的方法名
        String method = pjp.getSignature().getName();
        // 获取目标对象
        Object target = pjp.getTarget();
        // 执行完方法的返回值
        // 调用proceed()方法，就会触发切入点方法执行
        Object result=pjp.proceed();
        System.out.println("输出,方法名：" + method + ";目标对象：" + target + ";返回值：" + result);
        System.out.println("======执行环绕通知结束=========");
        return result;
    }

    /**
     * 在目标方法非正常执行完成, 抛出异常的时候会走此方法
     * @param jp
     * @param ex
     */
    @AfterThrowing(value=EDP,throwing="ex")
    public void doAfterThrowing(JoinPoint jp,Exception ex) {
        System.out.println("===========执行异常通知============");
    }
}
```

xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context
         http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    <context:component-scan base-package="com.yuanqinnan.aop" ></context:component-scan>
    <!-- 声明spring对@AspectJ的支持 -->
    <aop:aspectj-autoproxy/>
    <!--目标类-->
    <bean name="employeeService" class="com.yuanqinnan.aop.EmployeeServiceImpl"></bean>
</beans>
```

测试：

```java
@Test
public void fun4(){
    ApplicationContext act =  new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
    EmployeeService employeeService = (EmployeeService) act.getBean("employeeService");
    employeeService.addEmployee(null);
}
```

结果：

![1552223574464](img\1552223574464.png)

springAOP的知识就总结到这里

### 六、事务管理

#### 6.1 什么是事务

什么是事务（Transaction）？事务是数据库中的概念，是指访问并可能更新数据库中各种数据项的一个程序执行单元(unit)。

有个非常经典的转账问题：A向B转款1000元，A转出成功，扣除了A账户的1000元，但当系统准备向B账户增加1000元出现了故障，转入失败，但是A账户的金额已经扣除，这样的结果是A账户凭空少了1000元，很明显这样是不行的，正确的做法应该是当B账户增加成功后，A账户的扣款才能生效。

简单总结一句话就是：**事务逻辑上的一组操作,组成这组操作的各个逻辑单元,要么一起成功,要么一起失败**

#### 6.2 事务的四个特性（ACID）

- 原子性（Atomicity）：事务是一个原子操作，由一系列动作组成。事务的原子性确保动作要么全部完成，要么完全不起作用。
- 一致性（Consistency）：一旦事务完成（不管成功还是失败），系统必须确保它所建模的业务处于一致的状态，而不会是部分完成部分失败。在现实中的数据不应该被破坏。
- 隔离性（Isolation）：可能有许多事务会同时处理相同的数据，因此每个事务都应该与其他事务隔离开来，防止数据损坏。
- 持久性（Durability）：一旦事务完成，无论发生什么系统错误，它的结果都不应该受到影响，这样就能从任何系统崩溃中恢复过来。通常情况下，事务的结果被写到持久化存储器中。

#### 6.3 事务隔离（Isolation Level）

事务隔离意味着对于某一个正在运行的事务来说，好像系统中只有这一个事务，其他并发的事务都不存在一样。大部分情况下，很少使用完全隔离的事务。但不完全隔离的事务会带来如下一些问题。

**脏数据（Dirty Read）**：一个事务读到了另一个事务的未提交的数据

**不可重读（Unrepeatable Read）**：一个事务读到了另一个事务已经提交的 update 的数据导致多次查询结果不一致

**幻读（Phantom Read）**：一个事务读到了另一个事务已经提交的 insert 的数据导致多次查询结果不一致

通过设置事务隔离级别解决这些读的问题，事务隔离级别分别是：

**读操作未提交（Read Uncommitted）：**说明一个事务在提交前，其变化对于其他事务来说是可见的。这样脏读、不可重读和幻读都是允许的。当一个事务已经写入一行数据但未提交，其他事务都不能再写入此行数据；但是，任何事务都可以读任何数据。这个隔离级别使用排写锁实现（**脏读，不可重复读，虚读都有可能发生**）

**读操作已提交（Read Committed）**：它使用临时的共读锁和排写锁实现。这种隔离级别不允许脏读，但不可重读和幻读是允许的（**避免脏读。但是不可重复读和虚读有可能发生**）

**可重读（Repeatable Read）**：说明事务保证能够再次读取相同的数据而不会失败。此隔离级别不允许脏读和不可重读，但幻读会出现（**避免脏读和不可重复读.但是虚读有可能发生**）

**可串行化（Serializable）**：提供最严格的事务隔离。这个隔离级别不允许事务并行执行，只允许串行执行。这样，脏读、不可重读或幻读都可发生（**避免以上所有读问题**）

Mysql 默认:可重复读
Oracle 默认:读已提交

#### 6.4 spring事务管理的核心接口

因为在不同平台,操作事务的代码各不相同.spring提供了一个接口： **PlatformTransactionManager** 接口

![1552314137725](img\1552314137725.png)

PlatformTransactionManager主要有三个方法

![1552314763453](img\1552314763453.png)



- TransactionStatus getTransaction(TransactionDefinition definition) ，事务管理器 通过TransactionDefinition，获得“事务状态”，从而管理事务。
- void commit(TransactionStatus status)  根据状态提交
- void rollback(TransactionStatus status) 根据状态回滚

事务的状态：

![1552315322007](img\1552315322007.png)

获取事务时带入的接口为**TransactionDefinition**

![1552315675333](img\1552315675333.png)

隔离级别取值详解：

1. **PROPAGATION_REQUIRED** ：required , 必须。默认值，A如果有事务，B将使用该事务；如果A没有事务，B将创建一个新的事务。
2. PROPAGATION_SUPPORTS：supports ，支持。A如果有事务，B将使用该事务；如果A没有事务，B将以非事务执行。
3. PROPAGATION_MANDATORY：mandatory ，强制。A如果有事务，B将使用该事务；如果A没有事务，B将抛异常。
4. **PROPAGATION_REQUIRES_NEW** ：requires_new，必须新的。如果A有事务，将A的事务挂起，B创建一个新的事务；如果A没有事务，B创建一个新的事务。
5. PROPAGATION_NOT_SUPPORTED ：not_supported ,不支持。如果A有事务，将A的事务挂起，B将以非事务执行；如果A没有事务，B将以非事务执行。
6. PROPAGATION_NEVER ：never，从不。如果A有事务，B将抛异常；如果A没有事务，B将以非事务执行。
7. **PROPAGATION_NESTED** ：nested ，嵌套。A和B底层采用保存点机制，形成嵌套事务。

实现该接口的有DataSourceTransactionManager和HibernateTransitionmanager

#### 6.5 spring事务处理

spring的事务处理分为**编程式事务处理**与**声明式事务处理**

**编程式事务处理**：所谓编程式事务指的是通过编码方式实现事务，允许用户在代码中精确定义事务的边界。即类似于JDBC编程实现事务管理。管理使用TransactionTemplate或者直接使用底层的PlatformTransactionManager。对于编程式事务管理，spring推荐使用TransactionTemplate。

**声明式事务处理**：管理建立在AOP之上的。其本质是对方法前后进行拦截，然后在目标方法开始之前创建或者加入一个事务，在执行完目标方法之后根据执行情况提交或者回滚事务。声明式事务最大的优点就是不需要通过编程的方式管理事务，这样就不需要在业务逻辑代码中掺杂事务管理的代码，只需在配置文件中做相关的事务规则声明(或通过基于@Transactional注解的方式)，便可以将事务规则应用到业务逻辑中。

　　简单地说，编程式事务侵入到了业务代码里面，但是提供了更加详细的事务管理；而声明式事务由于基于AOP，所以既能起到事务管理的作用，又可以不影响业务代码的具体实现。

理论很多了，开始编码吧，已转账作为例子

DAO账户接口：

```java
//账户接口
public interface AccountDao {
    //转出
    void out(String outer,Double money);
    //转入
    void in(String in,Double money);
}
```

转账实现：

```java
//转账实现
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao   {
    public void out(String outer, Double money) {
        this.getJdbcTemplate().update("update account set money = money - ? where username = ?",money,outer);
    }

    public void in(String in, Double money) {
        this.getJdbcTemplate().update("update account set money = money + ? where username = ?",money,in);
    }
}
```

Service接口

```java
public interface AccountService {
    void transfer(String from,String to,Double money);
}
```

实现：

```java
public class AccountServiceImpl implements AccountService {
    // 业务层注入 DAO:
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transfer(String from, String to, Double money) {
        accountDao.out(from,money);
        accountDao.in(to,money);
    }
}
```

测试：

```java
@Test
public void fun1(){
    ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
    AccountService account = (AccountService) context.getBean("accountService");
    //张三 向 李四 转账1000
    account.transfer("zhangsan", "lisi", 1000d);
}
```

数据库原先数据：

![1552354935825](E:\JavaCode\spring\img\1552354935825.png)

执行方法之后：

![1552355081371](E:\JavaCode\spring\img\1552355081371.png)

这个结果很正常，现在我们让程序出一些错误，在两个操作之间增加一个异常

```java
accountDao.out(from,money);
int i= 1/0;//异常操作
accountDao.in(to,money);
```

运行报错：java.lang.ArithmeticException: / by zero

但是张三的账户还是扣除了1000元，李四账户并未改动

![1552357432518](E:\JavaCode\spring\img\1552357432518.png)

##### 6.5.1 编程式事务处理实现转账

现在需要我们来改造代码，让转账支持事务，改造如下：

在AccountServiceImpl注入事务管理，代码如下

```java
public class AccountServiceImpl implements AccountService {
    // 业务层注入 DAO:
    private AccountDao accountDao;
    //注入事务管理
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transfer(final String from,final String to, final Double money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus arg0) {
                accountDao.out(from, money);
                int i = 1/0;
                accountDao.in(to, money);
            }
        });
    }
}
```

配置文件也做相应修改

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context
         http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test"></property>
        <property name="user" value="root"></property>
        <property name="password" value="123456"></property>
    </bean>

    <bean id="accountDao" class="com.yuanqinnan.transaction.AccountDaoImpl">
        <property name="dataSource" ref="dataSource"></property>

    </bean>

    <bean id="accountService" class="com.yuanqinnan.transaction.AccountServiceImpl">
        <property name="accountDao" ref="accountDao"></property>
        <property name="transactionTemplate" ref="transactionTemplate"></property>
    </bean>

    <!-- 创建模板 -->
    <bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
        <property name="transactionManager" ref="txManager"></property>
    </bean>

    <!-- 配置事务管理器 ,管理器需要事务，事务从Connection获得，连接从连接池DataSource获得 -->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
</beans>
```

测试方法不改，仍然报错，但是张三的账户未被修改，说明事务生效

##### 6.5.2 声明式事务处理实现转账（基于AOP的 xml 配置）

这种方式不修改原有的逻辑代码，只是修改配置文件，配置文件如下:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context
         http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">

    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test"></property>
        <property name="user" value="root"></property>
        <property name="password" value="123456"></property>
    </bean>

    <bean id="accountDao" class="com.yuanqinnan.transaction.AccountDaoImpl">
        <property name="dataSource" ref="dataSource"></property>

    </bean>

    <bean id="accountService" class="com.yuanqinnan.transaction.AccountServiceImpl">
        <property name="accountDao" ref="accountDao"></property>
    </bean>
    <!-- 1 事务管理器 -->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!-- 2 事务详情（事务通知）  ， 在aop筛选基础上，比如对ABC三个确定使用什么样的事务。例如：AC读写、B只读 等
        <tx:attributes> 用于配置事务详情（属性属性）
            <tx:method name=""/> 详情具体配置
                propagation 传播行为 ， REQUIRED：必须；REQUIRES_NEW:必须是新的
                isolation 隔离级别
    -->
    <tx:advice id="txAdvice" transaction-manager="txManager">
        <tx:attributes>
            <tx:method name="transfer" propagation="REQUIRED" isolation="DEFAULT"/>
        </tx:attributes>
    </tx:advice>

    <!-- 3 AOP编程，利用切入点表达式从目标类方法中 确定增强的连接器，从而获得切入点 -->
    <aop:config>
        <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.yuanqinnan.transaction.AccountServiceImpl.transfer(..))"/>
    </aop:config>
</beans>
```

测试数据无误

##### 6.5.3 声明式事务处理实现转账（基于AOP的 注解 配置）

xml文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context
         http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
<context:component-scan base-package="com.yuanqinnan.transaction" ></context:component-scan>
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test"></property>
    <property name="user" value="root"></property>
    <property name="password" value="123456"></property>
</bean>
<bean id="accountDao" class="com.yuanqinnan.transaction.AccountDaoImpl">
    <property name="dataSource" ref="dataSource"></property>

</bean>

<bean id="accountService" class="com.yuanqinnan.transaction.AccountServiceImpl">
    <property name="accountDao" ref="accountDao"></property>
</bean>
<!-- 1 事务管理器 -->
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 2 将管理器交予spring
    * transaction-manager 配置事务管理器
    * proxy-target-class
        true ： 底层强制使用cglib 代理
-->
<tx:annotation-driven transaction-manager="txManager" proxy-target-class="true"/>
</beans>
```

AccountServiceImpl加上注解即可实现

```
@Transactional(propagation=Propagation.REQUIRED , isolation = Isolation.DEFAULT)
public class AccountServiceImpl implements AccountService {
```

以上三种方式均可实现事务的管理，事务管理讲完之后整个spring的入门总结也结束了，下面开始mybatis之旅