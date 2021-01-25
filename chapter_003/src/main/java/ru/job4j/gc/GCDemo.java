package ru.job4j.gc;
import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

public class GCDemo {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        User user = null;
        user = new User("", "","", "");
        System.out.println(sizeOf(user));
        user.setFam("d");
        System.out.println(sizeOf(user));
        System.out.println(sizeOf(new User()));
        for (int i = 0; i < 100; i++) {
            new User(String.valueOf(i), "fam".concat(String.valueOf(i)), "name".concat(String.valueOf(i)), "otch".concat(String.valueOf(i)));
        }
        System.gc();
        info();
    }
}
