import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class App {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(4); // 创建一个固定大小的线程池
        for (int i = 0; i < 6; i++) {
            es.submit(new Task("" + i)); // 提交任务
        }
        es.shutdown(); // 关闭线程池
    }
}

class Task implements Runnable { // 定义Task类（实现Runnable接口）
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start task " + name);
        try {
            Thread.sleep(1000); // 等待1s
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("end task " + name);
    }
}
