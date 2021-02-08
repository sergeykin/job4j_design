package thread;

public class MultiUser {
    public static void main(String[] args) throws InterruptedException {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.on();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.check();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        slave.start();
        Thread.sleep(3000);
        master.start();

    }
}
