package ru.job4j.product;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private List<Food> storage;

    public Shop() {
        storage = new ArrayList<>();
    }

    @Override
    public boolean add(Food food) {
        storage.add(food);
        return true;
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
