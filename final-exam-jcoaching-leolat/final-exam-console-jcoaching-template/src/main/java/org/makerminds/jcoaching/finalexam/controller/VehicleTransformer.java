package org.makerminds.jcoaching.finalexam.controller;

import java.util.ArrayList;
import java.util.List;

import org.makerminds.jcoaching.finalexam.model.Color;
import org.makerminds.jcoaching.finalexam.model.FuelType;
import org.makerminds.jcoaching.finalexam.model.Manufacturer;
import org.makerminds.jcoaching.finalexam.model.Transmission;
import org.makerminds.jcoaching.finalexam.model.Vehicle;

/**
 * responsible for transforming vehicle data into {@link Vehicle} objects.
 * 
 * @author Leonora Latifaj
 *
 */
public class VehicleTransformer {

	/**
	 * transforms a data array into a {@link Vehicle} list
	 * 
	 * @param vehicle data array
	 * @return list of {@link Vehicle} objects
	 */
	public List<Vehicle> transformDataArrayToVehicleObjects(List<String> vehicleDataArray) {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		for (String vehicles : vehicleDataArray) {
			vehicleList.add(transformToVehicleObject(vehicles));
		}
		return vehicleList;
	}

	/**
	 * transforms a vehicle data record as String into a {@link Vehicle} object
	 * 
	 * @param vehicle data record as String
	 * @return {@link Vehicle} object
	 */
	private Vehicle transformToVehicleObject(String vehicleAsString) {
		String[] vehicleInfo = vehicleAsString.split(",");
		int id = Integer.parseInt(vehicleInfo[0]);
		Manufacturer manufacturer = getManufacturer(vehicleInfo[1]);
		String model = vehicleInfo[2];
		int horsePower = Integer.parseInt(vehicleInfo[3]);
		double price = Double.parseDouble(vehicleInfo[4]);
		Color color = getColor(vehicleInfo[5]);
		int clocked_Kilometers = Integer.parseInt(vehicleInfo[6]);
		int year = Integer.parseInt(vehicleInfo[7]);
		FuelType fuelType = getFuelType(vehicleInfo[8]);
		Transmission transmission = getTranssmision(vehicleInfo[9]);
		return new Vehicle(id, manufacturer, model, horsePower, price, color, clocked_Kilometers, year, fuelType,
				transmission);
	}

	private Transmission getTranssmision(String transmission) {
		if (transmission.equals("AUTOMATIC")) {
			return Transmission.AUTOMATIC;
		} else if (transmission.equals("MANUAL")) {
			return Transmission.MANUAL;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private Color getColor(String color) {
		switch (color) {
		case "BLACK":
			return Color.BLACK;
		case "GREY":
			return Color.GREY;
		case "WHITE":
			return Color.WHITE;
		case "BLUE":
			return Color.BLUE;
		case "RED":
			return Color.RED;
		case "BROWN":
			return Color.BROWN;
		case "YELLOW":
			return Color.YELLOW;
		default:
			throw new IllegalArgumentException();
		}
	}

	private FuelType getFuelType(String fuelType) {
		// DISEL_FUEL;
		if (fuelType.equals("GASOLINE")) {
			return FuelType.GASOLINE;
		} else if (fuelType.equals("DIESEL_FUEL")) {
			return FuelType.DIESEL_FUEL;
		} else {
			throw new IllegalArgumentException("Illegal fuel type!!");
		}
	}

	 Manufacturer getManufacturer(String manufacturer) {
		// AUDI, BMW, VW, HONDA,SKODA;
		switch (manufacturer) {
		case "AUDI":
			return Manufacturer.AUDI;

		case "BMW":
			return Manufacturer.BMW;

		case "VW":
			return Manufacturer.VW;

		case "HONDA":
			return Manufacturer.HONDA;

		case "SKODA":
			return Manufacturer.SKODA;
		default:
			throw new IllegalArgumentException();

		}
	}
}
