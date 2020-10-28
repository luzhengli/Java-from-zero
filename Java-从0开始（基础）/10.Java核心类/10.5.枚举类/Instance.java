
public class Instance {
    public static void main(String[] args) {
        Weekday day = Weekday.SAT;
        if (day.dayValue == 6 || day.dayValue == 0) {
            System.out.println("Today is " + day.toString() + " work at home.");
        } else {
            System.out.println("Today is " + day.toString() + " work at office.");
        }

    }
}

enum Weekday {
    SUN(1, "星期日"), MON(2, "星期一"), TUE(3, "星期二"), WED(4, "星期三"), THU(5, "星期四"), FRI(6, "星期五"), SAT(0, "星期六");

    public final int dayValue;
    public final String chinese;

    private Weekday(int dayValue, String chinese) {
        this.dayValue = dayValue;
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return this.chinese;
    }
}