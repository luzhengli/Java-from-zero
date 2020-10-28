package animals;

import animals.Animal; // 属于相同包的不同类之间的调用无需 import

public class MammalInt implements Animal {
    @Override
    public void eat() {
        System.out.println("MammalInt eats");
    }

    @Override
    public void travel() {
        System.out.println("Mammal travels");
    }

    public int noOfLegs() {
        return 0;
    }

    public static void init () {
        MammalInt m = new MammalInt();
        m.eat();
        m.travel();
    }
}
