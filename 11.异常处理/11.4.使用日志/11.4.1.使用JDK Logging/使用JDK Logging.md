>   日志取代了 `System.out.println()` 的作用，它还具有下面的好处：
>
>   1.  可以设置输出样式，避免自己每次都写"ERROR: " + var；
>   2.  可以设置输出级别，禁止某些级别输出。例如，只输出错误日志；
>   3.  可以被重定向到文件，这样可以在程序运行结束后查看日志；
>   4.  可以按包名控制日志级别，只输出某些包打的日志；
>   5.  等等

# 使用 Logger

Java 默认的日志包是 `java.util.logging`。它提供了从严重到普通的7个日志级别：

-   SEVERE
-   WARNING
-   INFO
-   CONFIG
-   FINE
-   FINER
-   FINEST



实例：使用 `logging` 包的 `Logger` 类打印异常信息

```java
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

public class Instance {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Instance.class.getName());
        logger.info("Start process...");
        try {
            "".getBytes("invalidCharsetName");
        } catch (UnsupportedEncodingException e) {
            logger.severe(e.toString());
        }
        logger.fine("ok！"); // INFO 以下级别的日志不会被输出
        logger.info("Process end.");

    }
}
```

输出：

```
10月 13, 2020 9:45:01 下午 Instance main
信息: Start process...
10月 13, 2020 9:45:01 下午 Instance main
严重: java.io.UnsupportedEncodingException: invalidCharsetName
10月 13, 2020 9:45:01 下午 Instance main
信息: Process end.
```



# Logger 的优缺点

优点：

-   一般日志具备的优点
-   `logging` 默认的级别是 INFO，因此其以下级别不会被打印出来。因此可以屏蔽很多不必要的输出。

缺点：

-   在 JVM 启动时读取配置文件并完成初始化，一旦开始运行`main()`方法，就无法修改配置
-   配置不方便，需要在 JVM 启动时传递参数 `-Djava.util.logging.config.file=<config-file-name>`





