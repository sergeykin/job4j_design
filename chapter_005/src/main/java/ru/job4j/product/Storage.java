package ru.job4j.product;

import java.util.List;

public interface Storage {
    public void add(Food food);

    public boolean delete(Food food);

    public List<Food> getStorage();

    public boolean accept(Food food);

    public List<Food> clear();
}
