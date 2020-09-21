public class calNum {
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        Person p4 = new Person();

        int count = Person.getCount();
        System.out.println(count);
    }
}

class Person {
    public static int count = 0;

    public Person() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}
