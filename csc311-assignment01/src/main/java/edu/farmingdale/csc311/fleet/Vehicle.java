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

    /* ------------------------------------------------------------------
     * TODO-05     commit: TODO-05: add toString, equals and hashCode
     *
     * toString() returns exactly this shape, built with String.format:
     *
     *   2023 Honda Accord [VIN=1HGCM82633A004352] color=Blue, wheels=4,
     *   engine=2.0L, fuel=Gasoline, capacity=15.8 gallons
     *
     * (one line, no period at the end). When fuelType.hasEngine() is false
     * the engine part reads engine=n/a instead of a number. Use getLabel()
     * for the fuel and getUnit() after the capacity.
     *
     * Two vehicles are equal when their VINs match. Follow the usual steps:
     * same object, then instanceof, then compare the VIN strings.
     * Base hashCode on the VIN so it agrees with equals.
     * ------------------------------------------------------------------ */

    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO-05");
    }

    @Override
    public boolean equals(Object other) {
        throw new UnsupportedOperationException("TODO-05");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("TODO-05");
    }
}
