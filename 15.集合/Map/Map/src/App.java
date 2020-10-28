import java.util.HashMap;
import java.util.Map;
import static java.lang.System.out;

import packg1.Student;

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
        //     out.println(entry.getKey() + "'score: " + entry.getValue());
        // }

        // 4. 自定义类作为 key
        Map<Student, String> map = new HashMap<>();
        Student s1 = new Student("Li", 20);
        Student s2 = new Student("Li", 20);
        out.println(s1.equals(s2));
        map.put(s1, "Li");
        out.println(map.get(s1));
        out.println(map.get(s2));
    }
}
