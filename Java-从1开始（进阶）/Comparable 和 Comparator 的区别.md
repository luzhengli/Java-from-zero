> 本节代码路径：Comparable_and_Comparator

Comparable 就 Comparator 都是 Java 的接口，它们之间名字上很相似，但是有什么的关系呢？

# Comparable
Comparable 的作用：类通过实现 Comparable 接口，并重写其 `compareTo()` 方法，就可以按照指定规则比较自身类的对象。



Comparable 接口的定义如下：

```java
public interface Comparable<T> {
	public int compareTo(T o);
}
```

- 某对象调用`compareTo()` 方法返回负数、正数或0分别表示该对象与要比较的对象相比要大、小或者相等。



**实例**：定义一个 Student 类，并按照 score 进行同类的比较

```java
public class Student implements Comparable<NewStudent> {
    public String name;
    public int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student o) { // 重写 compareTo() 方法
        return -(this.score - o.score);
    }
}
```



# Comparator

有时候，我们想让类保持它的原貌，不想主动实现 Comparable 接口，但又希望它们进行比较，这时就可以使用 Comparator（比较器）。



Comparator 接口的定义较为复杂，但其核心方法只有两个：

```java
@FunctionalInterface
public interface Comparator<T> {
	int compare(T o1, T o2);
    boolean equals(Object obj);
}
```

- `compare(T o1, T o2)` 的返回值可能为负数，零或者正数，代表的意思是第一个对象小于、等于或者大于第二个对象。
- `equals(Object obj)` 需要传入一个 Object 作为参数，并判断该 Object 是否和 Comparator 保持一致。 // ?



实例：我们还是使用之前的 Student，但这次我们不在类定义中重写 `compareTo()`方法：

```java
package packg;

public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student " + getName() + ":" + getAge();
    }
}
```

然后通过 Comparator 定义比较器，这里我们可以根据 name 和 score 分别定义两个比较器：

```java
/*CompareByAge.java*/
import java.util.Comparator;

public class CompareByAge implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getAge() - o2.getAge();
    }
}
```

```java
/*CompareByName.java*/
import java.util.Comparator;

public class CompareByName implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName()); // 英文字符串根据 ASCII 码比较
    }
}
```

我们分别调用两个比较器，进行排序，然后查看结果：

```java
List<Student> list = new ArrayList<>();
list.add(new Student("wang2", 20));
list.add(new Student("wang3", 10));
list.add(new Student("wang1", 30));

System.out.println("按照名字排序：");
list.sort(new CompareByName()); // 排序时传入比较器
System.out.println(list);
System.out.println("按照年龄排序：");
list.sort(new CompareByAge()); // 排序时传入比较器
System.out.println(list);
```

输出：

```cmd
按照名字排序：
[Student wang1:30, Student wang2:20, Student wang3:10]
按照年龄排序：
[Student wang3:10, Student wang2:20, Student wang1:30]
```

# 结论

我们可以看到，Comparable 需要在定义类时实现。而 Comparator 可以定制各种比较方式，在需要时作为参数传入相应的方法（例如 `list.sort`）即可。



建议

- 如果只需要一种固定的比较方式，并且可以接收在类定义时加入比较的逻辑，推荐使用 Comparable。
- 如果需要多种比较方式，且不希望修改类本身，推荐使用 Comparator。



# Reference

1. 来吧，一文彻底搞懂Java中的Comparable和Comparator https://zhuanlan.zhihu.com/p/101583223

