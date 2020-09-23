> 本节笔记还要补充！由于理解不深，因此目前主要是摘录廖雪峰教程。	

# classpath

Q1：什么是 `classpath`？

A1：`classpath` 是 JVM 用到的一个环境变量，它用来指示 JVM 如何搜索 `.class` 文件（因为 `.class` 文件才是可以被 JVM 执行的字节码）。例如要加载一个 `abc.xyz.Hello` 的类，应该去搜索对应的 `Hello.class `文件，这时就需要知道 `classpath`。



Q2：`classpath` 具体长什么样？

A2：`classpath` 就是一组目录的集合，它设置的搜索路径与操作系统相关。例如，在Windows系统上，用 `;` 分隔，带空格的目录用 `""` 括起来，可能长这样：

```
C:\work\project1\bin;C:\shared;"D:\My Documents\project1\bin"
```



看一个实例，我们假设 `classpath` 是`.;C:\work\project1\bin;C:\shared`，当JVM在加载`abc.xyz.Hello`这个类时，会依次查找：

- ，<当前目录>\abc\xyz\Hello.class
- C:\work\project1\bin\abc\xyz\Hello.class
- C:\shared\abc\xyz\Hello.class



如果JVM在某个路径下找到了对应的 `class` 文件，就不再往后继续搜索。如果所有路径下都没有找到，就报错。



Q3：如何设定 `classpath`？

A3：有两种方法

1. 系统环境变量中设置`classpath`环境变量，不推荐。
2. 启动 JVM 时设置 `classpath` 变量，推荐。



Q4：启动 JVM 时如何设置 `classpath` 变量？

A4：通过 `java` 命令运行字节码程序时传入 `-classpath` 或 `-cp`：

```
java -classpath .;C:\work\project1\bin;C:\shared abc.xyz.Hello
```

或

```
java -cp .;C:\work\project1\bin;C:\shared abc.xyz.Hello
```

如果未设置系统变量，也未传入 `-cp` 参数，则 JVM 默认的 `classpath` 为 `.`，即当前目录。



Q5：我们编写程序会使用到核心库的 `class`，如 `String`，`ArrayList` 等。那 JVM 如何找到这些 `class` ？

A5：JVM 不依赖 `classpath` 加载核心库！保持默认即可（*这里其实没有细致的讲解到底如何找的*）。



# jar 包

Q6：jar 包是什么？

Q6：基本上就是一个 zip 包，但和 zip 包有一个区别。



Q7：有什么作用？

A7：它可以把 `package` 组织的目录层级，以及各个目录下的所有文件（包括 `.class` 文件和其他文件）都打成一个 jar 文件。



Q8：zip 包和 jar 包的区别？

A9：

- jar 文件的内容中，包含了一个 META-INF/MANIFEST.MF 文件，该文件是在生成 jar 文件的时候自动创建的，作为 jar 里面的"详情单"，包含了该 jar 包的版本、创建人和类搜索路径 `classpath` 等信息。
- 如果是可执行 jar 包，会包含 Main-Class 属性，表明 Main 方法入口，尤其是较为重要的 Class-Path 和 Main-Class。



# 小结

1. 待我搞清楚 Java 程序（多个 `.java` 源文件）的编译执行流程再来补充。

