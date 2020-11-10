import packg.Counter;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("start main...");
        // var t1 = new Thread1();
        // var t2 = new Thread2();
        // t1.start();
        // t2.start();
        // t1.join();
        // t2.join();
        // System.out.println("Counter.count = " + Counter.count);
        // System.out.println("end main.");

        var t1 = new Counter();
        var t2 = new Counter();

        new Thread(() -> {
            t1.add(10);
        }).start();
        new Thread(() -> {
            t2.add(10);
        }).start();
        new Thread(() -> {
            t1.dec(11);
        }).start();
        new Thread(() -> {
            t2.dec(10);
        }).start();
        new Thread(() -> {
            System.out.println("t1.count = " + t1.getCount());
        }).start();
        new Thread(() -> {
            System.out.println("t2.count = " + t2.getCount());
        }).start();

    }
}

// class Counter {
// public static final Object lock = new Object();
// public volatile static int count = 0;
// }

// class Thread1 extends Thread {
// @Override
// public void run() {
// for (int i = 0; i < 10000; i++) {
// synchronized (Counter.lock) { // 获取锁
// Counter.count++;
// } // 释放锁
// }
// }
// }

// class Thread2 extends Thread {
// @Override
// public void run() {
// for (int i = 0; i < 10000; i++) {
// synchronized (Counter.lock) {
// Counter.count--;
// }
// }
// }
// }
