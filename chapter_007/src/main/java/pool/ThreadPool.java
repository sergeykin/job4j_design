package pool;


import queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    int size;

    public ThreadPool() {
        size = Runtime.getRuntime().availableProcessors();

    }

    public void start() {
        for (int i = 0; i < size; i++) {
            Thread thread = new SimpleThread(tasks);
            thread.start();
            threads.add(thread);
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
