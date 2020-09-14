**问题**：VSCode 的 Code Runner 插件支持直接在输出栏输出代码结果（快捷键：Ctrl+Alt+N），由于它默认的编码是 GBK 而 Java 文件默认是 UTF-8，因此输出的内容会显示乱码。例如打印一个字符 a 的 Java 程序：

![image-20200914120415064](https://gitee.com/llillz/images/raw/master/image-20200914120415064.png)



为了解决这个问题，有**一种思路**：将 Java 代码在 VSCode 执行的终端下运行，并设置终端的编码为 UTF-8。具体操作如下：



进入 文件-首选项-设置，点击右上角的 打开设置（json），进入 json 形式的设置文件。



在 json 文件中添加如下内容：

```json
"code-runner.runInTerminal": true,  // 设置 run code 默认在终端下运行
    "code-runner.executorMap": {  // run code 命令映射——进入当前 java 文件所在目录 然后编译并执行 java
        "java": "cd $dir && javac -encoding utf-8 $fileName && java $fileNameWithoutExt"
    },
```



这里新添加了两行配置，第一个表示在键入 Ctrl+Alt+N 时默认在终端执行代码。第二个表示将 run-code（即键入 Ctrl+Alt+N 时的相应）命令映射到 “进入当前文件夹，以 UTF-8 编码编译当前 Java 文件，执行 Java 字节码”。



重新执行代码，发现结果正确返回：

![image-20200914120259877](https://gitee.com/llillz/images/raw/master/image-20200914120259877.png)



**值得注意**一点是，使用上述方法要先打开 Java 所在的目录。