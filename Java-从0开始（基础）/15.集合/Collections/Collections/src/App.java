import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // 1. 排序和洗牌
        // List<String> list = new ArrayList<>();
        // list.add("Peach");
        // list.add("Apple");
        // list.add("Banana");

        // System.out.println("排序前：" + list);
        // Collections.sort(list);
        // System.out.println("排序后：" + list);
        // Collections.shuffle(list);
        // System.out.println("第一次洗牌" + list);
        // Collections.shuffle(list);
        // System.out.println("第二次洗牌" + list);

        // 2. 封装为不可变对象
        List<String> list = new ArrayList<>();
        list.add("Peach");
        list.add("Apple");
        List<String> immutable = Collections.unmodifiableList(list);
        try {
            immutable.add("Banana");
        } catch (UnsupportedOperationException e) {
            System.err.println("捕获到错误：" + e);
        }
        list.add("Banana");
        System.out.println(list);
        System.out.println(immutable);

    };
}
