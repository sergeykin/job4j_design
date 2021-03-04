package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutorService {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        pool.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000 ; i++) {
                    System.out.println("Execute " + Thread.currentThread().getName() + " count="+i);
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " - прерван");
                }

            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000 ; i++) {
                    System.out.println("Execute " + Thread.currentThread().getName() + " count="+i);
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " - прерван");
                }
            }
        });

        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Execute " + Thread.currentThread().getName());
    }
}
