package packg5;

public class Pair2<T> {
    private T first;
    private T last;

    public Pair2(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }

    public T getLast() {
        return last;
    }

}
