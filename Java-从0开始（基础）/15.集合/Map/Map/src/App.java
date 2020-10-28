import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.System.out;

import java.time.DayOfWeek;

import packg1.*;

public class App {
    public static void main(String[] args) throws Exception {
        // 1.创建 Map 、 建立映射、通过 key 获取 value
        // Student s = new Student("Li", 89);
        // Map<String, Student> map = new HashMap<>();
        // map.put("Li", s); // 将"Li" 映射到实例s
        // Student target = map.get("Li"); // 根据 key 查询 value
        // out.println(target == s);

        // map.put("Li", new Student("Yang", 100)); // 重复对同一 key 作映射，会导致 value 的覆盖
        // out.println(map.get("Li").name);

        // Student other = map.get("Hu"); // 查询不存在的 key 返回 null
        // out.println(other);

        // out.println(map.containsKey("Li")); // 查询某个 key 是否存在

        // 2. 遍历 key
        // Map<String, Integer> map = new HashMap<>();
        // map.put("Li", 90);
        // map.put("Hu", 80);
        // map.put("Zhang", 100);
        // for (String name : map.keySet()) {
        // out.println(name + "'score: " + map.get(name));
        // }

        // 3. 遍历key和value
        // for (Map.Entry<String, Integer> entry : map.entrySet()) {
        // out.println(entry.getKey() + "'score: " + entry.getValue());
        // }

        // 4. 自定义类作为 key
        // Map<Student, String> map = new HashMap<>();
        // Student s1 = new Student("Li", 20);
        // Student s2 = new Student("Li", 20);
        // out.println(s1.equals(s2));
        // map.put(s1, "Li");
        // out.println(map.get(s1));
        // out.println(map.get(s2));

        // 5. 使用EnumMap
        // Map<DayOfWeek, String> map = new EnumMap<>(DayOfWeek.class);
        // map.put(DayOfWeek.MONDAY, "星期一");
        // map.put(DayOfWeek.TUESDAY, "星期二");
        // map.put(DayOfWeek.WEDNESDAY, "星期三");
        // map.put(DayOfWeek.THURSDAY, "星期四");
        // map.put(DayOfWeek.FRIDAY, "星期五");
        // map.put(DayOfWeek.SATURDAY, "星期六");
        // map.put(DayOfWeek.SUNDAY, "星期日");
        // out.println(map);
        // out.println(map.get(DayOfWeek.THURSDAY));

        // 6. TreeMap
        // Map<Person, Integer> map = new TreeMap<>(new Comparator<Person>() { // 传入
        // TreeMap 的部分如何理解？
        // public int compare(Person p1, Person p2) {
        // // 作为Key的class没有实现Comparable接口，那么，必须在创建TreeMap时同时指定一个自定义排序算法
        // return p1.name.compareTo(p2.name);
        // }
        // });
        // map.put(new Person("Tom"), 1);
        // map.put(new Person("Bob"), 2);
        // map.put(new Person("Lily"), 3);
        // for (Person key : map.keySet()) {
        // System.out.println(key);
        // }
        // System.out.println(map.get(new Person("Bob"))); // map 是 TreeMap 无需实现
        // equals() 和 hashCode()

        // Map<NewStudent, Integer> map = new TreeMap<>(new Comparator<NewStudent>() { // 传入 TreeMap 的部分如何理解？
        //     public int compare(NewStudent p1, NewStudent p2) {
        //         // if (p1.score == p2.score)
        //         //     return 0;
        //         // return p1.score > p2.score ? -1 : 1; // score 大的在前
        //         return -Integer.compare(p1.score, p2.score); // 注意减号
        //     }
        // });
        Map<NewStudent, Integer> map = new TreeMap<>();
        map.put(new NewStudent("Tom", 77), 1);
        map.put(new NewStudent("Bob", 66), 2);
        map.put(new NewStudent("Lily", 99), 3);
        for (NewStudent key : map.keySet()) {
            System.out.println(key);
        }
        System.out.println(map.get(new NewStudent("Bob", 66))); // 2
    }
}
