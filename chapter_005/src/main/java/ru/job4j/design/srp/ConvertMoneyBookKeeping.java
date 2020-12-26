package ru.job4j.design.srp;

public class ConvertMoneyBookKeeping implements ConvertMoney {
    @Override
    public Double convert(Double money) {
        return money * 100;
    }
}
