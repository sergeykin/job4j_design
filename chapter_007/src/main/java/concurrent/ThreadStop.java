package concurrent;

//public class ThreadStop {
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(
//                () -> {
//                    int count = 0;
//                    while (!Thread.currentThread().isInterrupted()) {
//                        System.out.println(count++);
//                    }
//                }
//        );
//        thread.start();
//        Thread.sleep(10);
//        thread.interrupt();
//    }
//}

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println("start ...");
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().isInterrupted());
                            System.out.println(Thread.currentThread().getState());
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
        progress.join();
    }
}