import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Instance {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(Instance.class);
        // log.info("start...");
        // log.warn("end.");

        // 静态方法引用Log
        // static final Log log = LogFactory.getLog(Instance.class);

        // static void foo(){
        // log.info("test");
        // }

        // 在实例方法中引用Log
        // Person p = new Person();
        // p.foo();

        // Student s = new Student();
        // s.foo();

        // 记录异常
        try{
            "".getBytes("addsa");
        } catch(Exception e){
            log.error("got exception!", e);
        }
    }
}

class Person {
    protected final Log log = LogFactory.getLog(getClass());

    final void foo() {
        log.info("foo");
    }
}

// 在子类中使用父类实例化的log:
class Student extends Person {
    void bar() {
        log.info("bar");
    }
}