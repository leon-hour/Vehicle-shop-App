package org.makerminds.jcoaching.finalexam.model;
public class Motorcycle extends Vehicle{
private int numberOfWheel;

	public Motorcycle(int id, Manufacturer manufacturer, String model, int horsePower, double price, Color color,
			int clocked_Kilometers, int year, FuelType fuelType, Transmission transmission, int numberOfWheel ) {
		super(id, manufacturer, model, horsePower, price, color, clocked_Kilometers, year, fuelType, transmission);
	}
	public int getNumberOfWheel() {
		return numberOfWheel;
	}
	public void setNumberOfWheel(int numberOfWheel) {
		this.numberOfWheel = numberOfWheel;
	}
}
