import java.util.LinkedList;
import java.util.Queue;

public class App {
    public static void main(String[] args) throws Exception {
        var task = new TaskQueue();
        new Thread(() -> {
            task.addTask("Hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            task.addTask("World");

        }).start();
        // Thread.sleep(1000);
        new Thread(() -> {
            task.getTask();
            task.getTask();
            // System.out.println(task.getTask());
        }).start();
    }
}

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        System.out.println("任务 " + s + " 入队成功！当前队列有 " + this.queue.size() + " 个任务！");
        this.notify();
    }

    public synchronized String getTask() {
        while (this.queue.isEmpty()) {
            System.out.println("等待队列加入任务...");
            // try {
            // Thread.sleep(1000);
            // } catch (Exception e) {
            // System.out.println(e);
            // }
            try {
                this.wait();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        String out = this.queue.remove();
        System.out.println("任务 " + out + " 出队成功！当前队列有 " + this.queue.size() + " 个任务！");
        return out;
    }
}