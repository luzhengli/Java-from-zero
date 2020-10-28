> Java是面向对象的语言，一个程序的基本单位就是`class` 。

实例：定义一个 `Hello` 类

```java
public class Hello {
	//
}
```

**类名要求**：

- 类名必须以英文字母开头，后接字母，数字和下划线的组合
- 习惯以大写字母开头

要注意遵守命名习惯，好的类命名：

- Hello
- NoteBook
- VRPlayer

不好的类命名：

- hello
- Good123
- Note_Book
- _World

注：`public` 是访问修饰符，表示该 `class` 是公开的。不写 `public` ，也能正确编译，但是这个类将无法从命令行执行。



>  一个 `class` 的内部可以定义多个方法

```java
public class Hello {
    public static void main(String[] args) { // 方法名是 main
        // 方法代码...
    } // 方法定义结束
}
```

- 这里定义了一个 `main` 静态方法，无返回值。

**方法名要求**：

- 基本和类名一样，但是首写字母小写

好的方法命名：

- main
- goodMorning
- playVR

不好的方法命名：

- Main
- good123
- good_morning
- _playVR



> Java 的注释有三种

1. 单行注释：`// 注释的内容`

2. 多行注释：

    ```java
    /*
    注释的内容
    */
    ```

3. 特殊的多行注释：

    ```java
    /**
     * 可以用来自动创建文档的注释
     * 
     * @auther liaoxuefeng
     */
    ```

    **多行注释的每行以星号开头，需要写在类和方法的定义处，可以用于自动创建文档。**

    

