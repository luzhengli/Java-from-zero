import java.io.Closeable;
import java.io.DataInputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // 获取父类
        // Class i = Integer.class;
        // Class n = i.getSuperclass();
        // System.out.println(n); // class java.lang.Number
        // Class o = n.getSuperclass();
        // System.out.println(o); // class java.lang.Object
        // System.out.println(o.getSuperclass());// null

        // 获取实现的接口
        // Class n = Integer.class;
        // Class[] ls = n.getInterfaces(); // 返回所有实现的接口
        // for (Class i : ls) { // 输出所有实现的接口
        // System.out.println(i);
        // }

        // 接口的父类
        // System.out.println(java.util.List.class.getSuperclass()); // null List继承的是接口
        // 因此getSuperclass方法返回为null
        // Class ls[] = java.util.List.class.getInterfaces(); // interface
        // java.util.Collection
        // for (Class c : ls) {
        // System.out.println(c);
        // }

        // 是否可以转型
        System.out.println(Integer.class.isAssignableFrom(Integer.class)); // true Integer是否可以赋值给Integer
        System.out.println(Number.class.isAssignableFrom(Integer.class)); // true Integer是否可以赋值给Number
        System.out.println(Object.class.isAssignableFrom(Integer.class)); // true Integer是否可以赋值给Object
        System.out.println(Integer.class.isAssignableFrom(Number.class)); // false Number是否可以赋值给Integer
    }
}
