package ru.job4j.parking;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void park() {
        Parking parking = new ParkingImpl(0, 1);
        Car track = new Track(5);
        Car pasengercar = new PassengerCar();
        assertThat(true, is(parking.park(track)));
        assertThat(false, is(parking.park(pasengercar)));
    }

    @Test
    public void getPassengerCarFreePlace() {
        Parking parking = new ParkingImpl(6, 0);
        Car track = new Track(5);
        Car pasengercar = new PassengerCar();
        parking.park(track);
        assertThat(1, is(parking.getPassengerCarFreePlace()));
        assertThat(0, is(parking.getTrackFreePlace()));
        parking.park(pasengercar);
        assertThat(0, is(parking.getPassengerCarFreePlace()));
    }

    @Test
    public void getTrackFreePlace() {
        Parking parking = new ParkingImpl(9, 4);
        Car track = new Track(5);
        parking.park(track);
        assertThat(3, is(parking.getTrackFreePlace()));
    }
}