package packg5;

// import packg5;
public class PairHelper{ 
    public static int add(Pair2<? extends Number> p ){
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }
}