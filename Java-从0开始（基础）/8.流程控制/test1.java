import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入原来的成绩：");
        float a = scanner.nextFloat();
        System.out.print("输入现在的成绩：");
        float b = scanner.nextFloat();
        float rate = (b - a) / a;
        System.out.printf("成绩提高了%.2f%%", rate * 100);
    }
}
