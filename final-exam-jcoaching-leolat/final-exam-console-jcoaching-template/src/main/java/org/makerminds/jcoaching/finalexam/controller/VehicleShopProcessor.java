package org.makerminds.jcoaching.finalexam.controller;

import java.util.ArrayList;
import java.util.List;

import org.makerminds.jcoaching.finalexam.model.Vehicle;

/**
 * responsible for processing business processes.
 * 
 * @author Leonora Latifaj
 *
 */
public class VehicleShopProcessor {

	/**
	 * responsible to sell a specified vehicle by id
	 * 
	 * @param vehiclesList
	 * @param vehicleChosenId
	 */
	public boolean sellVehicle(List<Vehicle> vehiclesList, int vehicleChosenId) {
		// TODO selling a vehicle means to remove it from the available vehicle list
		// Hint: use while loop to safely remove an object from a list
		int i = vehiclesList.size();
		while (i > 0) {
			if (vehiclesList.get(i - 1).getId() == vehicleChosenId) {
				vehiclesList.remove(i - 1);
				System.out.println("Remoded element at "+vehicleChosenId);
				return true;
			}
			i--;
		}
		return false;
	}
	
	public List<String> filterDataBasedInPrice(double price, List<Vehicle> vehicleList) {
		boolean hasDataInThisPriceRange = false;
		List<String> vehicleInfoList = new ArrayList<String>();
		for (Vehicle vehicle : vehicleList) {
			if (vehicle.getPrice() <= price) {
				String vehicleInfo = vehicle.getId() + "," + vehicle.getManufacturer() + "," + vehicle.getModel() + ","
						+ vehicle.getHorsePower() + "," + vehicle.getPrice() + "," + vehicle.getColor() + ","
						+ vehicle.getClockedKilometers() + "," + vehicle.getYear() + "," + vehicle.getFuelType()
						+ "," + vehicle.getTransmission();
				 vehicleInfoList.add(vehicleInfo);
				hasDataInThisPriceRange = true;
			}
		}
		if(!hasDataInThisPriceRange ) {
			System.out.println("No vehicle available in (0.0-" + price+") price range");
		}
		return vehicleInfoList;
	}
	
	public List<String> filterDataBasedInManifacturer(String manufacturer, List<Vehicle> vehicleList) {
		boolean hasVehicleOfThisMake = false;
		List<String> vehicleInfoList = new ArrayList<String>();
		VehicleTransformer vehicleTransformer = new VehicleTransformer();
		for (Vehicle vehicle : vehicleList) {
			if (vehicle.getManufacturer().equals(vehicleTransformer.getManufacturer(manufacturer))) {
				hasVehicleOfThisMake = true;
				 String vehicleInfo = vehicle.getId() + "," + vehicle.getManufacturer() + "," + vehicle.getModel() + ","
						+ vehicle.getHorsePower() + "," + vehicle.getPrice() + "," + vehicle.getColor() + ","
						+ vehicle.getClockedKilometers() + "," + vehicle.getYear() + "," + vehicle.getFuelType()
						+ "," + vehicle.getTransmission();
				 vehicleInfoList.add(vehicleInfo);
			}
		}
		if(!hasVehicleOfThisMake) {
			System.out.println("There's no available car of this make.");
		}
		return vehicleInfoList;
	}
}