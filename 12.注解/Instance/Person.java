package Instance;

@Report(level = "Warn", value = "warn")
public class Person {
    @Range(min = 0, max = 5)
    public String name;

    @Range(min = 0, max = 50)
    public String city;

    @Range(min = 0, max = 20)
    public int age;

    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

}
