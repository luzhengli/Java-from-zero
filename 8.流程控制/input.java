import java.util.Scanner;

import javax.swing.plaf.synth.SynthMenuBarUI;

public class input {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("input your name:");
        String name = scanner.nextLine(); // 读入字符串
        System.out.print("input your age:");
        int age = scanner.nextInt(); // 读入整数

        System.out.printf("Your name is %s, age is %d.", name, age);
    }
}
