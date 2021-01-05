package ru.job4j.product;

import java.util.List;

public interface Storage {
    public boolean add(Food food);

    public boolean delete(Food food);

    public List<Food> getStorage();
}
