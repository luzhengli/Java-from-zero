package packg2;

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int compareTo(Person other){ // 重写 comPareTo 方法 定义 Person 类的比较规则
        return this.name.compareTo(other.name);
    }

    public String toString() { // 重写 toString 方法 定义数组的输出
        return this.name + "-" + this.age;
    }
}
