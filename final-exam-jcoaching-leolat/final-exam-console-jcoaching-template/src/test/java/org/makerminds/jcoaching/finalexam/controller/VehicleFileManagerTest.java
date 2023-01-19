package org.makerminds.jcoaching.finalexam.controller;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class VehicleFileManagerTest {

	VehicleFileManager vehicleFileManager = new VehicleFileManager("/vehicle-list.txt");
	
	@Test
	public void importVehiclesFromFileTest() throws IOException, URISyntaxException{
		int expectedVehicleItemsListSize = 10;
		List<String> vehicleItemsList = vehicleFileManager.importVehiclesFromFile();
		int actualValue = vehicleItemsList.size();
		Assertions.assertEquals(expectedVehicleItemsListSize,actualValue);
	}
}
