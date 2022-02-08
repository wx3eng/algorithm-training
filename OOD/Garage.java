package OOD;

public class Garage {

}

class Level {

}

class Spot {

}

enum VehicleSize {
    compact(16),
    standard(18);

    private final int size;

    VehicleSize(int size) {
        this.size = size;
    }

    int getSize() {
        return size;
    }
}

abstract class Vehicle {
    abstract VehicleSize getSize();
}

class CompactCar extends Vehicle {
    @Override
    VehicleSize getSize() {
        return VehicleSize.compact;
    }
}

class StandardCar extends Vehicle {
    @Override
    VehicleSize getSize() {
        return VehicleSize.standard;
    }
}