package packg;

public class Counter {
    private volatile int count = 0;

    public void add(int n) {
        // 加上n
        synchronized (this) {
            this.count += n;
        }
    }

    public void dec(int n) {
        // 加上n
        synchronized (this) {
            this.count -= n;
        }
    }

    public int getCount() {
        return this.count;
    }

}
