package packg4;

public class Pair<T> {
    private T first;
    private T last;

    public Pair(Class<T> clazz) {
        first = clazz.newInstance();
        last = clazz.newInstance();
    }
}
