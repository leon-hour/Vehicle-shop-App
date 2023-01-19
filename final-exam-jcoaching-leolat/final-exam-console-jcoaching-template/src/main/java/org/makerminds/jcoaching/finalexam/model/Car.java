package org.makerminds.jcoaching.finalexam.model;
public class Car extends Vehicle{
private int numberOfDoors;
	public Car(int id, Manufacturer manufacturer, String model, int horsePower, double price, Color color,
			int clocked_Kilometers, int year, FuelType fuelType, Transmission transmission, int numberOfDoors) {
		super(id, manufacturer, model, horsePower, price, color, clocked_Kilometers, year, fuelType, transmission);
	}
	public int getNumberOfDoors() {
		return numberOfDoors;
	}
	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}
}
