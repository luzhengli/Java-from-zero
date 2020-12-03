# JDBC简介

JDBC

-   全称：Java数据库连接（Java DataBase Connectivity）

-   本质：Java 访问数据库的一组**标准接口**（即 Java 中的 `Interface`）

-   作用：JDBC 接口访问数据库驱动（在 Java 中本质上是封装了具体实现类的 jar 包），通过网络（TCP）访问到数据库，流程图如下所示（以 Java 访问 MySQL 为例）

    ```cmd
    ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
       ┌───────────────┐
    │  │   App.class   │  │
       └───────────────┘
    │          │          │
               ▼
    │  ┌───────────────┐  │
       │  java.sql.*   │
    │  └───────────────┘  │
               │
    │          ▼          │
       ┌───────────────┐     TCP    ┌───────────────┐
    │  │ mysql-xxx.jar │──┼────────>│     MySQL     │
       └───────────────┘            └───────────────┘
    └ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┘
              JVM
    ```

-   优点：

    -   对于各数据库厂商，使用相同的接口，Java 代码不需要针对不同数据库分别开发；
    -   Java 程序编译期仅依赖 java.sql 包，不依赖具体数据库的 jar 包；
    -   可随时替换底层数据库，访问数据库的 Java 代码基本不变。



# JDBC的基本使用方法

## 建立连接

1.  准备数据（这里添加数据是为了后面JDBC连接测试用）：在MySQL客户端执行以下代码：

    ```sql
    -- 创建数据库learjdbc:
    DROP DATABASE IF EXISTS learnjdbc;
    CREATE DATABASE learnjdbc;
    
    -- 创建登录用户learn/口令learnpassword
    CREATE USER IF NOT EXISTS learn@'%' IDENTIFIED BY 'learnpassword';
    GRANT ALL PRIVILEGES ON learnjdbc.* TO learn@'%' WITH GRANT OPTION;
    FLUSH PRIVILEGES;
    
    -- 创建表students:
    USE learnjdbc;
    CREATE TABLE students (
      id BIGINT AUTO_INCREMENT NOT NULL,
      name VARCHAR(50) NOT NULL,
      gender TINYINT(1) NOT NULL,
      grade INT NOT NULL,
      score INT NOT NULL,
      PRIMARY KEY(id)
    ) Engine=INNODB DEFAULT CHARSET=UTF8;
    
    -- 插入初始数据:
    INSERT INTO students (name, gender, grade, score) VALUES ('小明', 1, 1, 88);
    INSERT INTO students (name, gender, grade, score) VALUES ('小红', 1, 1, 95);
    INSERT INTO students (name, gender, grade, score) VALUES ('小军', 0, 1, 93);
    INSERT INTO students (name, gender, grade, score) VALUES ('小白', 0, 1, 100);
    INSERT INTO students (name, gender, grade, score) VALUES ('小牛', 1, 2, 96);
    INSERT INTO students (name, gender, grade, score) VALUES ('小兵', 1, 2, 99);
    INSERT INTO students (name, gender, grade, score) VALUES ('小强', 0, 2, 86);
    INSERT INTO students (name, gender, grade, score) VALUES ('小乔', 0, 2, 79);
    INSERT INTO students (name, gender, grade, score) VALUES ('小青', 1, 3, 85);
    INSERT INTO students (name, gender, grade, score) VALUES ('小王', 1, 3, 90);
    INSERT INTO students (name, gender, grade, score) VALUES ('小林', 0, 3, 91);
    INSERT INTO students (name, gender, grade, score) VALUES ('小贝', 0, 3, 77);
    ```

    插入后数据如下图所示

    ![image-20201121204903797](https://gitee.com/llillz/images/raw/master/image-20201121204903797.png)

2.  JDBC 连接

    1.  添加 Maven 依赖：JDBC 只是接口，真正使用时需要下载指定对应数据库的 JDBC 驱动，即封装了 Java 实现类的 jar 包。

        1.  以添加 MySQL 的驱动为例：需要下载 `mysql-connector-java` 依赖包；添加 Maven 依赖时需要指定正确的 version 和 scope（scope 选择 runtime，因为只需要运行期使用），如下所示

            ```xml
            <dependency>
            	<groupId>mysql</groupId>
            	<artifactId>mysql-connector-java</artifactId>
            	<version>8.0.22</version>
            </dependency>
            ```

    2.  打开 **Connection 连接**（Connection代表一个JDBC连接，它相当于Java程序到数据库的连接（通常是TCP连接）。**打开一个Connection时，需要准备URL、用户名和口令**，才能成功连接到数据库）

        1.  URL 的格式类似：`jdbc:mysql://<hostname>:<port>/<db>?key1=value1&key2=value2`。如果数据库运行在本机`localhost`，端口使用标准的`3306`，数据库名称是`learnjdbc`，则 URL 是 `jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8`
            1.  注：这里的两个参数 `useSSL` 和 `characterEncoding` 设置了不使用 SSL 加密以及使用 UTF-8 作为编码

    

**实例**：连接MySQL数据库系统中 learnjdbc 数据库（预先创建的）

```java
// JDBC连接的URL, 不同数据库有不同的格式:
String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc/?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC"; // 这里要连接的数据库是"learnjbdc" UTC是统一标准世界时间（必须指定该项） 
String JDBC_USER = "root";
String JDBC_PASSWORD = "password";
// 获取连接：
try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
    // TODO：访问数据库...
} // 自动关闭连接
```

-   数据库资源需要关闭，于是这里采用了 try(source){} 语句

**注**：Java 连接 MySQL 8.0 以后的版本需要在 JDBC_URL 中指定时区参数（即设置 `serverTimezone=UTC`）



---

## 查询（Retrieve）

### 使用 Statement 查询

在连接数据库之后，我们尝试**查询。具体流程如下**：

1.  通过`Connection`提供的`createStatement()`方法创建一个`Statement`对象，用于执行查询语句（实现已经构造完毕）
2.  执行`Statement`对象提供的`executeQuery("SELECT * FROM students")`方法并传入SQL语句，返回的结果使用`ResultSet`来引用
3.  反复调用`ResultSet`的`next()`方法并读取每一行结果



**实例**：从 learnjdbc 数据库的 students 数据表中查询 gender=1 的字段

```java
// 写法一：嵌套的 try(source){}
try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
    try (Statement stmt = conn.createStatement()) {
        try (ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")) {
            while (rs.next()) {
                long id = rs.getLong(1); // 注意：索引从1开始
                long grade = rs.getLong(2);
                String name = rs.getString(3);
                int gender = rs.getInt(4);
            }
        }
    }
}
```

```java
// 写法二：包含多个资源的 try(source){}
try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")
    ) {
    while (rs.next()) {
        long id = rs.getLong(1); // 注意：数据库的字段索引从1开始
        long grade = rs.getLong(2);
        String name = rs.getString(3);
        int gender = rs.getInt(4);
        System.out.printf("id = %d, grade = %d, name = %s, gender = %d\n", id, grade, name, gender);
    }
} // 依次关闭资源 ResultSet、Statement、Connection
```

-   `Statment`和`ResultSet`都是需要关闭的资源
-   `rs.next()`用于判断是否有下一行记录，如果有，将自动把当前行移动到下一行（一开始获得`ResultSet`时当前行不是第一行）
-   需要根据 `SELECT` 语句中查询的字段先后关系决定调用 `getLong()` 还是 `getInt()` 这类方法
-   `getLong()` 和 `getInt()` 这类方法需要指定字段的索引值（从1开始）或是字段名。在上面的例子里使用的是索引值。

输出：

```cmd
id = 1, grade = 1, name = 小明, gender = 1
id = 2, grade = 1, name = 小红, gender = 1
id = 5, grade = 2, name = 小牛, gender = 1
id = 6, grade = 2, name = 小兵, gender = 1
id = 9, grade = 3, name = 小青, gender = 1
id = 10, grade = 3, name = 小王, gender = 1
```

再查看下数据库，可以看到与期望查询的数据是一样的：

![image-20201121201104364](https://gitee.com/llillz/images/raw/master/image-20201121201104364.png)



### 使用 PreparedStatement 查询（建议）

Q：已经有了 Statement 查询方法，为什么要使用 PreparedStatement  查询？

A：使用 Statement 查询存在 **SQL 注入**的安全问题。SQL 注入是指用户可以传入精心构造的字符串，这些字符串作为参数传给 SQL 语句并进行拼接，拼接所得的 SQL 语句会产生意外的效果。



例如：把 name = `"bob' OR pass="`, pass = `" OR pass='"` 传给 SQL 语句模板 `"SELECT * FROM user WHERE login='" + name + "' AND pass='" + pass + "'"`。最后拼接后会变成：

```sql
SELECT * FROM user WHERE login='bob' OR pass=' AND pass=' OR pass=''
```

-   此时用户不需要输入密码也能访问到数据，显然这有悖于开发者的目的

为了解决这个问题，Java 还提供了 `PreparedStatement` 查询方式。



---

Q：PreparedStatement 查询的具体流程

A：

1.  通过`Connection`提供的`createStatement()`方法创建一个`prepareStatement`对象，确定好 SQL 语句的模板，并为之后要传入的参数进行占位。
2.  使用 `preparedStatement` 对象的 `setObject()`  方法填充 SQL 语句中的参数
3.  调用`preparedStatement`对象的`executeQuery()`方法执行 SQL 语句，并用 `ResultSet` 对象引用返回的结果集
4.  反复调用`ResultSet`的`next()`方法并读取每一行结果



实例：

```java
try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
    try (PreparedStatement ps = conn.prepareStatement("SELECT id, grade, name, gender FROM students WHERE gender=? AND grade=?")) { // 注意这里的占位符 ?
        ps.setObject(1, 1); // 第一个参数表示索引，注意，索引从1开始
        ps.setObject(2, 3);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id"); // 这里采用列名的方式索引
                long grade = rs.getLong("grade");
                String name = rs.getString("name");
                int gender = rs.getInt("gender");

                System.out.printf("id = %d, grade = %d, name = %s, gender = %d\n", id, grade, name, gender);
            }
        }
    }
}
```

输出：

```cmd
id = 9, grade = 3, name = 小青, gender = 1
id = 10, grade = 3, name = 小王, gender = 1
```

结果符合语句的输出



## 插入（Create）

## 常规的插入

和查询相比，插入的代码只需将`executeQuery()`改为`executeUpdate()`即可。



实例：新插入一条信息

```java
try (PreparedStatement ps = conn.prepareStatement("INSERT INTO students (id,grade,name,gender,score) VALUES (?,?,?,?,?)")) {
    ps.setObject(1, 15);
    ps.setObject(2, 1);
    ps.setObject(3, "老王");
    ps.setObject(4, 1);
    ps.setObject(5, 100);
    int n = ps.executeUpdate(); // 修改的部分，该方法返回插入的字段条数
    System.out.println("新插入" + n + "条数据");
}
```

查看结果：

![image-20201124211423325](https://gitee.com/llillz/images/raw/master/image-20201124211423325.png)



## 设置了自增主键时的插入

Q：如何获取插入后的自增主键的值？

A：

1.  警惕错误做法：要获取自增主键，不能先插入，再查询。这时因为这两条 SQL 语句之间可能有别的 SQL 语句修改了表。
2.  正确做法：
    1.  在创建`PreparedStatement`的时候，指定一个`RETURN_GENERATED_KEYS`标志位（就是一个`prepareStatement()`方法的一个参数），表示JDBC驱动必须返回插入的自增主键
    2.  在执行完SQL语句后，使用 `PreparedStatement ` 对象的 `getGeneratedKeys()` 方法获取一个`ResultSet`对象，这个对象包含了数据库自动生成的主键的值，读取该对象的每一行来获取自增主键的值。如果一次插入多条记录，那么这个`ResultSet`对象就会有多行返回值。如果插入时有多列自增，那么`ResultSet`对象的每一行都会对应多个自增值（自增列不一定必须是主键）。



实例：获取自增主键的插入

```java
try (PreparedStatement ps = conn.prepareStatement(
    "INSERT INTO students (grade, name, gender) VALUES (?,?,?)",
    Statement.RETURN_GENERATED_KEYS)) { // 设置 Statement.RETURN_GENERATED_KEYS 参数
    ps.setObject(1, 1); // grade
    ps.setObject(2, "Bob"); // name
    ps.setObject(3, "M"); // gender
    int n = ps.executeUpdate(); // 1
    try (ResultSet rs = ps.getGeneratedKeys()) { // 获取自增主键
        if (rs.next()) {
            long id = rs.getLong(1); // 注意：索引从1开始
        }
    }
}
```



## 更新（Update）

更新和插入在 JDBC 代码层面没有区别，都使用 `executeUpdate()` 方法。区别只在于 SQL 语句。



实例：

```java
try (PreparedStatement ps = conn.prepareStatement("UPDATE students SET name=? WHERE id=?")) {
    ps.setObject(1, "Bob"); // 注意：索引从1开始
    ps.setObject(2, 999);
    int n = ps.executeUpdate(); // 返回更新的行数
}
```



## 删除（Delete）

删除和插入在 JDBC 代码层面没有区别，都使用 `executeUpdate()` 方法。区别只在于 SQL 语句。



实例：

```java
try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
    try (PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE id=?")) {
        ps.setObject(1, 999); // 注意：索引从1开始
        int n = ps.executeUpdate(); // 删除的行数
    }
}
```



# 数据类型映射

使用 JDBC 时需要在 Java 和 MySQL 的数据类型中进行转换。JDBC在 `java.sql.Types` 定义了一组常量来表示如何映射SQL数据类型，常用的类型映射关系如下：

| SQL数据类型   | Java数据类型               |
| :------------ | :------------------------- |
| BIT, BOOL     | boolean                    |
| INTEGER       | int                        |
| BIGINT        | long                       |
| REAL          | float                      |
| FLOAT, DOUBLE | double                     |
| CHAR, VARCHAR | String                     |
| DECIMAL       | BigDecimal                 |
| DATE          | java.sql.Date, *LocalDate* |
| TIME          | java.sql.Time, *LocalTime* |

注：只有最新的JDBC驱动才支持`LocalDate`和`LocalTime`



# JDBC事务（学习完SQL后回看）

Q：数据库事务的概念？

A：数据库事务（Transaction）是由若干个SQL语句构成的一个操作序列。数据库系统保证在一个事务中的所有SQL要么全部执行成功，要么全部不执行，即**数据库事务具有 ACID 特性**：

-   Atomicity：原子性
-   Consistency：一致性
-   Isolation：隔离性
-   Durability：持久性



---

Q：事务的隔离级别是什么？

A：SQL 标准定义了4种隔离级别，分别对应可能出现的数据不一致的情况：

| Isolation Level  | 脏读（Dirty Read） | 不可重复读（Non Repeatable Read） | 幻读（Phantom Read） |
| :--------------- | :----------------- | :-------------------------------- | :------------------- |
| Read Uncommitted | Yes                | Yes                               | Yes                  |
| Read Committed   | -                  | Yes                               | Yes                  |
| Repeatable Read  | -                  | -                                 | Yes                  |
| Serializable     | -                  | -                                 | -                    |



---

**JDBC 事务的代码范式**：

```java
Connection conn = openConnection();
try {
    // 关闭自动提交（这步是必须的，否则每一条SQL语句会作为一个事务自动执行）:
    conn.setAutoCommit(false);
    // 执行多条SQL语句:
    insert(); update(); delete();
    // 提交事务:
    conn.commit();
} catch (SQLException e) {
    // 回滚事务:
    conn.rollback();
} finally {
    conn.setAutoCommit(true); 
    conn.close(); // 关闭资源
}
```

-   **默认情况下，获取到`Connection`连接后，总是处于“自动提交”模式**。
-   conn.setAutoCommit(false)：表示关闭自动提交，否则每一条SQL语句会作为一个事务自动执行。
-   conn.commit()：在若干行SQL语句后使用改行代码表示提交一个事务。
-   conn.rollback()：如果事务提交失败，需要捕获异常，然后使用改行代码回滚事务。



可以使用以下代码设定事务的隔离级别：

```java
// 设定隔离级别为READ COMMITTED:
conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
```



# JDBC批量操作（JDBC Batch）

Q：什么是数据库的 batch 操作？

A：SQL 数据库对 SQL 语句相同，但只有参数不同的若干语句可以作为 batch 执行，即批量执行，这种操作有特别优化，速度远远快于循环执行每个 SQL。 

---

JDBC Batch 代码范式：

```java
try (PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, gender, grade, score) VALUES (?, ?, ?, ?)")) {
    // 对同一个PreparedStatement反复设置参数并调用addBatch():
    for (Student s : students) {
        ps.setString(1, s.name);
        ps.setBoolean(2, s.gender);
        ps.setInt(3, s.grade);
        ps.setInt(4, s.score);
        ps.addBatch(); // 添加到batch
    }
    // 执行batch:
    int[] ns = ps.executeBatch();
    for (int n : ns) {
        System.out.println(n + " inserted."); // batch中每个SQL执行的结果数量
    }
}
```

-   JDBC 批量操作需要对同一个 `PreparedStatement`反复设置参数并调用`addBatch()`。
-   执行批量操作不是`executeUpdate()`，而是`executeBatch()`，返回的结果是多个`int`值，因此返回类型是`int[]`。



# JDBC 连接池

Q：什么是 JDBC 连接池？

A：JDBC 的连接创建类似线程创建，都是十分耗费资源的操作。类似线程池，也出现了数据库连接池这种通过复用 JDBC 连接以节省开销的方式。

和线程池不同，Java 仅提供了一个**标准接口 `javax.sql.DataSource`**，具体实现需要添加依赖包。**常用的常用的 JDBC 连接池实现有：**

-   HikariCP
-   C3P0
-   BoneCP
-   Druid



---

Q：如何使用 JDBC 连接池？

A：目前使用最广泛的是HikariCP。以其为例，介绍 **JDBC 连接池的使用步骤**：

1.  添加 HikariCP 依赖

    ```xml
    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
        <version>3.4.5</version>
    </dependency>
    ```

2.  创建 DataSource 实例（创建连接池）

    ```java
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(JDBC_URL);
    config.setUsername(JDBC_USER);
    config.setPassword(JDBC_PASSWORD);
    config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1s
    config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60s
    config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
    DataSource sd = new HikariDataSource(config); // 创建连接池并传入一些配置参数
    ```

    -   创建 DataSource 也是一个非常昂贵的操作，**通常 DataSource 实例总是作为一个全局变量存储，并贯穿整个应用程序的生命周期**

3.  获取 Connection（从连接池获取一个连接）

    ```java
    try (Connection conn = ds.getConnection()) { // 在此获取连接
        ...
    } // 在此“关闭”连接
    ```

    -   连接池创建后内部没有连接，因此需要调用 `ds.getConnection()` 选择一个空闲的连接，并标记为**“正在使用”**然后返回。
    -   try source 会自动调用 `ds.close()` ，它不会真的关闭一个连接，而是将其再次标记为**“空闲”**，从而等待下次被调用。
    -   连接池提供了许多可以配置的参数。例如，维护的最小、最大活动连接数，指定一个连接在空闲一段时间后自动关闭等，需要根据应用程序的负载合理地配置这些参数。

4.  使用某个具体连接（conn）的方法和之前章节介绍的一样

# 小结

-   JDBC 全称是 Java DataBase Connectivity，它是指 Java 应用程序访问数据库的一套标准接口。如果 Java 应用希望访问到数据库，需要 JDBC + 对应的数据库驱动（后者是封装了接口实现类的 jar 包）。