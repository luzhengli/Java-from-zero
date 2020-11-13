import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) throws Exception {
        var c1 = new Counter();
        var c2 = new newCounter();
        var c3 = new newCounterWithLimit();
        var q = new TaskQueue();
        // var t = new Thread(() -> {
        // try {
        // c3.add(10);
        // c3.add(20);
        // } catch (InterruptedException e) {
        // System.out.println(e);
        // }
        // });
        // t.start();
        // t.join();
        // System.out.println(c3.getCounter());

        var t1 = new Thread(() -> {
            try {
                q.addTask("Hello");
                System.out.println("已添加任务1.");
                Thread.sleep(1000);
                q.addTask("World");
                System.out.println("已添加任务2.");
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });
        var t2 = new Thread(() -> {
            try {
                System.out.println(q.getTask());
                System.out.println(q.getTask());
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // System.out.println(c3.getCounter());
    }
}

class Counter {
    private volatile int counter = 0;

    public int getCounter() {
        return counter;
    }

    public synchronized void add(int n) {
        counter += n;
    }
}

class newCounter {
    private volatile int counter = 0;
    private final Lock lock = new ReentrantLock(); // 创建ReentrantLock锁对象

    public int getCounter() {
        return counter;
    }

    public void add(int n) {
        lock.lock(); // 上锁
        try {
            counter += n;
        } finally {
            lock.unlock(); // 解锁
        }
    }
}

class newCounterWithLimit {
    private volatile int counter = 0;
    private final Lock lock = new ReentrantLock(); // 创建ReentrantLock锁对象

    public int getCounter() {
        return counter;
    }

    public void add(int n) throws InterruptedException {
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                counter += n;
            } finally {
                lock.unlock(); // 解锁
            }
        }
    }
}

class TaskQueue {
    private final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition(); // 创建一个与Lock绑定的实例condition
    private Queue<String> q = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();
        try {
            q.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (q.isEmpty()) {
                condition.await();
            }
            return q.remove();
        } finally {
            lock.unlock();
        }
    }
}