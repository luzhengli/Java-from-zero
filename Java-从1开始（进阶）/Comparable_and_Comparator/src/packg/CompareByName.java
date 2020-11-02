package packg;

import java.util.Comparator;

public class CompareByName implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName()); // 英文字符串根据 ASCII 码比较
    }
}
