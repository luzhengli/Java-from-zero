public class App {
    public static void main(String[] args) throws Exception {
        var p = new Person("li", 23);
        System.out.println(p.getName() + ": " + p.getAge());
        Object c1 = p.getClass();
        System.out.println(c1);
        Object c2 = Class.forName("Person");
        System.out.println(c2);
        Object c3 = Person.class;
        System.out.println(c3);
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
