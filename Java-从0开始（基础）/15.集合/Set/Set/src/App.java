import java.util.List;
import java.util.TreeSet;
import java.util.SortedSet;

public class App {
    public static void main(String[] args) throws Exception {
        List<Message> received = List.of(new Message(5, "下次去玩"),new Message(1, "Hello!"), new Message(2, "发工资了吗？"), new Message(2, "发工资了吗？"),
                new Message(3, "去哪吃饭？"), new Message(3, "去哪吃饭？"), new Message(3, "去哪吃饭？"), new Message(4, "Bye"));
        List<Message> displayMessages = process(received);
        for (Message message : displayMessages) {
            System.out.println(message);
        }
    }

    static List<Message> process(List<Message> received) {
        SortedSet<Message> sortedSet = new TreeSet<>();
        for (Message message : received) {
            sortedSet.add(message);
        }
        List<Message> list = List.of(sortedSet.toArray(new Message[sortedSet.size()]));
        return list;
    }
}

class Message implements Comparable<Message> {
    public final int sequence;
    public final String text;

    public Message(int sequence, String text) {
        this.sequence = sequence;
        this.text = text;
    }

    @Override
    public String toString() {
        return this.sequence + ":" + this.text;
    }

    @Override
    public int compareTo(Message o) {
        return this.sequence - o.sequence;
    }
}
