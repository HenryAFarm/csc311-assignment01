package edu.farmingdale.csc311.fleet;

/**
 * A named group of vehicles stored in a plain array.
 * No ArrayList, no HashMap. Arrays and loops only.
 *
 * @author Henry Arevalo
 */
public class Fleet {

    public static final int MAX_VEHICLES = 25;
    private final String name;
    private final Vehicle[] vehicles;
    private int count;



    public Fleet(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name: " + name);
        }
        this.name = name.trim();
        this.vehicles = new Vehicle[MAX_VEHICLES];
    }

    public String getName() {
        return name;
    }

    public boolean contains(Vehicle vehicle) {
        if (vehicle == null) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (vehicles[i].equals(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("vehicle: null");
        }
        if (count == MAX_VEHICLES || contains(vehicle)) {
            return false;
        }
        vehicles[count++] = vehicle;
        return true;
    }

    public boolean removeByVin(String vin) {
        if (vin == null || vin.trim().isEmpty()) {
            return false;
        }
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (vehicles[i].getVin().equalsIgnoreCase(vin.trim())) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            return false;
        }
        for (int i = index; i < count - 1; i++) {
            vehicles[i] = vehicles[i + 1];
        }
        vehicles[--count] = null;
        return true;
    }

    public Vehicle findByVin(String vin) {
        if (vin == null || vin.trim().isEmpty()) {
            return null;
        }
        for (int i = 0; i < count; i++) {
            if (vehicles[i].getVin().equalsIgnoreCase(vin.trim())) {
                return vehicles[i];
            }
        }
        return null;
    }

    public int size() {
        return count;
    }

    public Vehicle[] toArray() {
        Vehicle[] result = new Vehicle[count];
        for (int i = 0; i < count; i++) {
            result[i] = vehicles[i];
        }
        return result;
    }



    public Vehicle[] sortedByYear() {
        Vehicle[] result = toArray();
        for (int i = 0; i < result.length - 1; i++) {
            int earliest = i;
            for (int j = i + 1; j < result.length; j++) {
                Vehicle candidate = result[j];
                Vehicle current = result[earliest];
                if (candidate.getYear() < current.getYear()
                        || (candidate.getYear() == current.getYear()
                        && candidate.getMake().compareToIgnoreCase(current.getMake()) < 0)) {
                    earliest = j;
                }
            }
            Vehicle temporary = result[i];
            result[i] = result[earliest];
            result[earliest] = temporary;
        }
        return result;
    }

    public int countWithFuelType(FuelType fuel) {
        int matches = 0;
        for (int i = 0; i < count; i++) {
            if (vehicles[i].getFuelType() == fuel) {
                matches++;
            }
        }
        return matches;
    }

    public double averageEngineSize() {
        double total = 0.0;
        int engineVehicles = 0;
        for (int i = 0; i < count; i++) {
            if (vehicles[i].getFuelType().hasEngine()) {
                total += vehicles[i].getEngineSize();
                engineVehicles++;
            }
        }
        return engineVehicles == 0 ? 0.0 : total / engineVehicles;
    }

    public Vehicle longestRange() {
        Vehicle longest = null;
        for (int i = 0; i < count; i++) {
            if (longest == null || vehicles[i].rangeInMiles() > longest.rangeInMiles()) {
                longest = vehicles[i];
            }
        }
        return longest;
    }
}
