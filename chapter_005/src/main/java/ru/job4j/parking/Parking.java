package ru.job4j.parking;

public interface Parking {
    public boolean park(Car car);
    public int getPassengerCarFreePlace();
    public int getTrackFreePlace();
}
