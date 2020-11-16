import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class App {
    public static void main(String[] args) throws Exception {
        var counter = new Counter();
        var t = new Thread(() -> {
            counter.inc(1);
            counter.inc(3);
            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e) {
            //     System.out.println(e);
            // }
            counter.inc(5);
            counter.inc(7);
        });
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            var thread = new Thread() {
                public void run() {
                    counter.get();
                }
            };
            list.add(thread);
        }

        // 启动线程
        t.start();
        for (Thread thread : list) {
            thread.start();
        }
    }
}

class Counter {
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private final Lock rlock = rwlock.readLock();
    private final Lock wlock = rwlock.writeLock();
    private int[] counts = new int[10];

    public void inc(int index) {
        wlock.lock(); // 加写锁
        try {
            counts[index] = index;
        } finally {
            wlock.unlock(); // 释放写锁
        }
    }

    public void get() {
        rlock.lock(); // 加读锁
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(Arrays.toString(counts)); // 返回数组的副本
        } finally {
            rlock.unlock(); // 释放读锁
        }
    }
}