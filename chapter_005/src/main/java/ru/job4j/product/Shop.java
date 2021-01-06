package ru.job4j.product;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private List<Food> storage;

    public Shop() {
        storage = new ArrayList<>();
    }

    @Override
    public boolean accept(Food food) {
        double percent = CalcPercent.calc(food);
        if (percent >= 25 && percent <= 100 ) {
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
        double percent = CalcPercent.calc(food);
        if (percent > 75 && percent <= 100 ) {
            food.setDisscount(10);
        }
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
