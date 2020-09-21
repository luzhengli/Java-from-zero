import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Instance {
    public static void main(String[] args) {
        Student s = new Student("luyao");

        s.run();
        System.out.println(s.getName());

        // List list = new ArrayList();
        // Collection coll = list;
        // Iterable it = coll;
    }
}

interface Person {
    default void run() {
        System.out.println(getName() + " run.");
    }

    String getName();
}

class Student implements Person {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    // @Override
    // public void run() {
    // System.out.println("Student run.");
    // }

    @Override
    public String getName() {
        return this.name;
    }
}
