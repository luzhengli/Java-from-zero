public class Instance {
    public static void main(String[] args) {
        // 字符串的重载函数
        String s = "Test string";
        int n1 = s.indexOf('t');
        int n2 = s.indexOf("st");
        int n3 = s.indexOf("st", 4);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);

        // 
        Test t = new Test();
        t.hello("li", "world");
        System.out.println();
        t.hello("world");
    }
}

class Test {
    public void hello(String str) {
        System.out.printf("hello %s", str);
    }

    public void hello(String name, String str){
        System.out.printf("%s, hello %s", name, str);
    }
}