
public class Instance {
    public static void main(String[] args) {
        // // 引例
        // String s = "";
        // for (int i = 0; i < 1000; i++) {
        // s = s + "," + i;
        // }
        // System.out.println(s);

        // // 特点1. 不创建新的临时对象
        // StringBuilder sb = new StringBuilder(1024);
        // for (int i = 0; i < 1000; i++) {
        // // sb.append(',');
        // sb.append(i);
        // }
        // String s = sb.toString();
        // // System.out.println(s);

        // // 特点2. 支持链式操作
        // var sb = new StringBuilder(1024);
        // sb.append("Mr ").append("Bob").append("!").insert(0, "Hello, ");
        // System.out.println(sb.toString()); // Hello, Mr Bob!

        // // 自定义链式方法
        // var adder = new Adder();
        // int value = adder.add(3).inc().add(2).value();
        // System.out.println(value);

        // StringJoiner 类
        // String[] names = { "Bob", "Alice", "Grace" };
        // var sj = new StringJoiner(" - ");
        // for (String name : names) {
        // sj.add(name);
        // }
        // System.out.println(sj.toString()); // Bob - Alice - Grace

        // String[] names = { "Bob", "Alice", "Grace" };
        // var sj = new StringJoiner(", ", "Hi~", "!");
        // for (String name : names) {
        // sj.add(name);
        // }
        // System.out.println(sj.toString()); // Hi~Bob, Alice, Grace!

        String[] names = { "Bob", "Alice", "Grace" };
        String new_names = String.join(", ", names);
        System.out.println(new_names); // Bob, Alice, Grace
    }
}

class Adder {
    private int sum = 0;

    public Adder add(int n) {
        // 增加一个值
        this.sum += n;
        return this;
    }

    public Adder inc() {
        this.sum += 1;
        return this;
    }

    public int value() {
        return this.sum;
    }
}