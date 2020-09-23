package ru.job4j.it;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int column = 0;
    private int row = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length) {
            if (column < data[row].length) {
                return true;
            } else {
                row++;
            }
        }
        /*
        if (column < data.length) {
            if (row < data[column].length) {
                return true;
            } else {
                column++;
                return hasNext();
            }
        }
         */
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int tmp = data[row][column];
        column++;
        if (column == data[row].length) {
            column = 0;
            row++;
        }
        return tmp;
    }
}
