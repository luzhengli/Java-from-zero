import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class App {
    public static void main(String[] args) throws Exception {
        // Queue<String> q = new PriorityQueue<>();
        // q.add("Apple");
        // q.add("Peach");
        // q.add("Banana");

        // System.out.println(q.remove());
        // System.out.println(q.remove());
        // System.out.println(q.remove());

        // 2. 银行用户的例子
        Queue<User> q = new PriorityQueue<>(new UserComparator());
        q.offer(new User("Ken", "A10"));
        q.offer(new User("Bob", "A1"));
        q.offer(new User("Peter", "V10"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss", "V1"));
        System.out.println(q.poll()); // Boss/V1
        System.out.println(q.poll()); // Bob/A1
        System.out.println(q.poll()); // Alice/A2
        System.out.println(q.poll()); // null,因为队列为空
        System.out.println(q.poll()); // null,因为队列为空
        System.out.println(q.poll()); // null,因为队列为空
        

    }
}

class UserComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if (o1.number.charAt(0) == o2.number.charAt(0)) {
            return Integer.parseInt(o1.number.substring(1)) - Integer.parseInt(o2.number.substring(1));
        } else {
            if (o1.number.charAt(0) == 'V') {
                return -1; // V开头 优先级高
            } else {
                return 1;
            }
        }
    }
}

class User {
    public final String name;
    public final String number;

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String toString() {
        return name + "/" + number;
    }
}
