public class Instance {
    public static void main(String[] args) {
        Inner i = new Inner(); // 访问内部类
        i.hi();
    }

    // private 方法
    private static void Hello() {
        System.out.println("private hello!");
    }

    // 静态内部类
    static class Inner {
        public void hi() {
            Instance.Hello(); // 访问外部类的 private 方法
        }
    }
}
