public class Instance {
    public static void main(String[] args) {
        Person p1 = new Person("li");
        Person p2 = new Person("wang");
        // 静态字段
        p1.id = 123;
        System.out.println(p2.id);

        p2.id = 500;
        System.out.println(p1.id);

        // 静态方法
        Person.setId(1234567);
        System.out.println(Person.id);
    }
}

interface Father {
    // 编译器会自动加上public statc final:
    int MALE = 0;
    public static final String name = "Father";
}

class Person {
    public String name; // 实例字段
    public static int id; // 静态字段

    public Person(String name) {
        this.name = name;
    }

    public static void setId(int value) {
        id = value; // 由于 id 是静态方法 因此可以直接通过标识符访问
    }
}