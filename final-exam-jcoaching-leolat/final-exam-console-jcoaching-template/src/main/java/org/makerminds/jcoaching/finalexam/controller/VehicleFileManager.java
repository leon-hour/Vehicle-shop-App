package org.makerminds.jcoaching.finalexam.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.makerminds.jcoaching.finalexam.model.Vehicle;

/**
 * managing the file.
 * 
 * @author Leonora Latifaj
 *
 */
public class VehicleFileManager {
	
	private String filePath;
	
	public VehicleFileManager(String filePath) {
		this.filePath = filePath;
	}
	
	public List<String> importVehiclesFromFile() throws IOException, URISyntaxException{
			List<String> fileLines = Files.readAllLines(Paths.get(getClass().getResource(filePath).toURI()));
		return fileLines;
	}
	public void rewriteFile(List<Vehicle> vehicleList, String filePath) throws IOException, URISyntaxException {
		File file = new File(Paths.get(getClass().getResource(filePath).toURI()).toString());
		//File file = new File("C:\\Users\\Leonora Latifaj\\Downloads\\final-exam-console-jcoaching-template\\src\\main\\resources\\vehicle-list.txt");
		FileWriter fileWriter = new FileWriter(file,false);
		for(Vehicle vehicle: vehicleList) {
			StringBuffer stringBuffer = new StringBuffer();
			try {
			fileWriter.write(prepareTheVehicleForRewriting(stringBuffer,vehicle)+"\n");;
					}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		fileWriter.close();
	}
	
	private String prepareTheVehicleForRewriting(StringBuffer vehicleStringForRewrite, Vehicle vehicle) {
		vehicleStringForRewrite.append(vehicle.getId() + ",");
		vehicleStringForRewrite.append(vehicle.getManufacturer() + ",");
		vehicleStringForRewrite.append(vehicle.getModel() + ",");
		vehicleStringForRewrite.append(vehicle.getHorsePower() + ",");
		vehicleStringForRewrite.append(vehicle.getPrice() + ",");
		vehicleStringForRewrite.append(vehicle.getColor() + ",");
		vehicleStringForRewrite.append(vehicle.getClockedKilometers() + ",");
		vehicleStringForRewrite.append(vehicle.getYear() + ",");
		vehicleStringForRewrite.append(vehicle.getFuelType() + ",");
		vehicleStringForRewrite.append(vehicle.getTransmission());
		return vehicleStringForRewrite.toString();
	}
}