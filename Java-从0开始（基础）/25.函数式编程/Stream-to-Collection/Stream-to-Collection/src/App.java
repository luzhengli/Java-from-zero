import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        // Stream<String> stream = Stream.of("Apple", "", null, "Pear", " ", "Orange");
        // List<String> ls = stream.filter(str -> str != null &&
        // !str.isBlank()).collect(Collectors.toList());
        // System.out.println(ls);

        // List<String> list = List.of("Apple", "Banana", "Orange");
        // String[] arr = list.stream().toArray(String[]::new);
        // System.out.println(Arrays.toString(arr));

        // Stream<String> stream = Stream.of("APPL:Apple", "MSFT:Microsoft");
        // Map<String, String> map = stream.collect(Collectors.toMap(
        // // 把元素s映射为key:
        // s -> s.substring(0, s.indexOf(':')),
        // // 把元素s映射为value:
        // s -> s.substring(s.indexOf(':') + 1)));
        // System.out.println(map);

        // 分组输出
        List<String> list = List.of("Apple", "Banana", "Blackberry", "Coconut", "Avocado", "Cherry", "Apricots");
        Map<String, List<String>> groups = list.stream()
                .collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList()));
        System.out.println(groups);
    }
}
