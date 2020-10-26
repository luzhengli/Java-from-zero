import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

import packg1.StringArrayList;
import packg2.Person;
import packg3.Pair;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        // StringArrayList strArr = new StringArrayList();
        // System.out.println(strArr.getClass());

        // 使用泛型：以 ArrayList 为例
        // ArrayList<String> strArrList = new ArrayList<String>(); // 泛型提供模板
        // strArrList.add("abc");
        // String s = strArrList.get(0); // 不用强制类型转换 编译器保证类型正确
        // out.println(s);
        // out.println(strArrList.getClass());

        // ArrayList<T> 可以向上转型为 List<T>
        // List<String> list = new ArrayList<>(); // 可以省略<>中的list 编译器可以自动推断泛型类型

        // 注意：不能把 ArrayList<Integer> 向上转型为 ArrayList<Number> 或 List<Number>
        // ArrayList<Integer> integerList = new ArrayList<Integer>(); // 创建 ArrayList 对象
        // integerList.add(new Integer(123)); // 添加 Integer 元素
        // ArrayList<Number> numberList = integerList; // ArrayList<Integer> 转
        // ArrayList<Number>
        // numberList.add(new Float(12.34)); // 添加 Float 元素
        // Integer n = numberList.get(1); // 获取到 Float 元素赋给引用变量 Integer 显然出错！！
        // 可见 ArrayList<T> 中的元素不运行类型不同

        // 泛型接口
        // Person[] personList = new Person[] { new Person("Ken", 30), new
        // Person("Alice", 23), new Person("Bob", 34) };
        // Arrays.sort(personList);
        // out.println(Arrays.toString(personList));

        // 编写泛型
        // Pair<String, Integer> pair = new Pair<String, Integer>("first", 20);
        // out.println(pair.getFirst()); // 实例方法在调用类后（会自动调用构造函数）就知道泛型类型了
        // out.println(pair.getLast());
        // Pair<Integer, String> pair2 = Pair.create(30, "last"); // 静态方法预先不知道泛型类型的具体类型
        // out.println(pair2.getFirst());
        // out.println(pair2.getLast());

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

        Pair<String, String> p = new Pair<String, String>("123", "456");
        // Compile error:
        if (p instanceof Pair<?, ?>) {
            
        }
    }
}
