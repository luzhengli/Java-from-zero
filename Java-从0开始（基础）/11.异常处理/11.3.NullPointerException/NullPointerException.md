# 什么是 NullPointerException ？
NullPointerException 简称 NPE，叫做空指针异常。如果一个对象为 `null`，那么调用它的方法或字段就会抛出 NullPointerException。

Java 中并无指针的概念，我们定义的变量实际上是引用。因此似乎把 NullPointerException 叫做 NullReferenceException 更为贴切，但是这里只是 Java 借鉴 C 的一种写法，并无大碍。
# 如何处理 NullPointerException ？
1. NullPointerException 是一种代码逻辑错误，遇到它遵循原则是**早暴露，早修复，严禁使用catch来隐藏这种编码错误**：
2. 为了避免产生 `null`，我们需要在初始化或是返回时多用空字符串 `""` 或 空数组 `new String[0]` 来替代 `null`。
3. 如果调用方一定要根据`null`判断，比如返回`null`表示文件不存在，那么考虑返回`Optional<T>`：
```java
public Optional<String> readFromFile(String file) {
    if (!fileExist(file)) {
        return Optional.empty();
    }
    ...
}
```
这样调用方必须通过 `Optional.isPresent()` 判断是否有结果。

