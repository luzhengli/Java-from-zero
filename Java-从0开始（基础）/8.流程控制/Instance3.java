import javax.lang.model.util.ElementScanner14;

public class Instance3 {
    public static void main(String[] args) {
        // switch 语句
        // String fruit = "apple";
        // switch (fruit) {
        // case "apple":
        // System.out.println("Select apple.");
        // break;
        // case "banana":
        // System.out.println("Select banana.");
        // break;
        // case "mango":
        // System.out.println("Select mango.");
        // break;
        // default:
        // System.out.println("Don't select.");
        // }

        // switch 表达式：返回水果的价格
        // String fruit = "peach";
        // int price = switch (fruit) {
        // case "apple" -> 10;
        // case "banana" -> 13;
        // case "mango" -> 12;
        // default -> -1;
        // }; // 赋值语句要用分号结尾
        // System.out.printf("The price of %s is %d$", fruit, price);

        // yield 返回值
        String fruit = "mango";
        int price = switch (fruit) {
            case "apple" -> 10;
            case "banana" -> 13;
            case "mango" -> 12;
            default -> {
                int code = fruit.hashCode();
                yield code;
            }
        };
        if (fruit.equals("other")) {
            System.out.printf("The hashCode of %s is %d", fruit, price);
        } else {
            System.out.printf("The price of %s is %d$", fruit, price);
        }

    }
}
