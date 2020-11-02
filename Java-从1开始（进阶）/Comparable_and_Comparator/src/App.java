import java.util.ArrayList;
import java.util.List;

import packg.CompareByAge;
import packg.CompareByName;
import packg.Student;

public class App {
    public static void main(String[] args) throws Exception {
        List<Student> list = new ArrayList<>();
        list.add(new Student("wang2", 20));
        list.add(new Student("wang3", 10));
        list.add(new Student("wang1", 30));

        System.out.println("按照名字排序：");
        list.sort(new CompareByName());
        System.out.println(list);
        System.out.println("按照年龄排序：");
        list.sort(new CompareByAge());
        System.out.println(list);

    }
}
