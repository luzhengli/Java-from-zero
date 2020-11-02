> 这部分谈的都是同步IO（Java 提供了 `java.io` 包支持），`InputStream`、`OutputStrea`、`Reader` 和`Writer` 都是同步IO的抽象类，对应的具体实现类，以文件为例，有 `FileInputStream`、`FileOutputStream`、`FileReader` 和 `FileWriter`。



# 使用 File 对象操作文件和目录

> Java的标准库`java.io`提供了`File`对象来操作文件和目录

Q：如何**创建 File 对象**？

A：构造 File 对象时需要传文件路径，支持绝对和相对路径。

注：

1. windows 下路径分隔符是 `\\`，Linux 下路径分隔符是 `/` 。可以使用 `File.separator` 静态变量查看当前系统的分隔符。
2. Java的标准库`java.io`提供了`File`对象来操作文件和目录



实例：

```java
// 假设当前目录是C:\Docs
File f1 = new File("sub\\javac"); // 绝对路径是C:\Docs\sub\javac
File f3 = new File(".\\sub\\javac"); // 绝对路径是C:\Docs\sub\javac
File f3 = new File("..\\sub\\javac"); // 绝对路径是C:\sub\javac
```



---

Q：如何**返回 File 的路径**？

A：有三种形式

1. `getPath()`：返回构造方法传入的路径
2. `getAbsolutePath()`：返回绝对路径
3. `getCanonicalPath`：它和绝对路径类似，但是返回的是规范路径



注：什么是规范路径？

- 绝对路径可以表示成`C:\Windows\System32\..\notepad.exe`，而规范路径就是把`.`和`..`转换成标准的绝对路径后的路径：`C:\Windows\notepad.exe`。

---

Q：给 File 传入路径时，只要不进行磁盘操作，即使文件和目录不存在也不会报错。那么如何**判断文件和目录是否存在**？

A：

1. 判断该`File`对象是否是一个已存在的文件：`File.isFile()`
2. 断该`File`对象是否是一个已存在的目录：`File.isDirectory()`



---

Q：如何**判断文件的权限和大小**？

A：

- `boolean canRead()`：是否可读；
- `boolean canWrite()`：是否可写；
- `boolean canExecute()`：是否可执行；对目录而言，是否可执行表示能否列出它包含的文件和子目录。
- `long length()`：文件字节大小。



---

Q：如何**创建和删除文件**？

A：

- 创建新文件：`File.createNewFile()`
- 删除新文件：`File.delete()`



注：读写临时文件

- 创建临时文件：`File.createTempFile()`
- 在JVM退出时自动删除该文件：`File.deleteOnExit()`



---

Q：如何**创建和删除目录**？

A：

- `boolean mkdir()`：创建当前File对象表示的目录；
- `boolean mkdirs()`：创建当前File对象表示的目录，并在必要时将不存在的父目录也创建出来；
- `boolean delete()`：删除当前File对象表示的目录，当前目录必须为空才能删除成功。



---

**Q：如何遍历文件和目录？**

A：使用`list()`和`listFiles()`列出目录下的文件和子目录名。其中 `listFiles()` 还提供了很多重载方法，可以支持过滤出指定文件。



实例：过滤出当前目录下所有的可执行文件

```java
File[] fList2 = f.listFiles(new FilenameFilter() {
    public boolean accept(File dir, String name) {
        return name.endsWith(".exe");
    }
});
```

- `FilenameFilter` 提供了根据名称进行过滤的功能，具体来说需要重写 accept 方法。
- 

---

Java标准库还提供了一个`Path` 和 `Paths` 对象，它位于`java.nio.file`包。

- 通过 `Paths`.get(Str1, Str2, ...)` 方法可以直接根据输入的字符串拼接得到路径
- `Paths` 可以直接遍历得到 `Path`



实例：

```java
Path p = Paths.get("D:", "Anaconda");
out.println(p); // D:\Anaconda\bin
for (Path path : Paths.get("..").toAbsolutePath()) {
    out.println("   " + path);
}
```

输出：

```powershell
D:\Anaconda
Li
Note
Java
Java-从0开始（基础）
16.IO
File
File
..
```





# InputStream / OutputStream

IO流以`byte`（字节）为最小单位，因此也称为*字节流*。



# Reader / Writer

字符流传输的最小数据单位是`char`，按照`char`来读写。

Reader 和 Writer 本质上是一个能自动编解码的 InputStream 和 OutputStream。









