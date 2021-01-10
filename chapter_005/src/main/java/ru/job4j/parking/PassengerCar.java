package ru.job4j.parking;

public class PassengerCar implements Car {
    private final int size = 1;

    public PassengerCar() {
    }

    @Override
    public int getSize() {
        return size;
    }
}
