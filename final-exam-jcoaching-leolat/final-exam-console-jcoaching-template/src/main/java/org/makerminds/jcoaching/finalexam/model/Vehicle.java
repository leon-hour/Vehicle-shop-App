package org.makerminds.jcoaching.finalexam.model;

/**
 * class for vehicle objects.
 * 
 * @author Leonora
 *
 */
public class Vehicle {

	// TODO Vehicle implementation
	// TODO add attributes and Getters / Setters
	private int id;
	private Color color;
	private FuelType fuelType;
	private Manufacturer manufacturer;
	private Transmission transmission;
	private String model;
	private int horsePower;
	private double price;
	private int year;
	private int clocked_Kilometers;

	public Vehicle(int id, Manufacturer manufacturer, String model, int horsePower, double price, Color color,
			int clocked_Kilometers, int year, FuelType fuelType, Transmission transmission) {
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.horsePower = horsePower;
		this.price = price;
		this.color = color;
		this.clocked_Kilometers = clocked_Kilometers;
		this.year = year;
		this.fuelType = fuelType;
		this.transmission = transmission;
		
	
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getClockedKilometers() {
		return clocked_Kilometers;
	}

	public void setEngine_Displacement(int clocked_Kilometers) {
		this.clocked_Kilometers = clocked_Kilometers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}