# 通过一个例子了解泛型
实例：`ArrayList` 实现任意对象的数组形式存储

详见：https://www.liaoxuefeng.com/wiki/1252599548343744/1265102638843296

# 接口泛型

实例：实现自定义对象 `Person` 的排序

详见：`App.java` 和 `Person.java`

# 编写泛型

实例：单个泛型类型和多个泛型类型的编写

详见：`App.java` 和 `Pair.java`

# Java 的泛型是通过擦拭法实现

## 擦拭法

**Java 的泛型是由编译器实现的**，而虚拟机并不知道什么是泛型。具体来说编译器做了两件事情：

1. 将 `<T>` 视为 `Object`
2. 根据 `<T>` 的类型自动的实现安全的强制转型

这种 Java 实现泛型的方式被称作**擦拭法**。



**例子**：

我们编写了一个泛型：

```java
public class Pair<T> {
    private T first;
    private T last;
    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }
    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }
}
```

经过编译器处理，虚拟机需要执行的代码变成了：

```java
public class Pair {
    private Object first;
    private Object last;
    public Pair(Object first, Object last) {
        this.first = first;
        this.last = last;
    }
    public Object getFirst() {
        return first;
    }
    public Object getLast() {
        return last;
    }
}
```

使用泛型的时候，我们编写的代码也是编译器看到的代码：

```java
Pair<String> p = new Pair<>("Hello", "world");
String first = p.getFirst();
String last = p.getLast();
```

而虚拟机执行的代码并没有泛型：

```java
Pair p = new Pair("Hello", "world");
String first = (String) p.getFirst(); // 编译器进行了自动安全的强制转型
String last = (String) p.getLast();
```



## Java 泛型的局限

了解到泛型是由擦拭法实现的，那么可以知道 Java 泛型的局限：

1. **`<T>`不能是基本类型**，例如`int`。由于实际的类型是 `Object`，而 `Object` 无法持有基本类型。

2. 不能取得泛型类的 `class`，不同泛型类型类的 `class` 都是一样的。即不存在 `Pair<T>.class`，只有唯一的 `Pair.class`。下面是一个例子

    ```java
    import static java.lang.System.out;
    import packg3.Pair; // 详见 `packg3.Pair.java`
    
    public class App {
        public static void main(String[] args) throws Exception {
            // 擦拭法
            Pair<String, Integer> p1 = new Pair<String, Integer>("first", 20);
            Pair<Float,String> p2 = new Pair<Float,String>(3.21f, "last");
            Class c1 = p1.getClass();
            Class c2 = p2.getClass();
            
            out.println(c1);
            out.println(c1);
            out.println(c1 == c2);
            out.println(c1 == Pair.class);
            out.println(c2 == Pair.class);
        }
    }
    ```

    输出：

    ```
    class packg3.Pair
    class packg3.Pair
    true
    true
    true
    ```

3. 无法判断带泛型的类型，理由同 2。例子，以下代码是不合法的

    ```java
    Pair<String, String> p = new Pair<>("123", "456");
    // Compile error:
    if (p instanceof Pair<String, String>) {
    }
    ```

4. 不能实例化 `<T>`

# 总结

- 从功能上来说，泛型是一个模板。它的优点是避免了强制类型转换，而类型的正确性交由编译器检查。
- 泛型之间具有继承关系：可以把`ArrayList<Integer>`（ `ArrayList<T>`） 向上转型为`List<Integer>`（`List<T>`） ，而不能把 `ArrayList<Integer>` 向上转型为 `ArrayList<Number>`（理解：Number 这个“篮子”中必须装同一种子类的对象，不然取数时就容易使用错误的引用类型）
- 可以在接口中定义泛型，实现此接口的类必须实现正确的泛型类型。
- 可以省略编译器能自动推断出的类型（建议不要省略），例如：`List<String> list = new ArrayList<>();`
- 不指定泛型类型时，编译器默认将其视为 `Object` 类型。
- **泛型类型必须是引用类型**
- 编写泛型
    - 可以根据一个类改写成泛型类，需要定义泛型类型`<T>`
    - 静态方法的泛型类型不能和类中其他方法和字段的泛型类型一样，除此之外，还需要在 `static` 后加上泛型类型

