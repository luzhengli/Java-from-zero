package Instance;

import java.lang.reflect.Field;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("li", "Beijing", 18);
        Person p2 = new Person("llillz", "Beijing", 18);
        out.println(p1);
        out.println(p2);
    }

    void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
        // 遍历所有Field:
        for (Field field : person.getClass().getFields()) {
            // 获取Field定义的@Range:
            Range range = field.getAnnotation(Range.class);
            // 如果@Range存在:
            if (range != null) {
                // 获取Field的值: 如果是 String 就对其范围进行合法性判断
                Object value = field.get(person); // 根据字段的方法调用字段的值 有点意思
                // 如果值是String:
                if (value instanceof String) {
                    String s = (String) value;
                    // 判断值是否满足@Range的min/max:
                    if (s.length() <= range.min() || s.length() >= range.max()) {
                        throw new IllegalArgumentException("Invalid field: " + field.getName());
                    }
                }

                if (value instanceof Integer) {
                    Integer i = (Integer) value;
                    if (i <= range.min() || i >= range.max()) {
                        throw new IllegalArgumentException("Invalid field: " + field.getName());
                    }
                }
            }
        }
    }
}

