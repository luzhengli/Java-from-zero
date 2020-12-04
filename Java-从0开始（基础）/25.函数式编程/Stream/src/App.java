import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        // Stream<String> stream = Stream.of("A", "B", "C");
        // stream.forEach(System.out::println);

        // Stream<String> s1 = Arrays.stream(new String[] { "A", "D", "E" });
        // Stream<String> s2 = List.of("B", "C").stream();
        // s1.forEach(System.out::println);
        // s2.forEach(System.out::println);

        // Stream<Integer> naturalStream = Stream.generate(new NaturalSupplier());
        // naturalStream.limit(10).forEach(System.out::println);

        // 文件
        // try (Stream<String> lines = Files.lines(Paths.get("src/test"))) {
        // lines.forEach(System.out::println);
        // }

        // 正则表达式
        // Pattern p = Pattern.compile("\\s+");
        // Stream<String> s = p.splitAsStream("This is my best friend.");
        // s.forEach(System.out::println);

        // 基本类型的Stream
        // int[]转为IntStream
        // IntStream in = Arrays.stream(new int[] { 1, 2, 3 });
        // in.forEach(System.out::println);
        // String[]转为LongStream
        // LongStream lo = List.of("1", "2", "3").stream().mapToLong(Long::parseLong);
        // lo.forEach(System.out::println);
        // System.out.println();
        LongStream ls = LongStream.generate(new FibSupplier());
        ls.limit(10).forEach(System.out::println);

        // map 操作
        Stream<String> sl = List.of("  Apple ", " pear ", " ORANGE", " BaNaNa ").stream();
        sl.map(String::trim).map(String::toUpperCase).forEach(System.out::println);
    }

}  

class NaturalSupplier implements Supplier<Integer> {
    int n = 0;

    @Override
    public Integer get() {
        return n++;
    }
}

class FibSupplier implements LongSupplier {
    private long a = 0;
    private long b = 1;
    private boolean flag = true;

    @Override
    public long getAsLong() {
        if (flag) {
            flag = false;
            a = a + b;
            return a;
        } else {
            flag = true;
            b = a + b;
            return b;
        }
    }

    // private long n0 = 0; // 无中生有 无理取闹 凭空想象 凭空捏造
    // private long n1 = 1; // 一生二 二生三 三生万物
    // private long next; // 一定不能被外界访问到

    // @Override
    // public long getAsLong() {
    // next = n0 + n1;
    // n0 = n1; // 第二位的数组位移到第一位
    // n1 = next; // 第二位的数字填充next，为下次输出做准备
    // return n0; // 拥有多少只=(:з」∠)_兔子
    // }
}
