# 什么是 Commons Logging？
Commons Logging 是一个由 Apache 创建的第三方日志包。它的特点是可以通过修改配置文件挂接不同的日志系统，默认情况下使用的 Log4j（一个流行的日志系统），如果没有再使用 JDK Logging。

# 使用 Commons Logger

Commons Logging 定义了6个日志级别：

-   FATAL
-   ERROR
-   WARNING
-   INFO
-   DEBUG
-   TRACE

默认级别是`INFO`。



我们看一个具体实例（按照步骤顺序操作）：

1. 下载 Commons logging 包：进入[网址](https://commons.apache.org/proper/commons-logging/download_logging.cgi)下载 `commons-logging-1.2-bin.tar.gz` 文件，然后解压到与 Java 文件同目录下。在这里，就是解压到目录 `使用Commons-Logging` 下。我们进入该目录，后面的命令都在这个路径下输入。

2. 编写实例代码：在这个例子中，我们只需要使用两个 `LogFactory` 和 `Log` 两个类。
    1. 使用 `LogFactory` 获取 `Log` 对象
    2. 使用 `Log` 的方法打印日志
    
    代码如下：
    
    ```java
    import org.apache.commons.logging.Log;
    import org.apache.commons.logging.LogFactory;
    
    public class Instance {
        public static void main(String[] args) {
            Log log = LogFactory.getLog(Instance.class);
            log.info("start...");
            log.warn("end.");
        }
    }
    ```
    
3. 编译 Java 文件：在当前路径输入 `javac -cp commons-logging-1.2.jar Instance.java -encoding utf-8`，这里由于需要调用第三方包，因此需要在编译时加上 classpath 参数 `-cp`。此外，为了避免中文编码错误，需要设置 `-encoding` 参数为 utf-8。

4. 运行程序：在当前路径输入 `java -cp ".;commons-logging-1.2.jar" Instance`。在这里，classpath 由 `.` 和 `commons-logging-1.2.jar` 两部分组成，中间用 `;` 分割（如果是类 Unix 平台，需要用 `:`），这告诉 JVM 要在当前目录 `.` 下寻找名为 `commons-logging-1.2` 的 jar 包。



>   **注**：如果路径中包含中文或者空格，则第 4 步中命令的**双引号不能省略**！因此，建议以后学习或是项目中都不要包含中文或空格，这在将来很容易产生问题。



结果如下：

```bash
PS F:\Note\Java\11.异常处理\11.4.使用日志\11.4.2.使用Commons-Logging> java -cp ".;commons-logging-1.2.jar" Instance           
10月 14, 2020 11:33:42 上午 Instance main
信息: start...
10月 14, 2020 11:33:42 上午 Instance main
警告: end.
```



Commons Logging 的日志方法，例如 `info()` ，除了提供标准 `info(String)`外，还提供了有用的**重载方法 `info(String, Throwable)`**。这使得记录异常更加方便：

```java
try{

} catch (Exception e) {
	log.error("got exception!", e);
}
```

