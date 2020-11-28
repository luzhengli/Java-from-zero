import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        Stream.generate(new LocalDataSupplier()).limit(31)
                .filter(d -> d.getDayOfWeek() == DayOfWeek.SATURDAY || d.getDayOfWeek() == DayOfWeek.SUNDAY)
                .forEach(System.out::println);
    }
}

class LocalDataSupplier implements Supplier<LocalDate> {
    int n = -1;
    LocalDate start = LocalDate.of(2020, 11, 1);

    @Override
    public LocalDate get() {
        n++;
        return start.plusDays(n);
    }
}
