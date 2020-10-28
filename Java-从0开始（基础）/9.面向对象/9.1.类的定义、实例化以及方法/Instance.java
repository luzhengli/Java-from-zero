import java.util.Arrays;

public class Instance {
    public static void main(String[] args) {
        Persion coy = new Persion();
        coy.age = 23;
        coy.name = "luzheng";

        System.out.println(coy.age);
        System.out.println(coy.name);

        Book book1 = new Book();
        // book1.name = "Fly"; // 无法访问 编译时会报错

        // 使用方法访问属性
        book1.setName("Fly");
        System.out.println(book1.getName());
        // book1.setId(101); // 输入的 id 不合法 编译时报错

        // 私有方法
        Student stu = new Student();
        stu.setBrith(1998);
        int age = stu.getAge();
        System.out.println(age);

        // 可变参数
        Group g = new Group();
        g.setNames("li", "wang", "hu"); // 第一次传递可变参数
        System.out.println(Arrays.deepToString(g.getNames()));
        g.setNames("liu", "huang"); // 第二次传递可变参数
        System.out.println(Arrays.deepToString(g.getNames()));

    }
}

class Persion {
    public String name;
    public int age;
}

class Book {
    private String name;
    private int id;

    public void setName(String name) {
        if (name == null || name.isBlank()) { // 字符串不能为 null 或者 ""
            throw new IllegalArgumentException("invalid name!!");
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        if (id > 100 || id < 0) {
            throw new IllegalArgumentException("invalid id!!"); // 参数不合法时抛出异常
        } else {
            this.id = id;
        }
    }
}

class Student {
    private String name;
    private int birth;

    public void setBrith(int birth) {
        this.birth = birth;
    }

    public int getAge() {
        return calAge(2020);
    }

    private int calAge(int currentYear) {
        return currentYear - this.birth;
    }
}

class Group {
    private String[] names;

    public void setNames(String... names) { // 定义可变参数 names 这里相当于传入 String[]
        this.names = names;
    }

    public String[] getNames() {
        return this.names;
    }
}
