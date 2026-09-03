package edu.farmingdale.csc311.fleet;

import java.util.Locale;

/**
 * Base class for everything the motor pool owns. Abstract on purpose:
 * the fleet holds cars and trucks, never a plain "vehicle".
 *
 * @author Henry Arevalo
 */
public abstract class Vehicle implements Honkable {
    private final String vin;
    private final String make;
    private final String model;
    private int year;
    private String color;
    private int wheels;
    private final double engineSize;
    private final FuelType fuelType;
    private double fuelCapacity;

    protected Vehicle(String vin, String make, String model, int year, String color,
                      int wheels, double engineSize, FuelType fuelType, double fuelCapacity) {
        if (vin == null || vin.trim().length() != 17) {
            throw new IllegalArgumentException("vin: " + vin);
        }
        this.vin = vin.trim().toUpperCase(Locale.ROOT);
        this.make = validateText("make", make);
        this.model = validateText("model", model);
        if (fuelType == null) {
            throw new IllegalArgumentException("fuelType: null");
        }
        this.fuelType = fuelType;
        if (fuelType.hasEngine()) {
            if (!(engineSize > 0.0) || engineSize > 8.5) {
                throw new IllegalArgumentException("engineSize: " + engineSize);
            }
        } else if (engineSize != 0.0) {
            throw new IllegalArgumentException("engineSize: " + engineSize);
        }
        this.engineSize = engineSize;
        setColor(color);
        setYear(year);
        setWheels(wheels);
        setFuelCapacity(fuelCapacity);
    }

    private static String validateText(String field, String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + ": " + value);
        }
        return value.trim();
    }



    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1900 || year > 2100) {
            throw new IllegalArgumentException("year: " + year);
        }
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = validateText("color", color);
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        if (wheels < 2 || wheels > 18) {
            throw new IllegalArgumentException("wheels: " + wheels);
        }
        this.wheels = wheels;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        if (!(fuelCapacity > 0.0)) {
            throw new IllegalArgumentException("fuelCapacity: " + fuelCapacity);
        }
        this.fuelCapacity = fuelCapacity;
    }



    @Override
    public void honk() {
        System.out.println(hornSound());
    }

    @Override
    public void honk(int times) {
        if (times < 1) {
            throw new IllegalArgumentException("times: " + times);
        }
        for (int i = 0; i < times; i++) {
            System.out.println(hornSound());
        }
    }

    /** Subclasses answer these two. Do not write bodies here. */
    public abstract String category();

    public abstract double rangeInMiles();



    @Override
    public String toString() {
        String engine = fuelType.hasEngine()
                ? String.format(Locale.ROOT, "%.1fL", engineSize)
                : "n/a";
        return String.format(Locale.ROOT,
                "%d %s %s [VIN=%s] color=%s, wheels=%d, engine=%s, fuel=%s, capacity=%.1f %s",
                year, make, model, vin, color, wheels, engine,
                fuelType.getLabel(), fuelCapacity, fuelType.getUnit());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vehicle)) {
            return false;
        }
        Vehicle vehicle = (Vehicle) other;
        return vin.equals(vehicle.vin);
    }

    @Override
    public int hashCode() {
        return vin.hashCode();
    }
}
