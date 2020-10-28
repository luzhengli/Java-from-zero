public class Instance {
    public static void main(String[] args) {
        int n = 5;
        int[] a = new int[5];
        a[0] = 1;
        a[1] = 2;
        a[2] = 3;
        a[3] = 4;
        a[4] = 5;
        while (n != 0) {
            System.out.println(a[n - 1]);
            n--;
        }

        System.out.println("数组a的长度：" + a.length);

        int[] b = new int[] { 99, 88, 77 };
        System.out.println("数组b的长度：" + b.length);

        int[] c = { 99, 88, 77 };
        System.out.println("数组c的长度：" + c.length);
    }
}
