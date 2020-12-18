package ru.job4j.kiss;


import java.util.Comparator;
import java.util.List;

public class MaxMin {
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
        return max(value, comparator.reversed());
    }

    public static void main(String[] args) {
        MaxMin maxMin = new MaxMin();
        System.out.println(maxMin.max(List.of(5,7,6,4), Comparator.naturalOrder()));
        System.out.println(maxMin.min(List.of(5,7,6,4), Comparator.naturalOrder()));

    }

}
