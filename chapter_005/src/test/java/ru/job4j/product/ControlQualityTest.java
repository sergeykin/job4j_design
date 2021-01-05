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
    public void control() {
        String name = "food1";
        Calendar expaireDate = Calendar.getInstance();
        expaireDate.add(Calendar.DAY_OF_YEAR, 1);
        Calendar createDate = Calendar.getInstance();
        createDate.add(Calendar.DAY_OF_YEAR, -2);
        double price = 120;
        Food food = new Food(name, expaireDate, createDate, price);
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        ControlQuality controlQuality = new ControlQuality(new Warehouse(), new Shop(), new Trash());
        controlQuality.control(food);
        assertThat(foods, is(controlQuality.getShop().getStorage()));
    }

}