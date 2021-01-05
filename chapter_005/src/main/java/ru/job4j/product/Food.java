package ru.job4j.product;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Food {
    private String name;
    private Calendar expaireDate;
    private Calendar createDate;
    private double price;
    private double disscount = 0;

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", expaireDate=" + new SimpleDateFormat("dd.MM.yyyy").format(expaireDate.getTime())  +
                ", createDate=" + new SimpleDateFormat("dd.MM.yyyy").format(createDate.getTime())  +
                ", price=" + price +
                ", disscount=" + disscount +
                '}';
    }

    public Food(String name, Calendar expaireDate, Calendar createDate, double price) {
        this.name = name;
        this.expaireDate = expaireDate;
        this.createDate = createDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpaireDate() {
        return expaireDate;
    }

    public void setExpaireDate(Calendar expaireDate) {
        this.expaireDate = expaireDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDisscount() {
        return disscount;
    }

    public void setDisscount(double disscount) {
        this.disscount = disscount;
    }
}
