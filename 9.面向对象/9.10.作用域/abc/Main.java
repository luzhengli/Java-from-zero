// package abc;

public class Main {

    public static void foo() {
        // 可以访问 package 权限的类
        Hello h = new Hello();

        // 可以调用 package 权限的方法
        h.hi();
    }
}
