package ru.job4j.product;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private List<Food> storage;

    public Warehouse() {
        storage = new ArrayList<>();
    }

    @Override
    public boolean accept(Food food) {
        double percent = CalcPercent.calc(food);
        if (percent >= 0 && percent < 25 ) {
            return true;
        }
        return false;
    }

    @Override
    public List<Food> clear() {
        List<Food> list = new ArrayList<>(storage);
        storage.clear();
        return list;
    }

    @Override
    public void add(Food food) {
        storage.add(food);
    }

    @Override
    public boolean delete(Food food) {
        return storage.remove(food);
    }

    @Override
    public List<Food> getStorage() {
        return storage;
    }
}
