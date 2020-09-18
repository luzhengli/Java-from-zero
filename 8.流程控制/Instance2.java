public class Instance2 {
    public static void main(String[] args) {
        String a = "hello";
        String b = "HELLO".toLowerCase();
        System.out.println(a);
        System.out.println(b);

        // 比较引用的对象
        // if (a == b) {
        // System.out.println("a == b.");
        // } else {
        // System.out.println("a != b."); // 引用型变量引用的对象不同，因此 `==` 运算的结果为 false
        // }

        // 比较引用的内容
        // if (a.equals(b)) {
        //     System.out.println("a == b");
        // } else {
        //     System.out.println("a != b");
        // }


        //  null 对象调用 equals 方法
        a = null;
        if (a != null && a.equals(b)) {
            System.out.println("a == b");
        } else {
            System.out.println("a != b");
        }
    }
}
