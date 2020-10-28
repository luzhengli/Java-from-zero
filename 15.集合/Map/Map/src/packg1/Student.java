package packg1;

import java.util.Objects;

public class Student {
    public String name;
    public int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student s = (Student) obj;
            return Objects.equals(this.name, s.name) && (this.score == s.score);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.score);
    }
}
