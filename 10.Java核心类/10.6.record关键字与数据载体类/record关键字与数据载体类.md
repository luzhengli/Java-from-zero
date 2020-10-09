# 使用record关键字定义数据载体类

Q1：什么是不变类？

A1：`String`、`Integer`等类型的时候，这些类型都是不变类。它们满足**两个特点**

1. class 使用 `final` 修饰，因此无法被继承。
2. 所有字段使用 `final` 修饰，因此实例化后无法修改字段。



---

Q2：什么是纯数据载体类（plain data carriers）？如何定义？

A2：在开发过程中，有时我们会**想要将相关字段作为不可变数据组织在一起**。我们把这些数据用一个类承载，这个类就被称作纯数据载体类。



Java14 之前，我们可能会想到通过定义一个不变类来实现它。假如我们希望定义一个 `Point` 不变类，我们可以按照下面的方式定义

```java
public final class Point {
    private final int x; // 定义实例字段
    private final int y;

    public Point(int x, int y) { // 构造方法
        this.x = x;
        this.y = y;
    }

    public int x() { // 访问字段 x
        return this.x;
    }

    public int y() { // 访问字段 y
        return this.y;
    }
    
    // 还要重写一些方法，如 equals、hashCode、toString
}
```

- 可以看出，虽然定义比较简单，但存在一些问题：每次定义一个数据载体，需要编写大量代码、需要手动重写方法，即麻烦也不易维护。



Java14 开始，引入了 `record` 关键字。上面的代码就可以简化成下面这样：

```java
record Point(int x, int y){}
```

它相当于：

```java
public final class Point extends java.lang.Record {  
  private final int x;
  private final int y;
  public Point(int x, int y){
      this.x = x;
      this.y = y;
  }
  public java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public int x();
  public int y();
 }
```

- 使用 `record` 关键字定义的 `Point` 类会继承 `Record`。
- 生成的 `Point` 类是 `final` 类型的，因此无法被继承。
- 会自动产生两个 `final` 类型的私有实例字段，因此无法被修改。
- 会自动生成 `toString()` 、`hashCode()`和 `equals()`方法。
- 会自动生成访问私有实例字段的 getter 方法，但命名方式区别于 JavaBean。

# record 的其他特性

## 支持静态字段

我们不能将实例字段添加到 record 中。但是，我们可以添加静态字段。

```java
record Person (String firstName, String lastName) {
    static int x;
}
```



## 支持静态方法和实例方法

我们可以在 record 定义中增加静态方法和实例方法，以操作对象的状态。

```java
record Person (String firstName, String lastName) {
    static int x;

    public static void doX(){
        x++;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
```



## 支持添加构造方法

record 中的构造方法也叫 Compact Constructor，它可以帮助我们检查逻辑，例如下面代码中的 `public Peroson{...}`：

```java
record Person (String firstName, String lastName) {
    static int x;

    public Person{
        if(firstName == null){
            throw new IllegalArgumentException( "firstName can not be null !"); 
        }
    }

    public Person(String fullName){
        this(fullName.split(" ")[0],this(fullName.split(" ")[1])
    }
}
```



编译器会自动在 Compact Constructor 基础上添加代码，生成完整的构造方法：

```java
public Person{
        if(firstName == null){
            throw new IllegalArgumentException( "firstName can not be null !"); 
        }
        
        // 编译器生成的 
        this.firstName = firstName;
        this.lastName = lastName;
    }
```



**注**：我们使用 record 的原因是因为想要组织不可变的数据，如果我们需要添加很多方法或字段，应该考虑使用 class。

# 小结

1. 在开发过程中，有时我们会想要将相关字段作为不可变数据组织在一起。我们把这些数据用一个类承载，这个类就被称作纯数据载体类。

2. Java14 提供了关键字 `record`，可以方便的定义数据载体类。由于是预览特性，编译时需要命令：

     ```bash
    javac --enable-preview --release 14 Instance.java
    ```

3. record 体中支持添加静态字段、静态方法、实例方法以及构造方法。但是需要添加更多方法或字段时，应该考虑使用 class 而不是 record。



# Reference

1. https://www.cnblogs.com/hollischuang/p/12529803.html
2. https://www.liaoxuefeng.com/wiki/1252599548343744/1279767986831393