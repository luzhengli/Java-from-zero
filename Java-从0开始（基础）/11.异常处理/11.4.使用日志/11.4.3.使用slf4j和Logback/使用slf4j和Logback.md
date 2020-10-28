# slf4j和Logback

Q1：slf4j和Logback 是什么？

A1：slf4j和Logback类似Commons Logging和Log4j，它们一个作为日志 API，一个作为日志底层。



---

Q2：slf4j 有何优点？

A2：SLF4J对Commons Logging的接口有一些改进。例如要打印日志，有时需要拼接字符串

```java
int score = 99;
p.setScore(score);
log.info("Set score " + score + " for Person " + p.getName() + " ok.");
```

拼接字符串很麻烦，所以 slf4j中的接口改进成了下面这样

```java
int score = 99;
p.setScore(score);
logger.info("Set score {} for Person {} ok.", score, p.getName());
```



# slf4j和Logback的使用

环境准备，由于我们希望使用 slf4j和Logback，因此必须先在 `Instance.java` 所在目录下安装以下包：

- slf4j-api-1.7.x.jar
- logback-classic-1.2.x.jar
- logback-core-1.2.x.jar



然后我们需要在同目录下准备一个名为 `logback.xml` Logback 配置文件，内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

	<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
		<encoder>
			<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
		</encoder>
	</appender>

	<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<encoder>
			<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
			<charset>utf-8</charset>
		</encoder>
		<file>log/output.log</file>
		<rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
			<fileNamePattern>log/output.log.%i</fileNamePattern>
		</rollingPolicy>
		<triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
			<MaxFileSize>1MB</MaxFileSize>
		</triggeringPolicy>
	</appender>

	<root level="INFO">
		<appender-ref ref="CONSOLE" />
		<appender-ref ref="FILE" />
	</root>
</configuration>
```



代码方面，slf4j 和 Commons Logging 的接口十分类似，使用 slf4j 基本写法如下：

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Instance {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Instance.class);
        logger.info("Start process...");
        String s = "Test.";
        logger.info("The value of {} is {}", "s", s);
        logger.info("End process...");
    }
}
```



命令方面，依旧是编译+执行两部分：

1. 编译：输入`javac -cp slf4j-api-1.7.30.jar Instance.java -encoding utf-8`，会生成 `Instance.class`。
2. 执行：`java -cp ".;logback-classic-1.2.3.jar;logback-core-1.2.3.jar;slf4j-api-1.7.30.jar" Instance`。**注意**，由于这里引用了3个jar包，因此需要把它们都添加到 classpath。



最后的结果如下：

```
16:12:15.230 [main] INFO  Instance - Start process...
16:12:15.238 [main] INFO  Instance - The value of s is Test.
16:12:15.262 [main] INFO  Instance - End process...
```