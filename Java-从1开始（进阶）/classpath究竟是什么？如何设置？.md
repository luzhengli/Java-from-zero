Q：classpath 是什么？

A：classpath 本质是一系列路径，用于指示 JVM 寻找依赖的 `.class` 和 jar 包。



classpath 包括：

1.  jar 包的路径 //  JVM 可以自己找到 jar 包中需要的类
2.  包层次结构的顶层路径。例如你把编写的类都放在 `out` 目录下，那么这里 `out` 就是 classpath



---

Q：如何修改 classpath？

A：windows 下有两种：

1.  修改 CLASSPATH 环境变量，多个变量值用 `;` 分割

2.  在使用 `java` 命令时传入 `-cp` 变量。例如

    ```cmd
    java -cp "/home/myaccount/myproject/lib/CoolFramework.jar:/home/myaccount/myproject/output/"  MyMainClass
    ```



不建议设置 CLASSPATH 环境变量，因为这是一个全局变量。它在某些程序上表现很好，但可能在另一些程序上会造成问题。



# Reference

1.  What is a classpath and how do I set it? [What is a classpath and how do I set it?](https://stackoverflow.com/questions/2396493/what-is-a-classpath-and-how-do-i-set-it) https://stackoverflow.com/questions/2396493/what-is-a-classpath-and-how-do-i-set-it