package packg;

import java.util.Comparator;

public class CompareByAge implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getAge() - o2.getAge();
    }
}
