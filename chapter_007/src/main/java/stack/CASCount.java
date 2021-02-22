package stack;


import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        count.set(0);
    }

    public void increment() {
        Integer newValue = count.get() + 1 ;
        count.getAndSet(newValue);
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        casCount.increment();
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 50; i++) {
                        casCount.increment();
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(casCount.get());
    }
}
