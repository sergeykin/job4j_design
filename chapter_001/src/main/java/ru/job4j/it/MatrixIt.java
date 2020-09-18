package ru.job4j.it;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (column < data.length) {
            if (row < data[column].length) {
                return true;
            } else {
                column++;
                return hasNext();
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int tmp = data[column][row];
        row++;
        if (row == data[column].length) {
            row = 0;
            column++;
        }
        return tmp;
    }
}
