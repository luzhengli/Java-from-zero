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





# 小结

1.  传统借助临时变量的字符串拼接方法内存消耗大且不利于垃圾回收
2.  为实现字符串的高效拼接，Java 提供了一个**`StringBuilder` 类**
3.  对于普通的字符串 `+` 操作，并不需要我们将其改写为`StringBuilder`，因为Java编译器在编译时就自动把多个连续的 `+` 操作编码为 `StringConcatFactory` 的操作。在运行期，`StringConcatFactory` 会自动把字符串连接操作优化为数组复制或者 `StringBuilder` 操作

