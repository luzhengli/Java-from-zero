public class Instance {
    public static void main(String[] args) {
        Student1 s = new Student1();
        s.age = 10;
        s.name = "li";
        System.out.println(s.hello());

        // 判断实例所属的类
        Person p = new Person();
        System.out.println(p instanceof Person);
        System.out.println(p instanceof Student);

        // 向上转型
        Father f = new Son();

        // 向下转型
        if (f instanceof Son) {
            Son ss = (Son) f;
            System.out.println("ok");
        }

        // 向下转型代码改写
        if (f instanceof Son k) {
            System.out.println("ok!!!");
        }

    }
}

class Person {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }
}

class Book{

}

class Student extends Person {
    private int score;
    private Book book;  

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
}

class Person1 {
    protected String name; //
    protected int age; //
}

class Student1 extends Person1 {
    public String hello() {
        return "Hello, " + name; // OK!
    }
}

class Person2 {
    protected String name; //
    protected int age; //

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Student2 extends Person2 {
    private int score;

    public Student2(String name, int age, int score) {
        super(name, age); // 调用当前类的构造函数前必须先显示调用父类的构造函数
        this.score = score;
    }

    public String hello() {
        return "Hello, " + name; // OK!
    }
}

class Father {

}

class Son extends Father {

}

