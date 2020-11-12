import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class App {
    public static void main(String[] args) throws InterruptedException {
        var q = new TaskQueue();
        var ts = new ArrayList<Thread>();
        // 启动5个 task出队线程
        for (int i=0; i<5; i++) {
            var t = new Thread() {
                public void run() {
                    // 尝试获取task:
                    while (true) {
                        try {
                            String s = q.getTask();
                            System.out.println("execute task: " + s);
                        } catch (InterruptedException e) {
                            return; // 被中断时就结束线程
                        }
                    }
                }
            };
            t.start(); // 先启动线程
            ts.add(t); // 把启动了的线程添加到ArrayList
        }
        // 启动1个task入队线程 （执行10次入队操作）
        var add = new Thread(() -> {
            for (int i=0; i<10; i++) {
                String s = "t-" + Math.random();
                System.out.println("add task: " + s);
                q.addTask(s);
                try { Thread.sleep(100); } catch(InterruptedException e) {} // 等待100ms
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        // 使用中断结束5个获取task入队线程 否则它们将一直陷入while循环状态
        for (var t : ts) {
            t.interrupt();
        }
    }
}

class TaskQueue {
    Queue<String> queue = new LinkedList<>();
    
    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notifyAll(); // 唤醒其他在wait的所有线程
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.remove();
    }
}
