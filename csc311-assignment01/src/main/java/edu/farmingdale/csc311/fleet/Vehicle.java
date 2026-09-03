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
        this.color = validateText("color", color);
        if (year < 1900 || year > 2100) {
            throw new IllegalArgumentException("year: " + year);
        }
        this.year = year;
        if (wheels < 2 || wheels > 18) {
            throw new IllegalArgumentException("wheels: " + wheels);
        }
        this.wheels = wheels;
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
        if (!(fuelCapacity > 0.0)) {
            throw new IllegalArgumentException("fuelCapacity: " + fuelCapacity);
        }
        this.fuelCapacity = fuelCapacity;
    }

    private static String validateText(String field, String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + ": " + value);
        }
        return value.trim();
    }

    /* ------------------------------------------------------------------
     * TODO-03     commit: TODO-03: add Vehicle getters and setters
     *
     * Fill in the getters. The four setters repeat the rules from TODO-02,
     * so have the constructor call the setters instead of writing each
     * check twice.
     * ------------------------------------------------------------------ */

    public String getVin() {
        throw new UnsupportedOperationException("TODO-03");
    }

    public String getMake() {
        throw new UnsupportedOperationException("TODO-03");
    }

    public String getModel() {
        throw new UnsupportedOperationException("TODO-03");
    }

    public int getYear() {
        throw new UnsupportedOperationException("TODO-03");
    }

    public void setYear(int year) {
        throw new UnsupportedOperationException("TODO-03");
    }

    public String getColor() {
        throw new UnsupportedOperationException("TODO-03");
    }

    public void setColor(String color) {
        throw new UnsupportedOperationException("TODO-03");
    }

    public int getWheels() {
        throw new UnsupportedOperationException("TODO-03");
    }

    public void setWheels(int wheels) {
        throw new UnsupportedOperationException("TODO-03");
    }

    public double getEngineSize() {
        throw new UnsupportedOperationException("TODO-03");
    }

    public FuelType getFuelType() {
        throw new UnsupportedOperationException("TODO-03");
    }

    public double getFuelCapacity() {
        throw new UnsupportedOperationException("TODO-03");
    }

    public void setFuelCapacity(double fuelCapacity) {
        throw new UnsupportedOperationException("TODO-03");
    }

    /* ------------------------------------------------------------------
     * TODO-04     commit: TODO-04: implement honk methods from Honkable
     *
     * Vehicle says "implements Honkable" but supplies no horn code yet.
     *
     *      honk()          print hornSound() on one line
     *      honk(int)       print hornSound() that many times, one per line.
     *                      Throw IllegalArgumentException when times < 1.
     *
     * Do not implement hornSound() here. Car and Truck each answer it,
     * and honk() calls whichever one the object actually is.
     * ------------------------------------------------------------------ */

    @Override
    public void honk() {
        throw new UnsupportedOperationException("TODO-04");
    }

    @Override
    public void honk(int times) {
        throw new UnsupportedOperationException("TODO-04");
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
