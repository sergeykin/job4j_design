package ru.job4j.product;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class ControlQualityTest {

    @Test
    public void distibute() {
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
        assertThat(foods, is(controlQuality.getStorages().get(1).getStorage()));
    }

}