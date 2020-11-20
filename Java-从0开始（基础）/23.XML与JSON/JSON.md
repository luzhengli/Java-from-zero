# JSON 的简介

-   全称：JavaScript Object Notation（JavaScript 对象标记）
-   特点（优点）：
    -   只允许使用 UTF-8 编码，不存在编码问题；
    -   只允许使用双引号作为key，特殊字符用`\`转义，格式简单；
    -   浏览器内置 JSON 支持，如果把数据用 JSON 发送给浏览器，可以用 JavaScript 直接处理。



实例：一个 json 文件

```json
{
    "id": 1,
    "name": "Java核心技术",
    "author": {
        "firstName": "Abc",
        "lastName": "Xyz"
    },
    "isbn": "1234567",
    "tags": ["Java", "Network"]
}
```



# JSON 的数据类型

-   键值对：`{"key": value}`
-   数组：`[1, 2, 3]`
-   字符串：`"abc"`
-   数值（整数和浮点数）：`12.34`
-   布尔值：`true`或`false`
-   空值：`null`



# 解析 JSON

常用的用于解析JSON的第三方库有：

-   Jackson
-   Gson
-   Fastjson
-   ...



## Jackson

使用 Jackson 解析 JSON 需要引入以下依赖：

-   com.fasterxml.jackson.core:jackson-databind:2.10.0



实例：使用 Jackson 解析 JSON（反序列化）

```java
InputStream input = Main.class.getResourceAsStream("/book.json");
ObjectMapper mapper = new ObjectMapper();
// 反序列化时忽略不存在的JavaBean属性:
mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
Book book = mapper.readValue(input, Book.class);
```

-   关闭`DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES`功能使得解析时如果JavaBean不存在该属性时解析不会报错。
-   