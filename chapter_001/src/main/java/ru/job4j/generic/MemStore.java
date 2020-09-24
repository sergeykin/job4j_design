package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        if (findById(model.getId()) == null) {
            mem.add(model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        int index = getIndex(id);
        if (index != -1) {
            mem.set(index, model);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        int index = getIndex(id);
        if (index != -1) {
            return mem.remove(mem.get(index));
        } else {
            return false;
        }
    }

    @Override
    public T findById(String id) {
        for (T memo:mem) {
            if (memo.getId().equals(id)) {
                return memo;
            }
        }
        return null;
    }

    private int getIndex(String id) {
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
