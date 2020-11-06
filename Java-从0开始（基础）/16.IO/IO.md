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





# 基于字节的IO流：InputStream / OutputStream 抽象类

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

OutputStream 是一个抽象类，它的基本方法是 `void write(int b)`，签名如下：

```java
public abstract void write(int b) throws IOException;
```

- 注：传入的 `int` 实际上只会写入一个字节，即 `int` 的低八位。



此外，`OutputStream`有个`flush()`方法，能强制把缓冲区内容输出。

通常情况，例如缓冲区写满或是关闭 `OutputStream` 时，`OutputStream` 会自动调用 `flush()` 方法。而在某些情况下需要手动调用该方法。例如我们希望在发送一条消息后，对方能够立即受到。这种情况下需要每次在输入发送内容后立即执行一次 `flush` 方法。



OutputStream 的实现类有：FileOutputStream 、ByteArrayOutputStream。

### FileOutputStream

实例1：一次写入一个字符

```javascript
static void writeFileOutputStreamByByte() throws IOException {
    try (OutputStream output = new FileOutputStream("out\\output1.txt")) {
        output.write(72); // H
        output.write(101); // e
        output.write(108); // l
        output.write(108); // l
        output.write(111); // o
    } // 会自动调用 output.close()
}
```



---

实例2：一次写入多个字符

```java
static void writeFileOutputStreamByByteArray() throws IOException{
    try (OutputStream output = new FileOutputStream("out\\output2.txt")) {
         output.write("World".getBytes("UTF-8"));  // 把字符串按“UTF-8”编码编码成字节数组
    } 
}
```

- `OutputStream .write()` 方法是一个重载方法，可以写入一个 `int` 的低八位，也可以写入一个 `byte[]`



### ByteArrayOutputStream

`ByteArrayOutputStream`可以在内存中模拟一个`OutputStream`：

```java
static void writeByteArrayOutputStream() throws IOException {
    byte[] data;
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
        output.write("Hello ".getBytes("UTF-8"));
        output.write("World.".getBytes("UTF-8"));
        data = output.toByteArray();
    }
    System.out.println(new String(data, "UTF-8"));
}
```



---

`try(resource) { ... }` 语句支持操作多个`AutoCloseable`资源，资源间用 `;`隔开。

实例：同时打开两个文件（一个输入流、一个输出流），然后把输入流的数据写入到输出流。

```java
// 读取input.txt，写入output.txt:
try (InputStream input = new FileInputStream("input.txt");
     OutputStream output = new FileOutputStream("output.txt"))
{
    input.transferTo(output); // transferTo的作用是?
}
```



## FilterInputStream 和 FilterOutputStream 抽象装饰类

FilterInputStream 和 FilterOutputStream 是两个装饰抽象类。其下的一些具体类可以装饰 InputStream 和 OutputStream，以增强它们的功能。

### 使用 ZipInputStream 读取 zip

继承关系如下：

```cmd
┌───────────────────┐
│    InputStream    │
└───────────────────┘
          ▲
          │
┌───────────────────┐
│ FilterInputStream │
└───────────────────┘
          ▲
          │
┌───────────────────┐
│InflaterInputStream│
└───────────────────┘
          ▲
          │
┌───────────────────┐
│  ZipInputStream   │
└───────────────────┘
          ▲
          │
┌───────────────────┐
│  JarInputStream   │
└───────────────────┘
```

-   ZipInputStream 可以实现 zip 包的读取
-   JarInputStream 可以实现读取jar文件里面的`MANIFEST.MF`文件（本质来说，jar 包就是加了描述文件的 zip 包）



**ZipInputStream 的基本用法**：

1.  创建一个 ZipInputStream，为其传入 FileInputStream 作为数据源。然后循环调用 `ZipInputStream.getNextEntry()` 方法（该方法会返回`ZipEntry`对象），直至返回 `null`。
2.   `ZipEntry` 可以是压缩文件或者目录（可以通过 `ZipEntry.isDirectory()`方法判断是否为目录）。如果是压缩文件，可以直接使用 `ZipEntry.read()` 方法读取，直至返回 `-1`。



实例：

```java
try (ZipInputStream zip = new ZipInputStream(new FileInputStream("src\\files\\test1.zip"))) {
    ZipEntry entry = null;
    while ((entry = zip.getNextEntry()) != null) { // 遍历zip实体，直至为null
        if (!entry.isDirectory()) { // 如果zip实体不是目录可以读取字节
            int n;
            while ((n = zip.read()) != -1) { // 读取zip输入流
                System.out.println((char) n);
            }
        }
    }
}
```

输出：

```
H
e
l
l
o
```



### 使用 ZipOutputStream 写入 zip

**ZipOutputStream 的基本用法**：

1.  遍历每一个待写入的 File 对象 file
    1.  使用 `ZipOutputStream.putNextEntry()` 方法并传入 `ZipEntry` 对象
    2.  使用 `ZipOutputStream.write()` 方法写入数据，对于 File 的数据源可以是 `getFileDataAsBytes(file)`
    3.  调用 `ZipOutputStream.closeEntry()` 方法结束 zip 打包。



实例：

```java
public static void main(String[] args) throws Exception {
    try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("src\\files\\testAll.zip"))) {
        File[] files = { new File("src\\files\\test1.txt"), new File("src\\files\\test2.txt"),
            new File("src\\files\\test3.txt"), };
        for (File file : files) {
            zip.putNextEntry(new ZipEntry(file.getName())); // 创建 ZipEntry
            zip.write(getFileDataAsBytes(file)); // 写入数据调用子实现的方法：getFileDataAsBytes
            zip.closeEntry(); // 关闭 ZipEntry
        }
    }
}

static byte[] getFileDataAsBytes(File file) throws IOException {
    // 根据File获取到其bytes
    byte[] bytes = new byte[100];
    try (InputStream input = new FileInputStream(file)) {
        int n;
        while ((n = input.read(bytes)) != -1) ;
    }
    return bytes;
}
```



### 使用 PrintStream 输出数据

PrintStream 继承了 FilterOutputStream，它为 OutputStream 接口提供了一组写入方法：

-   写入`int`：`print(int)`
-   写入`boolean`：`print(boolean)`
-   写入`String`：`print(String)`
-   写入`Object`：`print(Object)`，实际上相当于`print(object.toString())`

以及对应的一组`println()`方法。



Java 内置提供了几个 PrintStream：

-   System.out：标准输出
-   System.err：标准错误输出



PrintStream 相较于 OutputStream 的好处在于：

1.  提供了一组打印方法
2.  不会抛出 `IOException`，使得编写代码时无需捕获它



**有话想说**：没想到最常用的输入输出函数背后其实是一个装饰器 `PrintStream`！有意思。此外，知道它的本质之后，以后可以简写输出方法了，例如

```java
PrintStream out = System.out;
out.println("123");
```



# 基于字符的IO流：Reader / Writer 抽象类

字符流传输的最小数据单位是`char`，按照`char`来读写。

Reader 和 Writer 本质上是一个能自动编解码的 InputStream 和 OutputStream。



## Reader

`Reader`是Java的IO库提供的另一个输入流接口。和`InputStream`的区别是，**`InputStream`是一个字节流，即以`byte`为单位读取，而`Reader`是一个字符流，即以`char`为单位读取**：

| InputStream                         | Reader                                |
| :---------------------------------- | :------------------------------------ |
| 字节流，以`byte`为单位              | 字符流，以`char`为单位                |
| 读取字节（-1，0~255）：`int read()` | 读取字符（-1，0~65535）：`int read()` |
| 读到字节数组：`int read(byte[] b)`  | 读到字符数组：`int read(char[] c)`    |



`java.io.Reader`是所有字符输入流的超类，它最主要的方法是：

```java
public int read() throws IOException;
```

这个方法读取字符流的下一个字符，并返回字符表示的`int`，范围是`0`~`65535`。如果已读到末尾，返回`-1`。



### Filereader

FileReader 是 Reader 的子类。使用 `int read()` 方法逐字符读取 FileReader 的代码：

```java
try (Reader reader = new FileReader("src\\files\\test1.txt", StandardCharsets.UTF_8)) {  
    int n;
    while ((n = reader.read()) != -1) {
        System.out.printf("%c", n);
    }
}
```

-   按字符读取必须在打开字符输入流时指定编码，例如这里的 `StandardCharsets.UTF_8`



输出：

```cmd
Hello，这是一个测试文件！
```

---

还可以使用 `int read(char[] cbuf)` 方法一次读取多个字符到 cbuf，代码如下：

```java
try (Reader reader = new FileReader("src\\files\\test1.txt", StandardCharsets.UTF_8)) {
    char[] buffer = new char[3]; 
    int n;
    while ((n = reader.read(buffer)) != -1) {
        System.out.println(buffer);
    }
}
```



输出：

```cmd
Hel
lo，
这是一
个测试
文件！
```



### CharArrayReader

`CharArrayReader`可以在内存中模拟一个`Reader`，它的作用实际上是把一个`char[]`数组变成一个`Reader`，这和`ByteArrayInputStream`非常类似：

```java
try (Reader reader = new CharArrayReader("Hello".toCharArray())) {
}
```



### StringReader

**`StringReader`可以直接把`String`作为数据源**，它和`CharArrayReader`几乎一样：

```java
try (Reader reader = new StringReader("Hello")) {
}
```



## Writer

省略不表。可见：https://www.liaoxuefeng.com/wiki/1252599548343744/1298366912790561



## 使用 InputStreamReader 将任何 InputStream 转换为 Reader

-   除了特殊的`CharArrayReader`和`StringReader`，普通的`Reader`实际上是基于`InputStream`构造的
-   `Reader` 要从`InputStream`中读入字节流（`byte`），再将编码设置为`char`就可以实现字符流
-   从源码来看，FileReader 持有一个 FileReader
-   因此，**`Reader` 本质是一个基于`InputStream`的`byte`到`char`的转换器**



如果现在有一个 `InputStream`，可以通过设置编码将其转为 `Reader`。实现转换的转换器是 `InputStreamReader`。实现代码如下：

```java
try (Reader reader = new java.io.InputStreamReader(new FileInputStream("src\\files\\test1.txt"),
                StandardCharsets.UTF_8);) {
    char[] buffer = new char[100];
    int n;
    while ((n = reader.read(buffer)) != -1) {
        System.out.println(buffer);
    }
}
```



# 读取 classpath 资源

暂时不表



# 序列化和反序列化

暂时不表





# 小结

- InputStream 和 OutputStream
    - InputStream 和 OutputStream 是**基于字节**的输入输出流，本质是一个抽象类。其具体实现类有 FileInputStream（文件输入流）、ByteArrayInputStream（内存中的字节数组输入流）；FileOutPutStream（文件输出流）、ByteArrayOutputStream（内存中的字节数组输出流）。
    - InputStream 提供的 `int read()` 方法可以读取数据（最小单位是字节），它是一个重载方法，可以按字节一个个读，也可以通过缓存一次性读多个字节。
    - OutputStream 提供的 `void write()` 方法可以写入数据（最小单位是字节），它是一个重载方法，可以按字节一个个写，也可以通过缓存一次性写多个字节。
    - `public long InputStream.transferTo(OutputStream out)` 可以实现讲输入流的数据全部写入到输出流中。通过该方法可以实现文件的复制。
- try(source) 语句
    - 为了保证输入/出流正确关闭，因此需要在 `try(source){...}`语句中进行实例化和执行相应的操作语句。
    - `try(source){...}`语句支持打开多个流，不同的资源间用 `;` 分割。
- FilterInputStream 和 FilterOutputStream 
    - InputStream 和 OutputStream 各自提供了 FilterInputStream 和 FilterOutputStream 两个装饰抽象类，这两个抽象类下面有各自具有 ZipInputStream、DataInputStream（或ZipOutputStream、DataOutputStream） 等具体装饰器类。可以使用装饰器增强 InputSteam 和 OutputStream 的功能。
    - FileInputStream 和 FileOutputStream 分别借助 ZipInputStream 和 ZipOutputStream 的装饰可以实现 zip 的读写。
- Reader 和 Writer 是**基于字符**的读写流，它们提供和 InputStream&OutputSteam 类似的读写方法。读写时注意需要指定字符编码。
- 使用 InputStreamReader，可以把一个 InputStream 转换成一个 Reader。

