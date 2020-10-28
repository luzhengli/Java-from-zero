public class cal_root {
    public static void main(String[] args) {
        // 求根公式
        double a = 3, b = 8, c = 4, r1, r2;

        double delta = Math.sqrt(b * b - 4 * a * c);
        r1 = (-b + delta) / 2 / a;
        r2 = (-b - delta) / 2 / a;        

        System.out.println(r1);
        System.out.println(r2);
    }
}
