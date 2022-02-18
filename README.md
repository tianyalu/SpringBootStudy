# `Spring Boot`入门学习

[TOC]

参考：[http://c.biancheng.net/spring_boot/overview.html](http://c.biancheng.net/spring_boot/overview.html)

## 一、`Spring Boot`是什么

众所周知 Spring 应用需要进行大量的配置，各种 XML 配置和注解配置让人眼花缭乱，且极容易出错，因此 Spring 一度被称为“配置地狱”。

为了简化 Spring 应用的搭建和开发过程，Pivotal 团队在 Spring 基础上提供了一套全新的开源的框架，它就是 Spring Boot。

Spring Boot 具有 Spring 一切优秀特性，Spring 能做的事，Spring Boot 都可以做，而且使用更加简单，功能更加丰富，性能更加稳定而健壮。随着近些年来微服务技术的流行，Spring Boot 也成为了时下炙手可热的技术。

Spring Boot 提供了大量开箱即用（out-of-the-box）的依赖模块，例如 spring-boot-starter-redis、spring-boot-starter-data-mongodb 和 spring-boot-starter-data-elasticsearch 等。这些依赖模块为 Spring Boot 应用提供了大量的自动配置，使得 Spring Boot 应用只需要非常少量的配置甚至零配置，便可以运行起来，让开发人员从 Spring 的“配置地狱”中解放出来，有更多的精力专注于业务逻辑的开发。

## 1.1 `Spring Boot`的特性

1. 独立运行的`Spring`项目

Spring Boot 可以以 jar 包的形式独立运行，Spring Boot 项目只需通过命令“ java–jar xx.jar” 即可运行。

## 二、`Spring Boot`配置绑定

### 2.1 两种配置绑定方式

`SpringBoot`提供了以下两种方式进行配置绑定：

* 使用`@ConfigurationProperties`注解
* 使用`@Value`注解

注意：

> 1. 只有在容器中的组件，才会拥有`SpringBoot`提供的强大功能，如果我们想要使用`@ConfigurationProperties`注解进行配置绑定，那么首先就要保证该`JavaBean`对象在`IoC`容器中，所以需要用到`@Component`注解来添加组件到容器中；
> 2. `JavaBean`上使用了注解`@ConfigurationProperties(prefix="person")`，它表示将这个`JavaBean`中的所有属性与配置文件中以`person`为前缀的配置进行绑定。

### 2.2 `@value`与`@ConfigurationProperties`对比

> 1. 使用位置不同
>
> * `@ConfigurationProperties`：标注在`JavaBean`的类名上；
> * `@Value`：标注在`JavaBean`的属性上。
>
> 2. 功能不同
>
> * `@ConfigurationProperties`：用于批量绑定配置文件中的配置；
> * `@Value`：只能一个一个地指定需要绑定的信息。
>
> 3. 松散绑定支持不同
>
> * `@ConfigurationProperties`：支持松散绑定（松散语法），例如实体类`Person`中有一个属性为`lastName`，那么配置文件中的属性名支持以下写法：`person.firstName`，`person.first-name`、`person.first_name`、`PERSON_FIRST_NAME`;
> * `@Value`：不支持松散绑定。
>
> 4. `SpEL`支持不同
>
> * `@ConfigurationProperties`：不支持`SpEL`表达式；
> * `@Value`：支持`SpEL`表达式。
>
> 5. 复杂类型封装
>
> * `@ConfigurationProperties`：支持所有类型数据的封装，例如`Map`、`List`、`Set`以及对象等；
> * `@Value`：只支持基本数据类型的封装，例如字符串、布尔值、整数等类型。
>
> 6. 应用场景不同
>
> `@Value`和`@ConfigurationProperties`两个注解 之间，并没有明显的优劣之分，他们只是适合的应用场景不同而已：
>
> * 若只是获取配置文件中的某项值，则推荐使用`@Value`注解；
> * 若专门编写了一个`JavaBean`来和配置文件进行映射，则建议使用`@ConfigurationProperties`注解。

### 2.3 `@PropertySource`

如果将所有的配置都集中到`application.properties`或`application.yml`中，那么这个配置文件会十分的臃肿且难以维护，因此我们通常会将与`SpringBoot`无关的配置（例如自定义配置）提取出来，写在一个单独的配置文件中，并在对应的`JavaBean`上使用`@PropertySource`注解指向该配置文件。

## 三、`Spring Boot`导入`Spring`配置

默认情况下，`Spring Boot`中是不包含任何的`Spring`配置文件的，即使我们手动添加`Spring`配置文件到项目中，也不会被识别。为此，`Spring Boot`提供了以下两种方式来导入`Spring`配置：

* 使用`@ImportResource`注解加载`Spring`配置文件
* 使用全注解方式加载`Spring`配置

## 四、`Spring Boot`默认配置文件

通常情况下，`Spring Boot`在启动时会将`resource`目录下的`application.properties`或`application.yml`作为默认配置文件，我们可以在该配置文件中对项目进行配置，但这并不意味着`Spring Boot`项目只能存在一个`application.properties`或`application.yml`。`Spring Boot`启动时会扫码以下5个位置的`application.properties`或`application.yml`文件，并将它们作为`Spring boot`的默认配置文件：

```bash
1. file:./config/
2. file:./config/*/
3. file:./
4. classpath:/config/
5. classpath:/

# 注：file指当前项目根目录；classpath指当前项目的类路径，即resources目录
```

以上所有位置的配置文件都会被加载，且它们优先级依次降低，序号越小优先级越高。其次，位于相同位置的`application.properties`的优先级高于`application.yml`，所有位置的文件都会被加载，高优先级配置会覆盖第优先级配置，形成互补配置，即：

* 存在相同的配置内容时，高优先级的内容会覆盖低优先级的内容；
* 存在不同的配置内容时，高优先级和低优先级的配置内容取并集。

## 五、`Spring Boot`外部配置文件

除了默认配置文件，`Spring Boot`还可以加载一些位于项目外部的配置文件，可以通过以下两个参数来指定外部配置文件的路径：

* `spring.config.location`
* `spring.config.additional-location`

我们可以先将`Spring Boot`项目打包成`JAR`文件，然后在命令行启动命令中，使用命令行参数`--spring.config.location`来指定外部配置文件的路径：

```bash
java -jar {JAR} --spring.config.location={外部配置文件全路径}

# 需要注意的是使用该参数指定配置文件后，会使项目默认配置文件(application.properties或application.yml)失效，Spring Boot将会只加载指定的外部配置文件。
```

我们还可以在`Spring Boot`启动时，使用命令行参数`--spring.additional-location`来加载外部配置文件：

```bash
java -jar {JAR} --spring.config.additional-location={外部配置文件全路径}

# 但与`--spring.config.location`不同，·--spring.config.additional-location`不会是项目默认的配置文件失效，使用该命令行参数添加的外部配置文件会与项目默认配置文件共同生效，形成互补配置，且优先级是最高的，比所有默认配置文件的优先级都高。
```

**注意：**

`Maven`对项目进行打包时，位于项目根目录下的配置文件是无法被打包进项目的`JAR`包的，因此位于根目录下的默认配置文件无法在`JAR`中生效，即该项目将只加载指定的外部配置文件和项目类路径（`classpath`）下的默认配置文件，他们的加载优先级顺序为：

```bash
1. spring.config.additional-location指定的外部配置文件my-application.yml
2. classpath:/config/application.yml
3. classpath:/application.yml
```

针对于`Maven`打包时导致项目根目录下的配置文件无法被加载的问题，可以通过以下3种方式解决：

> 1. 在`IDEA`的运行配置（`Run/Debug Configuration`）中，添加虚拟机参数`-Dspring.config.additional-location=E:\Web\WorkSpace\myConfig\my-application.yml`,指定外部配置文件；
>
> 2. 在`IDEA`的运行配置（`Run/Debug Configuration`）中，添加虚拟机参数`--spring.config.additional-location=E:\Web\WorkSpace\myConfig\my-application.yml`,指定外部配置文件；
>
> 3. 在主启动类中调用`System.setProperty()`方法添加系统属性`spring.config.additional-location`，指定外部配置文件。

## 六、`Spring Boot`配置加载顺序

`Sprint Boot`不仅可以通过配置文件进行配置，还可以通过环境变量、命令行参数等多种形式进行配置。`Spring Boot`配置优先级（优先级由高到低）：

```bash
1. 命令行参数
2. 来自java:comp/env的JNDI属性
3. Java系统属性（System.getProperties()）
4. 操作系统环境变量
5. RandomValuePropertySource配置的random.*属性值
6. 配置文件（YAML文件、Properties文件）
7. @Configuration注解类上的@PropertySource指定的配置文件
8. 通过SpringApplication.setDefaultProperties指定的默认属性
```

以上所有形式的配置都会被加载，当存在相同配置内容时，高优先级的配置会覆盖低优先级的配置；存在不同的配置内容时，高优先级和低优先级的配置内容取并集，共同生效，形成互补配置。

`Spring Boot`中的所有配置，都可以同命令行参数进行指定，其配置形式如下：

```bash
java -jar {Jar文件名} --{参数1}={参数值1} --{参数2}={参数值2}

# 示例：
java -jar helloworld-0.0.1-SNAPSHOT.jar --server.port=8081 --server.servlet.context-path=/bcb
# --server.port: 指定服务器端口号
# --server.servlet.context-path: 指定上下文路径（项目的访问路径）
```

