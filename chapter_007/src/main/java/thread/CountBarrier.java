package thread;


public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            System.out.println("count=" + String.valueOf(count));
            count ++;
            monitor.notifyAll();
        }
    }

    public void await() {
        System.out.println("await count = "+String.valueOf(count));
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int total = 10;
        CountBarrier countBarrier = new CountBarrier(total);
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    for (int i = 0; i <= 400*total; i++) {
                        countBarrier.count();
                    }
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        slave.start();
        master.start();



    }
}