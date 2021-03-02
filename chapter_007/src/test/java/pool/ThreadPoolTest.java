package pool;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ThreadPoolTest {

    @Test
    public void test() {
        ThreadPool threadPool = new ThreadPool();
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 100; i++) {
            threadPool.work(runnable);
        }
        threadPool.start();
        threadPool.shutdown();

        Assert.assertThat(list.size(), is(10000));
    }
}