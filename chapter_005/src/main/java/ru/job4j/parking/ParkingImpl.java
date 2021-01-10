package ru.job4j.parking;

public class ParkingImpl implements Parking {
    private int sizeCarFreePlace;
    private int sizeTrackFreePlace;

    public ParkingImpl(int sizeCarFreePlace, int sizeTrackFreePlace) {
        this.sizeCarFreePlace = sizeCarFreePlace;
        this.sizeTrackFreePlace = sizeTrackFreePlace;
    }

    @Override
    public boolean park(Car car) {
        int sizeCar = car.getSize();
        if (sizeCar == 1 && sizeCarFreePlace > 0) {
            sizeCarFreePlace--;
            return true;
        }
        if (sizeCar > 1) {
            if (sizeTrackFreePlace > 0) {
                sizeTrackFreePlace--;
                return true;
            }
            if (sizeCarFreePlace >= sizeCar) {
                sizeCarFreePlace = sizeCarFreePlace - sizeCar;
                return true;
            }
        }
        return false;
    }

    @Override
    public int getPassengerCarFreePlace() {
        return sizeCarFreePlace;
    }

    @Override
    public int getTrackFreePlace() {
        return sizeTrackFreePlace;
    }
}
