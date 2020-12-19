package ru.job4j.solid;

import java.util.Comparator;
import java.util.List;
//1. добавление изменений в базовый метод, а не расширение его в дочерних
//2. повтор кода
//3. нарушение принципа единственной обязанности, в данном случае и возврат значение и его печать на консоль
public class BaseClass {
    void get(String string) {
        //1. добавление изменений в базовый метод, а не расширение его в дочерних
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        if (value.size() == 0) {
            return null;
        }
        T max = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (comparator.compare(max, value.get(i)) < 0) {
                max = value.get(i);
            }
        }
        return max;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        //2. повтор кода
        if (value.size() == 0) {
            return null;
        }
        T min = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (comparator.compare(min, value.get(i)) > 0) {
                min = value.get(i);
            }
        }
        //3. нарушение принципа единственной обязанности, в данном случае и возврат значение и его печать на консоль
        System.out.println(min);
        return min;
    }
}
