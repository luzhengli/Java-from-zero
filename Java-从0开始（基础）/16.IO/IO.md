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



## InputStream 

`InputStream` 是一个抽象类，它的基本方法是 `read()`，签名如下：

```java
public abstract int read() throws IOException;
```

-   功能：**读取**输入流的下一个**字节**，并返回字节表示的`int`值（0~255）。如果已读到末尾，返回`-1`表示不能继续读取。



InputStream 的实现类有 `FileInputStream` 和 `ByteArrayInputStream`。



### FileInputStream

`FileInputStream` 是 `InputStream` 的子类，用于从文件流中读取字节**。一个个字节的读取**文件流的所有字节的代码如下：

```java
public static void readFile() throws IOException {
    try(InputStream input = new FileInputStream("README.md")){
        int n;
        while((n=input.read())!=-1){
            System.out.println(n);
        }
    } // 编译器在此自动为我们写入finally并调用close()
}
```

-   **Java 7 引入的新的`try(resource){...}`语法，只需要编写`try`语句，让编译器自动为我们关闭资源**
-   编译器只看`try(resource = ...)`中的对象是否实现了**`java.lang.AutoCloseable`接口**，如果实现了，就自动加上`finally`语句并调用`close()`方法
-   `InputStream`和`OutputStream`都实现了这个接口，因此，都可以用在`try(resource)`中



---

可以**利用缓冲一次读取多个字节**，`InputStream`提供了两个重载方法来支持读取多个字节：

-   `int read(byte[] b)`：读取若干字节并填充到`byte[]`数组，返回读取的字节数
-   `int read(byte[] b, int off, int len)`：指定`byte[]`数组的偏移量和最大填充数

上述的`read()`方法的返回值不再是字节的`int`值，而是返回实际读取了多少个字节。如果返回`-1`，表示没有更多的数据了，读取到的字节保存在 `byte[]` 中。示例代码如下：

```java
public static void readFileBytes() throws IOException {
    try (InputStream input = new FileInputStream("README.md")) {
        byte[] buffer = new byte[100]; // 设置缓冲区大小
        int n;
        while ((n = input.read(buffer)) != -1) {
            System.out.println("已经读取" + n + "个字节");
            // for (byte b : buffer) { // 遍历bytes
            //     System.out.println(b);
            // }
        }
    }
}
```





---

实例：读取所有字符，在内存中拼接成字符串

```java
static void concatString() throws IOException {
    byte[] bytes = { 72, 101, 108, 108, 111, 33 };
    StringBuilder sb = new StringBuilder(16);
    try (InputStream input = new ByteArrayInputStream(bytes)) {
        int n;
        while ((n = input.read()) != -1) {
            sb.append((char) n);
        }
        System.out.println(sb.toString());
    }
}
```





### ByteArrayInputStream

`ByteArrayInputStream`可以在内存中模拟一个`InputStream`。

看一个实例：如何通过 `byte[]` 构造出 `ByteArrayInputStream`，然后读取它

```java
static void readByteArrayInputStream() throws IOException {
	byte[] bytes = { 72, 101, 108, 108, 111, 33 };
        try (InputStream input = new ByteArrayInputStream(bytes)) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char) n); // H e l l o !
        }
    }
}
```

-   `ByteArrayInputStream` 实际上是把一个 `byte[]` 数组在内存中变成一个 `InputStream`



## OutputStream



# Reader / Writer

字符流传输的最小数据单位是`char`，按照`char`来读写。

Reader 和 Writer 本质上是一个能自动编解码的 InputStream 和 OutputStream。



# 小结

-   InputStream 是输入流，本质是一个抽象类。其具体实现类有 FileInputStream（文件输入流）、ByteArrayInputStream（内存中的字节数组输入流）。
-   InputStream 提供的 `int read()` 方法可以读取数据（最小单位是字节），它是一个重载方法，可以按字节一个个读，也可以通过缓存一次性读多个字节。
-   为了保证输入流正确关闭，因此需要在 `try(source){...}`语句中进行实例化和执行相应的操作语句。