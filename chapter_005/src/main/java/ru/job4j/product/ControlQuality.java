package ru.job4j.product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControlQuality {

    public List<Storage> getStorages() {
        return storages;
    }

    private final List<Storage> storages ;

    public ControlQuality(List<Storage> storages) {
        this.storages = new ArrayList<>(storages);
    }

    public void control(Food food) {
        for (Storage storage:storages) {
            if (storage.accept(food)) {
                storage.add(food);
                break;
            }
        }
    }

    public void distibute() {
        List<Food> foods = new ArrayList<>();
        for (Storage storage:storages) {
            foods.addAll(storage.clear());
        }
        for (Food food:foods) {
            this.control(food);
        }
    }

    public static void main(String[] args) {
        String name = "food1";
        Calendar expaireDate = Calendar.getInstance();
        expaireDate.add(Calendar.DAY_OF_YEAR, 1);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_YEAR, -2);
        double price = 120;
        Food food = new Food(name, expaireDate, createDate, price);
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        List<Storage> storageList = new ArrayList<>();
        storageList.add(new Warehouse());
        Storage shop = new Shop();
        //shop.add(food);
        storageList.add(new Shop());
        Storage trash = new Trash();
        trash.add(food);
        storageList.add(trash);
        //storageList.add(new Trash());
        ControlQuality controlQuality = new ControlQuality(storageList);
        controlQuality.distibute();
        System.out.println(controlQuality.getStorages().get(1).getStorage());
    }

}

