import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import packg1.Person;

import java.util.Collections;

// import java.util.Map;
// import java.util.Set;
import static java.lang.System.out;

public class App {
    public static void main(String[] args) throws Exception {
        // 1. 创建 List：通过 ArrayList
        // List<String> list = new ArrayList<String>();
        // list.add("apple");
        // list.add("banana");
        // list.add(null);
        // list.add("apple");
        // String s = list.get(2); // 获取 null
        // out.println(list.size()); // 4
        // out.println(s); // null

        // 2. 创建 List：通过 List.of()
        // List<String> list = List.of("Apple", "Banana", "Apple");
        // out.println(list.contains("Apple")); // ture
        // out.println(list.contains("apple")); // false
        // out.println(list.indexOf("Banana")); // 1
        // out.println(list); // [1, 2, 3]

        // 3. 通过 Iterator 遍历 List
        // for (Iterator<String> it = list.iterator(); it.hasNext();) {
        // String n = it.next();
        // out.println(n);
        // }

        // 4. 使用 for each 循环
        // for (String s : list) {
        // out.println(s);
        // }

        // 5. List 转 Array
        // List<Integer> list = List.of(12, 34, 56);
        // Integer[] array = list.toArray(new Integer[3]); // 传入 Array
        // for (Integer n : array) {
        // out.println(n);
        // }

        // Number[] array2 = list.toArray(new Number[3]);
        // for (Number n : array2) {
        // out.println(n);
        // }

        // 6. Array 转 List
        // Integer[] array = { 1, 2, 3 };
        // List list = List.of(array);
        // out.println(list);

        /*
         * // test1：给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字 // 构造从start到end的序列：
         * final int start = 10; final int end = 20; List<Integer> list = new
         * ArrayList<>(); for (int i = start; i <= end; i++) { list.add(i); } //
         * 洗牌算法shuffle可以随机交换List中的元素位置: Collections.shuffle(list); // 随机删除List中的一个元素:
         * int removed = list.remove((int) (Math.random() * list.size())); int found =
         * findMissingNumber(start, end, list); System.out.println(list.toString());
         * System.out.println("missing number: " + found); System.out.println(removed ==
         * found ? "测试成功" : "测试失败");
         */

        // 7. 测试 Person 对象的 List.contains() 方法
        // List<Person> list = List.of(
        // new Person("Hu"),
        // new Person("Xiao"),
        // new Person("Li")
        // );
        // out.println(list.contains(new Person("Li"))); // false

        // 8. 重写 equals() 方法
        List<Person> list = List.of(new Person("Hu", 18), new Person("Xiao", 20), new Person("Li", 22));
        out.println(list.contains(new Person("Li", 22))); // false

    }

    static int findMissingNumber(int start, int end, List<Integer> list) {
        int found = 0;
        List<Integer> tempList = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            tempList.add(i);
        }

        for (Integer n : tempList) {
            if (!list.contains(n)) {
                found = n;
                break;
            }
        }
        return found;
    }
}
