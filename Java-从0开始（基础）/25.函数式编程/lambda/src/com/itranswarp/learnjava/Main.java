package com.itranswarp.learnjava;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Learn Java from https://www.liaoxuefeng.com/
 * 
 * @author liaoxuefeng
 */
public class Main {

	public static void main(String[] args) {
		String[] array = new String[] { "apple", "Orange", "banana", "Lemon" };
		// 请使用忽略大小写排序，并改写为Lambda表达式:
		// Arrays.sort(array, (s1, s2) -> {
		// 	String a = (String) s1.toUpperCase();
		// 	String b = (String) s2.toUpperCase();
		// 	return a.compareTo(b);
		// });

		// String[] array2 = new String[] { "apple", "orange", "banana", "lemon" };
		// 静态方法的引用
		// Arrays.sort(array2, Main::cmp);

		// 实例方法的引用
		// Arrays.sort(array2, String::compareTo);

		// 构造方法的引用
		List<String> names = List.of("Bob", "Alice", "Tim");
		List<Person> persons = names.stream().map(Person::new).collect(Collectors.toList());
		System.out.println(persons);
	}

	static int cmp(String s1, String s2){
		return s1.compareTo(s2);
	}
}

class Person {
	String name;

	public Person(String name) {
		this.name = name;
	}

	public String toString() {
		return "Person:" + this.name;
	}
}


