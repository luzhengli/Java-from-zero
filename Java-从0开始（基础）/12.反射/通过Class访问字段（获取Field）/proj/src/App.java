import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class App {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public字段"score":
        // System.out.println(stdClass.getField("score")); // 根据名称获取类的字段（包含父类）
        // 获取继承的public字段"name":
        // System.out.println(stdClass.getField("name")); //
        // 根据名称获取类的字段（包含父类）这里name就是从父类的继承的字段
        // 获取private字段"grade":
        // System.out.println(stdClass.getDeclaredField("grade")); // 根据名称获取类的字段（不含父类）

        // 使用Field访问字段的信息（除了值）
        // Field f = stdClass.getDeclaredField("grade");
        // System.out.println(f.getName());
        // System.out.println(f.getType());
        // int m = f.getModifiers();
        // System.out.println(m);
        // System.out.println(Modifier.isAbstract(m));
        // System.out.println(Modifier.isStatic(m));
        // System.out.println(Modifier.isFinal(m));
        // System.out.println(Modifier.isPrivate(m));

        // 使用Field访问字段的值
        // Object p = new Person("luyao");
        // Class c = p.getClass();
        // Field f = c.getDeclaredField("name");
        // f.setAccessible(true);
        // Object value = f.get(p);
        // System.out.println(value); // luyao

        // 使用Field设置字段的值
        Person p = new Person("Xiao Ming");
        System.out.println(p.getName()); // "Xiao Ming"
        Class c = p.getClass();
        Field f = c.getDeclaredField("name");
        f.setAccessible(true);
        f.set(p, "Xiao Hong");
        System.out.println(p.getName()); // "Xiao Hong"
    }
}

class Student extends Person {
    public int score;
    private final int grade = 1;

    public Student(String name){
        super(name);
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
