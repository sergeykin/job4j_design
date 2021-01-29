package memory;


import java.util.concurrent.atomic.AtomicReference;

public final class DCLSingleton {
    private volatile static DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }


    private DCLSingleton() {
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    while (inst == null) {
                        System.out.println(Thread.currentThread().getName());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        inst = DCLSingleton.instOf();
        thread.join();

    }
}
