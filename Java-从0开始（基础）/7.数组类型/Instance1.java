import java.util.Arrays;

public class Instance1 {
    public static void main(String[] args) {
        // 输出数组
        int[] a = new int[] { 4, 2, 3, 1 };
        System.out.println(Arrays.toString(a));

        // 排序
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
