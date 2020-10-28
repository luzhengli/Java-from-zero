import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class Instance {
    public static void main(final String[] args) throws Exception {
        BeanInfo info = Introspector.getBeanInfo(Person.class); // 获取 JavaBean 的信息
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) { // 获取所有属性的信息
            System.out.println(pd.getName()); // 属性
            System.out.println("\t" + pd.getReadMethod()); // 读方法
            System.out.println("\t" + pd.getWriteMethod()); // 写方法
        }
    }
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

}