package concurrent;

import java.util.Arrays;

public class ConsoleProgress implements Runnable {
    String[] process = {"-", "\\", "|", "/"};
    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r load: " + process[count++]);
                if (count > 3) {
                    count = 0;
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt(); //
    }
}
