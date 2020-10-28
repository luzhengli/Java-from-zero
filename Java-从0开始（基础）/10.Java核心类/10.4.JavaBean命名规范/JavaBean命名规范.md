# JavaBean

**Q1**：JavaBean 是什么？

A1：**JavaBean 是 符合一种命名规范的 `class`**，它通过一组读方法（`getter`）和写方法（`setter`）来定义属性。



具体来说，**读方法和写方法的命名规则**是：

1.  字段是 xyz，则读写方法的开头分别是 `get` 和 `set` ，然后加上大写字母开头的字段名 `Xyz`。示例如下

    ```java
    // 读方法:
    public Type getXyz()
    // 写方法:
    public void setXyz(Type value)
    ```

2.  `boolean`字段比较特殊，它的读方法一般命名为`isXyz()`。



---

**Q2**：什么是属性？`属性 == 字段` 吗？

A2：

1.  属性是指一对读写方法。举个例子，如果某个类具有下面这一对方法：

    ```java
    void setA(String s){}
    String getA(){}
    ```

    那我们可以说这个类**具有一个可读写的 `a` 属性**。如果仅有 `setA` 方法，那我们可以说这个类具有一个只读属性 `a`。当调用 `setA` 方法时可以说设置 `name` 属性，当调用 `getA` 方法时可以说获取了 `name` 属性。

2.  `属性 != 字段` ，字段是成员变量，用于承载数据，而属性指的是读写方法，不是一个东西。



# 枚举 JavaBean 属性及对应方法

要枚举一个 JavaBean 的所有属性以及对应的读写方法，可以直接使用Java核心库提供的**`Introspector`类**，具体代码可遵循下面的模板：

```java
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class Instance {
    public static void main(final String[] args) throws Exception {
        BeanInfo info = Introspector.getBeanInfo(Person.class); // 获取 JavaBean 的信息
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) { // 获取所有属性的信息
            System.out.println(pd.getName()); // 属性
            System.out.println("\t" + pd.getReadMethod()); // 读方法
            System.out.println("\t" + pd.getWriteMethod()); // 写方法
        }
    }
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```



输出：

```
age
        public int Person.getAge()
        public void Person.setAge(int)
class
        public final native java.lang.Class java.lang.Object.getClass()
        null
name
        public java.lang.String Person.getName()
        public void Person.setName(java.lang.String)
```

-   **注**：`class`属性是从`Object`继承的`getClass()`方法带来的。



# 小结

1.  JavaBean 是 符合一种命名规范的 `class`，它通过一组读方法（`getter`）和写方法（`setter`）来定义属性。
2.  属性和字段不是相同概念。前者一般指代一对读写方法，可以说某个 `name` 属性具有一对读写方法；后者指的是类的成员变量。
3.  `Introspector.getBeanInfo()`可以获取属性列表，进而枚举属性及其读写方法，具体代码有一套模板可以套用。