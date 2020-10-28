package packg1;

import java.util.Comparator;

public class NewStudent implements Comparable<NewStudent> {
    public String name;
    public int score;

    public NewStudent(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return String.format("{%s: score=%d}", name, score);
    }

    @Override
    public int compareTo(NewStudent o) {
        return -(this.score - o.score);
    }
}
