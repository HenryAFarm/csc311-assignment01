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

    /* ------------------------------------------------------------------
     * TODO-09     commit: TODO-09: implement Fleet reports
     *
     * None of these may reorder or change the internal array. Start from
     * toArray() when you need a different order.
     *
     *    sortedByYear()
     *        a new array ordered by year, oldest first. When two years
     *        match, order by make A to Z ignoring case
     *        (String.compareToIgnoreCase). Write the sort yourself:
     *        selection sort or insertion sort, your choice. No Arrays.sort,
     *        no Comparator.
     *
     *    countWithFuelType(FuelType fuel)
     *        how many vehicles use that fuel.
     *
     *    averageEngineSize()
     *        average engine size over the vehicles whose fuel type has an
     *        engine. Electrics are left out, otherwise their 0.0 drags the
     *        number down and it means nothing. Return 0.0 when the count is
     *        zero, and watch the division.
     *
     *    longestRange()
     *        the vehicle with the largest rangeInMiles(), or null when the
     *        fleet is empty. On a tie keep the one added first. Note that
     *        this compares cars against trucks without a single if about
     *        the type: rangeInMiles() already knows which formula to run.
     * ------------------------------------------------------------------ */

    public Vehicle[] sortedByYear() {
        throw new UnsupportedOperationException("TODO-09");
    }

    public int countWithFuelType(FuelType fuel) {
        throw new UnsupportedOperationException("TODO-09");
    }

    public double averageEngineSize() {
        throw new UnsupportedOperationException("TODO-09");
    }

    public Vehicle longestRange() {
        throw new UnsupportedOperationException("TODO-09");
    }
}
