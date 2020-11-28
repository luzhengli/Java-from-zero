import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        // 累加
        // int sum = Stream.of(1, 2, 3, 4).reduce(0, (acc, n) -> acc + n);
        // System.out.println(sum);

        // 累乘
        // int product = Stream.of(1, 2, 3, 4).reduce(1, (acc, n) -> acc * n);
        // System.out.println(product);

        // 把配置文件信息转为Map
        List<String> props = List.of("profile=native", "debug=true", "logging=warn", "interval=500");
        Map<String, String> map = props.stream()
                // 把元素从k=v转换为Map[k]=v:
                .map(kv -> {
                    String[] ss = kv.split("\\=", 2);
                    return Map.of(ss[0], ss[1]);
                    // 把元素组合成一个Map
                }).reduce(new HashMap<String, String>(), (m, kv) -> {
                    m.putAll(kv);
                    return m; // Map.putAll 没有返回值 因此需要return返回
                });
        map.forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });

    }
}
