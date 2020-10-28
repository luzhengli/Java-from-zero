import jdk.nashorn.api.tree.Tree;

public class Instance {
    public static void main(String[] args) {
        // // 短路原则
        // boolean a = 5 < 3; // False
        // boolean result = a && (5 / 0 > 1); // False 遇到 && 直接计算得到结果 因此后面的代码实际不会执行
        // System.out.println(result);

        // 三目运算符：返回较大的值
        int a = 2, b = 3;
        System.out.println(a > b ? a : b);
    }
}
