package packg1;

public class Person {
    public String name;
    // public int score;

    public Person(String name){
        this.name = name;
        // this.score = score;
    }

    @Override
    public String toString() {
        return String.format("Person:"+"%s", this.name);
    }
}
