package org.makerminds.jcoaching.finalexam.console;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import org.makerminds.jcoaching.finalexam.controller.VehicleFileManager;
import org.makerminds.jcoaching.finalexam.controller.VehicleShopPrinter;
import org.makerminds.jcoaching.finalexam.controller.VehicleShopProcessor;
import org.makerminds.jcoaching.finalexam.controller.VehicleTransformer;
import org.makerminds.jcoaching.finalexam.model.Vehicle;

/**
 * providing main method to start the application.
 * 
 * @author Leonora Latifaj
 *
 */
public class VehicleShop {

	private static VehicleTransformer vehicleTransformer = new VehicleTransformer();
	private static final String VEHICLE_LIST_PATH = "/vehicle-list.txt";
	private static int avaliableVehicles = 0;

	public static void main(String[] args) throws IOException, URISyntaxException {

		Scanner scanner = new Scanner(System.in);
		// File reading
		do {
			VehicleFileManager vehicleFileManager = new VehicleFileManager(VEHICLE_LIST_PATH);
			List<String> vehicleDataAsStringList = vehicleFileManager.importVehiclesFromFile();
			// Getting the vehicle number available
			avaliableVehicles = vehicleDataAsStringList.size();
			// Transformation into Vehicle Java Objects
			List<Vehicle> vehicleList = vehicleTransformer.transformDataArrayToVehicleObjects(vehicleDataAsStringList);

			// Checking if there are available vehicles 
			VehicleShopPrinter vehicleShopPrinter = new VehicleShopPrinter();
			if(avaliableVehicles == 0) {
				vehicleShopPrinter.printNoMoreVehicleAvailableMessage();
				System.exit(0);
			}
			
			// Printing available vehicles
			vehicleShopPrinter.printAvailableVehicles(vehicleList);
			
			vehicleShopPrinter.printInputInfoRequest();
			// User interaction - selecting an option
			int option = scanner.nextInt();
			// Checking the input option from user
			switch (option) {
			case 0:
				break;
			case 1:
				vehicleShopPrinter.printVehiclePriceRequest();
				// User interaction - selecting a price to filter the data based on
				double price = scanner.nextDouble();
				//filtering data based on the input the user has entered
				vehicleShopPrinter.printFilteredDataBasedInPrice(price, vehicleList);
				break;
			case 2:
				vehicleShopPrinter.printManufacturingFilterRequest();
				// User interaction - selecting a manufacturer to filter the data based on
				String manufacturer = scanner.next();
				//filtering data based on the input the user has entered
				vehicleShopPrinter.printFilteredDataBasedInManifacturer(manufacturer, vehicleList);
				break;
			case 3:
				System.exit(0);
			default:
				throw new IllegalArgumentException();
			}
			// User interaction - selecting a vehicle to sell
			vehicleShopPrinter.printVehicleIdToSellRequest();
			int vehicleChosenId = scanner.nextInt();

			// Selling a vehicle
			VehicleShopProcessor vehicleShopProcessor = new VehicleShopProcessor();
			boolean sold = vehicleShopProcessor.sellVehicle(vehicleList, vehicleChosenId);
			if (sold) {
				vehicleShopPrinter.printVehicleSoldMessage(vehicleChosenId);
			} else {
				vehicleShopPrinter.printNoVehicleWithThisIdMessage();
			}

			vehicleFileManager.rewriteFile(vehicleList,VEHICLE_LIST_PATH);
		} while (avaliableVehicles != 0);
		
		scanner.close();
	}
}