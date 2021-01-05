package ru.job4j.product;
import org.joda.time.DateTime;
import org.joda.time.Days;
import java.util.Calendar;
import java.util.Date;

public class ControlQuality {
    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Shop getShop() {
        return shop;
    }

    public Trash getTrash() {
        return trash;
    }

    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;

    public ControlQuality(Warehouse warehouse, Shop shop, Trash trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void control(Food food) {
        Date today = Calendar.getInstance().getTime();
        Calendar expaireDate = food.getExpaireDate();
        Calendar createDate = food.getCreateDate();
        int expDay = Days.daysBetween(new DateTime(createDate), new DateTime(expaireDate)).getDays();
        double dayExpired = Days.daysBetween(new DateTime(expaireDate), new DateTime(today)).getDays();
        double percent = (1 - (dayExpired / expDay))*100;
        if (percent < 25) {
            warehouse.add(food);
            return;
        }
        if (percent <= 75) {
            shop.add(food);
            warehouse.delete(food);
            return;
        }
        if (percent <= 100) {
            shop.delete(food);
            food.setDisscount(10);
            shop.add(food);
            return;
        }
        shop.delete(food);
        trash.add(food);
    }

}

