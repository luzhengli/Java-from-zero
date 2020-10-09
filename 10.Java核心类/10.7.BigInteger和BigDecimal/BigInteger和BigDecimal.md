# BigInteger

Q1：CPU支持的最大的整形数是 `long`，共 64 位，它可以直接通过CPU指令进行计算，速度非常快。但如果某个整数超过了 `long` 表示的范围，又该如何表示？

A1：使用软件模拟的方式表示整数。**Java 提供了 `java.math.BigInteger` 表示任意大小的整数**，它的内部通过 `int[]` 数组模拟一个大整数。



---

Q2：`BigInteger` 如何运算？

A2：必须使用相应的实例方法。如加法方法 `add`：

```java
BigInteger l1 = new BigInteger("1234567890"); // 注意这里传入的数据必须包含在字符串中 因为 Java 基本整形不支持这么大的数据
BigInteger l2 = new BigInteger("111222333444");
BigInteger sum = l1.add(l2);
System.out.println(sum);
```



---

Q3：如何将 `BigInteger` 转为其他类型？

A3：**`BigInteger` 也是不可变类，也继承 `Number`类**。而`Number`定义了转换为基本类型的几个方法：

- 转换为`byte`：`byteValue()`
- 转换为`short`：`shortValue()`
- 转换为`int`：`intValue()`
- 转换为`long`：`longValue()`
- 转换为`float`：`floatValue()`
- 转换为`double`：`doubleValue()`

**注**：

- 上述这些方法在转换时如果超出了基本类型的范围，转换时将丢失高位信息，因此结果未必准确。
- 如果希望保证精确转换，可以使用 `intValueExact()`、`longValueExact()`等方法，在转换时如果超出范围，将直接抛出`ArithmeticException`异常。



# BigDecimal

Q1：如何精确表示一个任意大小的浮点数？

A1：Java 提供的 `BigDecimal` 可以表示一个任意大小且精度完全准确的浮点数。



---

Q2：`BigDecimal` 如何运算？

A2：类似 `BigInteger` ，需要使用对应的运算方法。如执行乘法运算：

```java
BigDecimal bd = new BigDecimal("123.4567");
System.out.println(bd.multiply(bd));
```



---

Q3：如何查看 `BigDecimal` 的小数位数？

A3：使用 **`scale()` 方法**查看，例如

```java
BigDecimal d1 = new BigDecimal("123.45");
BigDecimal d2 = new BigDecimal("123.4500");
BigDecimal d3 = new BigDecimal("1234500");
System.out.println(d1.scale()); // 2
System.out.println(d2.scale()); // 4
System.out.println(d3.scale()); // 0
```

**注**：如果 `BigDecimal` 是一个整数，那么先后使用 `stripTrailingZeros()`和 `scale()` 方法会返回负数。例如返回 -2 ，表示这个数是整数，且某位有两个 0 。

```java
BigDecimal d3 = new BigDecimal("1234500");
System.out.println(d3.stripTrailingZeros().scale()); // -2
```



---

Q4：如何去除 `BigDecimal` 末尾的 0？

A4：使用 **`stripTrailingZeros()` 方法**。例如

```java
BigDecimal d1 = new BigDecimal("123.45");
BigDecimal d2 = new BigDecimal("123.4500");
BigDecimal d3 = new BigDecimal("1234500");

System.out.println(d1.stripTrailingZeros()); // 123.45
System.out.println(d2.stripTrailingZeros()); // 123.45
System.out.println(d3.stripTrailingZeros()); // 1.2345E+6
```



---

Q5：如何设置一个 `BigDecimal` 的 `scale`？

A5：可以使用 **`setScale(小数点位数, 保留精确的方式)`方法**。若转换后值的精度比初始值低，那么可以四舍五入或是直接截断。

```java
BigDecimal bd = new BigDecimal("123.4567");
System.out.println(bd.setScale(2, RoundingMode.HALF_DOWN)); // 123.46
System.out.println(bd.setScale(2, RoundingMode.DOWN)); //123.45
```



---

Q6：**`BigDecimal`做加、减、乘时，精度不会丢失，但是做除法时，存在无法除尽的情况。**那么如何解决 `BigDecimal` 在除法运算时产生除不尽的问题？

A6：使用 **`divide()` 方法**时必须指定精度和保留小数点的方式。

```java
BigDecimal d1 = new BigDecimal("123.456");
BigDecimal d2 = new BigDecimal("23.456789");
System.out.println(d1.divide(d2, 10, RoundingMode.HALF_UP)); // 5.2631244626
```



---

Q7：`BigDecimal` 进行除法运算时，如何查看余数？

A7：调用**`divideAndRemainder()`方法，它会返回一个数组，包含两个`BigDecimal`，分别是商和余数。**可以通过余数是否为 0 判断是否整除

```java
BigDecimal d1 = new BigDecimal("12.75");
BigDecimal d2 = new BigDecimal("0.15");
BigDecimal remainder = d1.divideAndRemainder(d2)[1];

if (remainder.signum() == 0) { // signum 方法下面会介绍
	System.out.println("d1 可以整除 d2.");
} else {
	System.out.println("d1 无法整除 d2.");
}
```

输出

```
d1 可以整除 d2.
```



**注**：`BigDecimal` 有一个 **`signum()` 方法**，它会根据 `BigDecimal` 是否是正数/0/负数，返回 1/0/-1。



---

Q8：**`BigDecimal` 如何相互比较**？

A8：如果使用 `equals()` 方法比较，当且仅当 `BigDecimal`的值相等，且 `scale()`相等时，二者才相等。**如果希望根据值进行比较，应当使用 `compareTo()` 方法**，它会根据两个值的大小返回负数、整数和 0，分别表示小于、大于和等于。

```java
BigDecimal d1 = new BigDecimal("123.456");
BigDecimal d2 = new BigDecimal("123.45600");
System.out.println(d2.equals(d1)); // false
System.out.println(d2.stripTrailingZeros().equals(d1)); // true
System.out.println(d2.compareTo(d1)); // 0
```



# 小结

1. Java 提供了 `java.math.BigInteger` 表示任意大小的整数。
2. `BigInteger` 也是不可变类，也继承 `Number`类，它提供了一些数据类型转换方法，例如 `longValue()`。
3. 如果希望将 `BigInteger` 精确转为基本类型，选需要使用 `xxxValueExact()` 方法。如果超出基本类型范围，编译器会抛出 `ArithmeticException` 异常。
4. Java 提供了 `java.math.BigDecimal` 来精确表示任意大小的浮点数。这常用于财务计算。
5. `BigDecimal` 提供了一些和精度（小数点）`scale` 、求余有关的方法。
6. `BigDecimal` 实际是通过一个 `BigInteger` 和 `scale` 来表示的，因此它们之间的比较应该使用 `compareTo()` 而非 `equals()`。

