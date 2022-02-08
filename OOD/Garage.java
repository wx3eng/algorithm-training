package OOD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Garage {
    private final List<Level> levelList;

    public Garage(int numLevel, int numSpotEachLevel) {
        List<Level> list = new ArrayList<>(numLevel);
        for (int i = 0; i < numLevel; i++) {
            list.add(new Level(numSpotEachLevel));
        }
        levelList = Collections.unmodifiableList(list);
    }

    public boolean hasSpot(Vehicle v) {
        for (Level elem : levelList) {
            if (elem.hasSpot(v)) {
                return true;
            }
        }
        return false;
    }

    public boolean park(Vehicle v) {
        for (Level elem : levelList) {
            if (elem.park(v)) {
                return true;
            }
        }
        return false;
    }

    public boolean leave(Vehicle v) {
        for (Level elem : levelList) {
            if (elem.leave(v)) {
                return true;
            }
        }
        return false;
    }
}

class Level {
    private final List<Spot> spotList;

    Level(int num) {
        List<Spot> list = new ArrayList<>(num);
        int i = 0;
        for (; i < num / 2; i++) {
            list.add(new Spot(VehicleSize.compact));
        }
        for (; i < num; i++) {
            list.add(new Spot(VehicleSize.standard));
        }
        spotList = Collections.unmodifiableList(list);
    }

    boolean hasSpot(Vehicle v) {
        for (Spot elem : spotList) {
            if (elem.availableAndFit(v)) {
                return true;
            }
        }
        return false;
    }

    boolean park(Vehicle v) {
        for (Spot elem : spotList) {
            if (elem.availableAndFit(v)) {
                elem.park(v);
                return true;
            }
        }
        return false;
    }

    boolean leave(Vehicle v) {
        for (Spot elem : spotList) {
            if (elem.getVehicle() == v) {
                elem.leave();
                return true;
            }
        }
        return false;
    }
}

class Spot {
    private final VehicleSize size;
    private Vehicle v;

    Spot(VehicleSize size) {
        this.size = size;
    }

    boolean availableAndFit(Vehicle v) {
        return v == null && this.size.getSize() >= v.getSize().getSize() ? true : false;
    }

    void park(Vehicle v) {
        this.v = v;
    }

    void leave() {
        this.v = null;
    }

    Vehicle getVehicle() {
        return this.v;
    }
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