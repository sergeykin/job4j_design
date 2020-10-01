package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();

        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();

        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.next();
                i.add(value);
                break;
            } else {
                i.next();
            }

        }
    }

    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        Objects.requireNonNull(filter);
        List<T> out = new ArrayList<>();
        ListIterator<T> each = list.listIterator();
        while (each.hasNext()) {
            T tmp = each.next();
            if (filter.test(tmp)) {
                out.add(tmp);
                each.remove();
            }
        }
        return out;
    }

    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        Objects.requireNonNull(filter);
        List<T> out = new ArrayList<>();
        ListIterator<T> each = list.listIterator();
        while (each.hasNext()) {
            T tmp = each.next();
            if (filter.test(tmp)) {
                out.add(tmp);
                each.set(value);
            }
        }
        return out;
    }

    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        List<T> out = new ArrayList<>();
        ListIterator<T> each = list.listIterator();
        while (each.hasNext()) {
            T tmp = each.next();
            Iterator<T> itElements = elements.iterator();
            while (itElements.hasNext()) {
                if (tmp.equals(itElements.next())) {
                    out.add(tmp);
                    each.remove();
                }
            }
        }
        return out;
    }

}
