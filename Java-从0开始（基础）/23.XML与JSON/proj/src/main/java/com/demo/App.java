package com.demo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception{
        // 使用 Jackson 解析 XML
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

        // 使用 Jackson 解析 JSON
        InputStream in = App.class.getResourceAsStream("../../JSON-demo.json");
        ObjectMapper mapper2 = new ObjectMapper(); //
        // 反序列化时忽略不存在的JavaBean属性:
        mapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Book book2 = mapper2.readValue(in, Book.class);
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
