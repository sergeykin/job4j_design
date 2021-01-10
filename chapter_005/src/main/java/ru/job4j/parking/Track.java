package ru.job4j.parking;

public class Track implements Car {
    private int size;

    public Track(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
