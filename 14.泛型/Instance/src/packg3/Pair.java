package packg3;

public class Pair<T, E> {
    private T first;
    private E lase;

    public Pair(T first, E last) {
        this.first = first;
        this.lase = last;
    }

    public T getFirst() {
        return this.first;
    }

    public E getLast() {
        return this.lase;
    }

    // 静态泛型方法应该使用其他类型区分
    public static <P, Q> Pair<P, Q> create(P first, Q last) { // 不要忘记 static 后的<P, Q> 静态方法预先不知道泛型类型的具体类型 因此必须使用和实例方法不同的字母表示泛型类型
        System.out.println("Static method excuted...");
        return new Pair<P, Q>(first, last);
    }
}
