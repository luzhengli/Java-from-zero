import java.util.Arrays;

public class Instance2 {
    public static void main(String[] args) {
        // 定义一个二维数组
        int[][] a = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        System.out.println(a.length);

        // 二维数组的一行就是一个一维数组
        int[] b = a[1];
        System.out.println(Arrays.toString(b));

        // 二维数组的每行长度可以不一样
        int[][] c = {
            {1,2,3,4},
            {5,6},
            {7,8,9}
        };
        System.out.println(Arrays.deepToString(c));

        // 打印二维数组
        System.out.println(Arrays.deepToString(a));
    }
}
