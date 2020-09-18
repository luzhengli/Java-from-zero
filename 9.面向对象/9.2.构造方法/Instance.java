public class Instance {
    public static void main(String[] args) {
        // 构造方法
        Persion one = new Persion("li", 22);

        System.out.println(one.getName());
        System.out.println(one.getAge());

        // 空构造方法 默认初始化属性
        Persion p = new Persion();
        System.out.println(p.getName());  
        System.out.println(p.getAge());
        
        // 构造方法调用构造方法
        Persion q = new Persion("hu");
        System.out.println(q.getName());  
        System.out.println(q.getAge());  

        
    }
}

class Persion {
    private String name;
    private int age;

    public Persion(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Persion(String name){
        this(name, 23);  // Persion(String name)构造方法 调用 Persion(String name, int age)构造方法
    }
    

    public Persion(){

    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}