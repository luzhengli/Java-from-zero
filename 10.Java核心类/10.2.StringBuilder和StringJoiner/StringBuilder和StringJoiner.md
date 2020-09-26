# StringBulider 类

**引例**：拼接1到1000，最后以字符串形式输出

```java
String s = "";
for (int i = 0; i < 1000; i++) {
	s = s + "," + i;
}
```

-   这个例子中，每个循环都会创建一个新的临时对象 `s`，同时老的对象将被抛弃。这样做不仅浪费内存，同时也会影响垃圾回收（GC）效率。



为了解决上述这个问题，Java 提供另一个 `StringBuilder` 类。



**Q1**：什么是 `StringBuilder` ？

A1：`StringBuilder` 是 Java 标准库提供的**可变对象**，可以**预分配缓冲区**，以实现高效的拼接字符串。



**`StringBuilder` 的特点**

1.  向 `StringBuilder` 中新增字符时，**不会创建新的临时对象**

    ```java
    public class Instance {
        public static void main(String[] args) {
            StringBuilder sb = new StringBuilder(1024);
            for (int i = 0; i < 1000; i++) {
                // sb.append(',');
                sb.append(i);
            }
            String s = sb.toString();
            // System.out.println(s);
        }
    }
    ```

2.  支持**链式操作**

    ```java
    var sb = new StringBuilder(1024);
    sb.append("Mr ").append("Bob").append("!").insert(0, "Hello, ");
    System.out.println(sb.toString()); // Hello, Mr Bob!
    ```

    -   `append()` 方法会返回 `sb` 对象本身的引用 `this`，因此`sb` 可以实现链式操作。



# StringJoiner 类

常常需要用分隔符拼接字符串，Java 提供了一个 `StringJoiner` 类来实现它：

```java
String[] names = { "Bob", "Alice", "Grace" };
var sj = new StringJoiner(" - "); // 1. 初始化 StringJoiner 时要指定分隔字符串
for (String name : names) { // 2. 遍历元素 添加到 StringJoiner
    sj.add(name);
}
System.out.println(sj.toString()); // Bob - Alice - Grace
```



还可以为待拼接的第一个子串以及最后一个子串**加上开头和结尾**，这时只需要在实例化 `StringJoin`时加上再 2 个参数：

```java
String[] names = { "Bob", "Alice", "Grace" };
var sj = new StringJoiner(", ", "Hi~", "!");  //
for (String name : names) {
	sj.add(name);
}
System.out.println(sj.toString()); // Hi~Bob, Alice, Grace!
```



# String.join() 方法

如果不需要在拼接字符串时指定开头和结尾，这时使用 `String.join()` 方法更方便，它分别接受分隔符字符串和待拼接字符串作为参数，然后返回一个拼接后的新字符串：

```java
String[] names = { "Bob", "Alice", "Grace" };
String new_names = String.join(", ", names); // 
System.out.println(new_names); // Bob, Alice, Grace
```



# 小结

1.  借助临时变量的字符串拼接方法内存消耗大且不利于垃圾回收
2.  为实现字符串的高效拼接，Java 提供了一个**`StringBuilder` 类**。它支持 **`append()` 方法**追加字符串，待拼接完成后，再使用 **`toString()` 方法**转为`String` 类型。
3.  对于普通的字符串 `+` 操作，并不需要我们将其改写为`StringBuilder`，因为Java编译器在编译时就自动把多个连续的 `+` 操作编码为 `StringConcatFactory` 的操作。在运行期，`StringConcatFactory` 会自动把字符串连接操作优化为数组复制或者 `StringBuilder` 操作
4.  字符串的拼接需要指定分隔符时，可以使用 **`StringJoiner` 类**。它实例化时传入的三个参数依次表示分隔字符串、开头字符串、结尾字符串。
5.  如果无需指定开头和结尾的字符串，可以使用 **`String.join()` 方法**拼接字符串。

