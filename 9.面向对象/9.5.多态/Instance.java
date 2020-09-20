public class Instance {
    public static void main(String[] args) {
        // Overload vs Override
        // Dog dog = new Hound(); // 引用变量的类型是Dog 真实对象类型是Hound
        // dog.bark();

        // 理解多态
        Animal am = new Cat();
        am.eat();
        am.sleep();
        am.run();
        // am.catchMouse();这里先注释掉，等会会说明
        // System.out.println(am.name);//这里先注释，待会说明
        System.out.println(am.num);
        System.out.println(am.age);

        // 多态不能使用子类的特有属性和方法
        // System.out.println(am.name);
        // am.catchMouse();

        // 通过向下转型使得能够使用子类的特有属性和方法
        if (am instanceof Cat) {
            Cat ct = (Cat) am;
            ct.catchMouse();
            System.out.println(ct.name);
        }
    }
}

class Dog {
    public void bark() {
        System.out.println("woof ");
    }
}

class Hound extends Dog {
    public void sniff() {
        System.out.println("sniff ");
    }

    @Override // 让编译器帮助检查是否进行了正确的覆写
    public void bark() {
        System.out.println("bowl");
    }
}

class Animal {
    int num = 10;
    static int age = 20;

    public void eat() {
        System.out.println("动物吃饭");
    }

    public static void sleep() {
        System.out.println("动物在睡觉");
    }

    public void run() {
        System.out.println("动物在奔跑");
    }
}

class Cat extends Animal {
    int num = 80;
    static int age = 90;
    String name = "tomCat";

    public void eat() {
        System.out.println("猫吃饭");
    }

    public static void sleep() {
        System.out.println("猫在睡觉");
    }

    public void catchMouse() {
        System.out.println("猫在抓老鼠");
    }

}