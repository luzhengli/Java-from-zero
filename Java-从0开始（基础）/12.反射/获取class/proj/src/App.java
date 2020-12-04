public class App {
    public static void main(String[] args) throws Exception{
        // printClassInfo("".getClass()); 
        // printClassInfo(Runnable.class); 
        // printClassInfo(java.time.Month.class);
        // printClassInfo(String[].class); 
        // printClassInfo(int.class);

    }

    static void printClassInfo(Class cls) {
        System.out.println("Class name: " + cls.getName()); // 类的全称名
        System.out.println("Simple name: " + cls.getSimpleName()); // 类的简称名
        if (cls.getPackage() != null) {
            System.out.println(P"Package name: " + cls.getPackage().getName()); // 类所在的包的包名
        }
        System.out.println("is interface: " + cls.isInterface()); // 是否是接口
        System.out.println("is enum: " + cls.isEnum()); // 是否是枚举
        System.out.println("is array: " + cls.isArray()); // 是否是数组
        System.out.println("is primitive: " + cls.isPrimitive());  // 是否是8种原始类型之一（原始类型的定义见文档）
        System.out.println();
    }
}
