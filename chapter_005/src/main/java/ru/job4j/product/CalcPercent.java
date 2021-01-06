package ru.job4j.product;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Calendar;
import java.util.Date;

public class CalcPercent {
    public static double calc(Food food) {
        Date today = Calendar.getInstance().getTime();
        Calendar expaireDate = food.getExpaireDate();
        Calendar createDate = food.getCreateDate();
        int expDay = Days.daysBetween(new DateTime(createDate), new DateTime(expaireDate)).getDays();
        double dayExpired = Days.daysBetween(new DateTime(expaireDate), new DateTime(today)).getDays();
        double percent = (1 - (dayExpired / expDay))*100;
        return percent;
    }
}
