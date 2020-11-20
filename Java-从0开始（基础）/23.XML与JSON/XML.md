# XML简介

Q：XML是什么？

A：

-   全称：XML是**可扩展标记语言（eXtensible Markup Language）**的缩写
-   本质：XML是一种数据表示的格式，可以表示复杂的数据结构
-   作用：XML常用于传输和存储数据
-   特点：XML的特点是
    -   纯文本，默认使用 UTF-8 编码
    -   支持嵌套，适合表示结构化数据



# XML的结构

**XML有固定的结构，主要可分为三部分**

1.  首行声明 XML 版本以及编码：类似 `<?xml version="1.0" encoding="UTF-8"?>`
2.  声明文档定义类型（DTD - Document Type Definition），这部分是**可选**的：类似`<!DOCTYPE note SYSTEM "book.dtd">`
3.  XML文档内容：由一个根元素（可包含任意的子元素），元素可以包含属性



**实例**：一个XML文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE body [
    <!ENTITY warning "Warning: Something bad happened... please refresh and try again.">
]>
<body>
    <message> &warning; </message>
</body>
```



# 表示特殊字符

常用的特殊字符及其转义表示

| 字符 | 表示     |
| :--- | :------- |
| <    | `&lt;`   |
| >    | `&gt;`   |
| &    | `&amp;`  |
| "    | `&quot;` |
| '    | `&apos;` |



# 文档定义类型（DTD）

DTD文档可以指定一系列规则，例如：

-   根元素必须是`book`
-   `book`元素必须包含`name`，`author`等指定元素
-   `isbn`元素必须包含属性`lang`
-   ...



# XML技术体系

XML是一个技术体系，除了我们经常用到的XML文档本身外，XML还支持：

-   DTD和XSD：验证XML结构和数据是否有效；
-   Namespace：XML节点和属性的名字空间；
-   XSLT：把XML转化为另一种文本；
-   XPath：一种XML节点查询语言；
-   ...



# 解析 XML

XML是一种树形结构的文档，它有两种标准的解析API：

-   DOM：一次性读取XML，并在内存中表示为树形结构；
-   SAX：以流的形式读取XML，使用事件回调。

此外，还将有一种简便的解析 XML 的第三方包：Jackson



## DOM API

-   DOM是 Document Object Model的缩写
-   DOM 模型就是把 XML 结构作为一个树形结构处理，从根节点开始，每个节点都可以包含任意个子节点



**实例**：XML 文件解析为 DOM 结构后是怎么样的

对于以下XML文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<book id="1">
    <name>Java核心技术</name>
    <author>Cay S. Horstmann</author>
    <isbn lang="CN">1234567</isbn>
    <tags>
        <tag>Java</tag>
        <tag>Network</tag>
    </tags>
    <pubDate/>
</book>
```

解析为DOM结构后类似如下

```cmd
                      ┌─────────┐
                      │document │
                      └─────────┘
                           │
                           ▼
                      ┌─────────┐
                      │  book   │
                      └─────────┘
                           │
     ┌──────────┬──────────┼──────────┬──────────┐
     ▼          ▼          ▼          ▼          ▼
┌─────────┐┌─────────┐┌─────────┐┌─────────┐┌─────────┐
│  name   ││ author  ││  isbn   ││  tags   ││ pubDate │
└─────────┘└─────────┘└─────────┘└─────────┘└─────────┘
                                      │
                                 ┌────┴────┐
                                 ▼         ▼
                             ┌───────┐ ┌───────┐
                             │  tag  │ │  tag  │
                             └───────┘ └───────┘
```

-   顶层的 document 代表 XML 文档，它是真正的“根”，而`<book>`虽然是根元素，但它是`document`的一个子节点



解析 DOM（以上述XML文件为例）的 Java 代码：

```java
// XML解析为DOM
InputStream in = App.class.getResourceAsStream("./XML-demo.xml");
DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
DocumentBuilder db = dbf.newDocumentBuilder();
Document doc = db.parse(in);
```



打印DOM的节点的 Java 代码：

函数部分

```java
static void printNode(Node n, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print(' ');
        }
        switch (n.getNodeType()) {
            case Node.DOCUMENT_NODE: // Document节点
                System.out.println("Document: " + n.getNodeName());
                break;
            case Node.ELEMENT_NODE: // 元素节点
                System.out.println("Element: " + n.getNodeName());
                break;
            case Node.TEXT_NODE: // 文本
                System.out.println("Text: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.ATTRIBUTE_NODE: // 属性
                System.out.println("Attr: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            default: // 其他
                System.out.println("NodeType: " + n.getNodeType() + ", NodeName: " + n.getNodeName());
        }
        // if (n.hasAttributes()) {
        //     NamedNodeMap as = n.getAttributes();
        //     for (int i = 0; i < as.getLength(); i++) {
        //         printNode(as.item(i), indent + 1);
        //     }
        // }
        for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
            printNode(child, indent + 4);
        }
    }
```

调用

```java
printNode(doc, 0);
```

输出

```cmd
Document: #document
    Element: book
        Text: #text =

        Element: name
            Text: #text = Java核心技术
        Text: #text = 

        Element: author
            Text: #text = Cay S. Horstmann
        Text: #text =

        Element: isbn
            Text: #text = 1234567
        Text: #text =

        Element: tags
            Text: #text =

            Element: tag
                Text: #text = Java
            Text: #text =

            Element: tag
                Text: #text = Network
            Text: #text =

        Text: #text =

        Element: pubDate
        Text: #text =
```





## SAX API

-   SAX是 Simple API for XML 的缩写
-   它是一种基于流的解析方式，边读取XML边解析，并以事件回调的方式让调用者获取数据。因为是一边读一边解析，所以无论XML有多大，占用的内存都很小。



SAX解析会触发一系列**事件**：

-   startDocument：开始读取XML文档；
-   startElement：读取到了一个元素，例如`<book>`；
-   characters：读取到了字符；
-   endElement：读取到了一个结束的元素，例如`</book>`；
-   endDocument：读取XML文档结束。



用 SAX API 解析 XML 的 Java 代码：

```java
InputStream input = Main.class.getResourceAsStream("/book.xml");
SAXParserFactory spf = SAXParserFactory.newInstance();
SAXParser saxParser = spf.newSAXParser();
saxParser.parse(input, new MyHandler()); // MyHandler 是回调对象 要继承 DefaultHandler
```

回调对象的定义代码

```java
class MyHandler extends DefaultHandler {
    public void startDocument() throws SAXException {
        print("start document");
    }

    public void endDocument() throws SAXException {
        print("end document");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        print("start element:", localName, qName);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        print("end element:", localName, qName);
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        print("characters:", new String(ch, start, length));
    }

    public void error(SAXParseException e) throws SAXException {
        print("error:", e);
    }

    void print(Object... objs) {
        for (Object obj : objs) {
            System.out.print(obj);
            System.out.print(" ");
        }
        System.out.println();
    }
}
```

输出

```cmd
start document 
characters:

characters: Java核心技术
end element:  name
characters:
     
characters: Cay S. Horstmann
end element:  author
characters:

characters: 1234567
end element:  isbn
characters:

characters:

characters: Java
end element:  tag
characters:

characters: Network
end element:  tag
characters:

end element:  tags
characters:

end element:  pubDate
characters:

end element:  book
end document
```



## Jackson

>   可执行代码见 proj 目录

Jackson 是 Java 的第三方包，提供了一套用于Java（和JVM平台）的数据处理工具，处理以Avro，BSON，CBOR，CSV，Smile，（Java）属性，Protobuf，XML或YAML编码的数据；甚至包括大量数据格式模块，以支持广泛使用的数据类型的数据类型，例如Guava，Joda，PCollections以及许多其他类型

简言之，Jackson 可以轻松做到 XML 到 JavaBean 的转换。



**实例**：使用 Jackson 实现对 XML 用 Javabean 的方式读取

使用之前需要先添加两个 Maven 依赖：

-   com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.10.1
-   org.codehaus.woodstox:woodstox-core-asl:4.4.1

代码如下：

```java
package com.demo;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception{

        InputStream input = App.class.getResourceAsStream("../../XML-demo.xml");
        JacksonXmlModule module = new JacksonXmlModule();
        XmlMapper mapper = new XmlMapper(module); // 核心对象
        Book book = mapper.readValue(input, Book.class);
        System.out.println("book id: "+book.id);
        System.out.println("book name: "+book.name);
        System.out.println("book author: "+book.author);
        System.out.println("book isbn: "+book.isbn);
        System.out.println("book tags: "+book.tags);
        System.out.println("book pubDate: "+book.pubDate);
    }
}

class Book {
    public long id;
    public String name;
    public String author;
    public String isbn;
    public List<String> tags;
    public String pubDate;
}
```

