import java.util.Iterator;

import packg.ReverseArrayList;

public class App {
    public static void main(String[] args) {
        ReverseArrayList<String> rlist = new ReverseArrayList<>();
        rlist.add("Apple");
        rlist.add("Banana");
        rlist.add("Peach");
        for (Iterator<String> it = rlist.iterator(); it.hasNext();) {
        System.out.println(it.next());
        }
        for (String s : rlist) {
            System.out.println(s);
        }

    }
}
