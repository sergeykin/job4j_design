package synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.array.SimpleArray;

import java.util.Collections;
import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private SimpleArray simpleArray = new SimpleArray();

    public synchronized void add(T value) {
        simpleArray.add(value);
    }

    public synchronized T get(int index) {
        return (T) simpleArray.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.simpleArray).iterator();
    }

    private Iterable<T> copy(SimpleArray array) {
        SimpleArray<T> tmp = new SimpleArray<>();
        for (Object t:array) {
            tmp.add((T)t);
        }
        return tmp;
    }
}
