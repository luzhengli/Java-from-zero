>   有关代码部分没有深入分析，需要时解决一下。

Q：Java 的 `Class` 是什么的？

A：

-   区别于面向对象的类（抽象概念），Java 的 `Class` 是一个实实在在的类，它定义在 `java.lang` 包中。
-   由于 `Class` 是一个实在的类，因此可以拥有实例，即 `Class` 对象。
-   Java 里，所有的类的根源都是 Object 类，Class 也不例外，它是继承自 Object 的一个特殊的类。
-   Java 中的类会持有一个 `Class` 对象



---

Q：Java 的 `Class` 有什么作用？

A：`Class` 内部可以记录类的成员、接口等信息。即它是用于表示其他类的一种类。



*实例：代码（还没深入分析）*



---

Q：如何获取到某个类c的实例p的 `Class` 对象，即 `Class` 的实例？

A：有三种方法

1.  使用对象p的 `getClass()` 方法
2.  使用 `Class` 的静态方法 `forName(className)` ，通过类名获取 `Class` 实例，其中 `className` 是实例p的类名
3.  使用`类名.class` 获取 `Class` 实例

```java
var p = new Person("li", 23);
System.out.println(p.getName() + ": " + p.getAge());
Object c1 = p.getClass();
System.out.println(c1);
Object c2 = Class.forName("Person");
System.out.println(c2);
Object c3 = Person.class;
System.out.println(c3);
```



---



# Reference

1.  https://www.cnblogs.com/flyme/p/4571030.html