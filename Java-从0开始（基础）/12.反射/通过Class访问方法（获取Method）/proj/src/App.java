import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public方法getScore，参数为String:
        // System.out.println(stdClass.getMethod("getScore", String.class,
        // int[].class));
        // 获取继承的public方法getName，无参数:
        // System.out.println(stdClass.getMethod("getName"));
        // 获取private方法getGrade，参数为int:
        // System.out.println(stdClass.getDeclaredMethod("getGrade", int.class));

        // 获取方法信息
        // Method method = stdClass.getMethod("getScore", boolean.class, int.class);
        // System.out.println(method.getName());
        // System.out.println(method.getReturnType());
        // System.out.println(method.getParameterTypes());
        // int m = method.getModifiers();
        // System.out.println(m);

        // 利用反射调用实例方法
        // String s = "Hello";
        // Method m = s.getClass().getMethod("substring", int.class); //
        // 获取String实例s的Class实例的substring方法对应的Method
        // String subString = (String)m.invoke(s, 1);
        // System.out.println(subString); // ello

        // 利用反射调用静态方法
        // 获取Integer.parseInt(String)方法，参数为String:
        // Method m = Integer.class.getMethod("parseInt", String.class);
        // 调用该静态方法并获取结果:
        // Integer n = (Integer) m.invoke(null, "12345");
        // 打印调用结果:
        // System.out.println(n);
    }
}

class Student extends Person {
    public int getScore(boolean type, int array) {
        return 99;
    }

    private int getGrade(int year) {
        return 1;
    }
}

class Person {
    public String getName() {
        return "Person";
    }
}
