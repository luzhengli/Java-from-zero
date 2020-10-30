package packg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReverseArrayList<T> implements Iterable<T> {
    private List<T> list = new ArrayList<>();

    public void add(T element) {
        list.add(element);
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator(list.size());
    }

    class ReverseIterator implements Iterator<T> { // 这里ReverseIterator后为何不加<T>?
        private int index;

        public ReverseIterator(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return this.index > 0;
        }

        @Override
        public T next() {
            index--;
            return ReverseArrayList.this.list.get(index);
        }
    }

}
