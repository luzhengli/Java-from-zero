import java.io.PrintStream;

public class App {
    public static void main(String[] args) throws InterruptedException { // 主线程启动main函数 main函数可以再创建线程
        // 1.中断线程
        // PrintStream out = System.out;
        // out.println("start main...");
        // Thread t = new MyThread();
        // t.start();
        // Thread.sleep(1);
        // t.interrupt(); // main线程请求t线程中断
        // t.join();
        // out.println("end main.");

        // 2. 中断等待结束的线程会抛出异常
        // Thread t = new MyThread(); // 创建t线程
        // t.start(); // 启动t线程
        // Thread.sleep(1000); // 主线程等待1s
        // t.interrupt(); // 中断t线程
        // t.join(); // 等待t线程结束
        // System.out.println("end");

        // 3. 通过running标志位中断线程
        HelloThread hello = new HelloThread();
        hello.start();
        Thread.sleep(1); // 这里让主进程main等待一会是为了让hello进程有空打印一些内容 不至于马上就中断退出
        hello.running = false;
    }
}

class HelloThread extends Thread {
    public volatile boolean running = true; // 线程间共享变量使用 volatile 关键字标识

    @Override
    public void run() {
        int n = 0;
        while (running) {
            n++;
            System.out.println(n + " hello!");
        }
        System.out.println("end hello.");
    }

}