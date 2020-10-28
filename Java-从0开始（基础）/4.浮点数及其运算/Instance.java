import javax.sql.rowset.spi.SyncFactoryException;

public class Instance {
    public static void main(String[] args) {
        // 浮点数的运算可能产生误差的例子
        double x = 1.0 / 10;
        double y = 1 - 9.0 / 10;
        System.out.println(x);  // 
        System.out.println(y);  // 

        // 浮点数的比较
        double r = Math.abs(x-y);
        if(r<0.0001)
            System.out.println("x==y");
        else
            System.out.println("x!=y");

    }
}
