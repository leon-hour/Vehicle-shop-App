package org.makerminds.jcoaching.finalexam.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

import org.makerminds.jcoaching.finalexam.controller.VehicleFileManager;
import org.makerminds.jcoaching.finalexam.controller.VehicleShopPrinter;
import org.makerminds.jcoaching.finalexam.controller.VehicleShopProcessor;
import org.makerminds.jcoaching.finalexam.controller.VehicleTransformer;
import org.makerminds.jcoaching.finalexam.model.Vehicle;

public class VehicleShopGUI {
	private  String FILE_NAME = "/vehicle-list.txt";
	private DefaultTableModel vehicleItemTableModel = new DefaultTableModel();
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VehicleShopGUI window = new VehicleShopGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VehicleShopGUI() throws IOException, URISyntaxException {
		initialize();
	}
	
	private void initialize() throws IOException, URISyntaxException {
		frame = new JFrame();
		frame.setBounds(0, 0, 1000, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel applicationNameLabel = new JLabel("Vehicle Shop application");
		applicationNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(applicationNameLabel, BorderLayout.NORTH);
		prepareVehicleDataTable();
		JTable vehicleDataTable = new JTable(vehicleItemTableModel);
		JScrollPane scrollPane = new JScrollPane(vehicleDataTable);
		scrollPane.setPreferredSize(new Dimension(950, 225));
		JPanel panel = new JPanel();
		scrollPane.setBorder(BorderFactory.createTitledBorder("Available Vehicles"));
		panel.add(scrollPane);
		frame.getContentPane().add(panel);
		JButton sellVehicleButton = new JButton("Sell Vehicle");
		sellVehicleButton.setPreferredSize(new Dimension(200,40));
		JButton filterDataBasedInPrice = new JButton("Filter vehicle based in price");
		filterDataBasedInPrice.setPreferredSize(new Dimension(200,40));
		JButton filterDataBasedInManufacturer = new JButton("Filter vehicles based in manufacturer");
		filterDataBasedInManufacturer.setPreferredSize(new Dimension(200,40));
		JButton allAvailableVehicles = new JButton("Show all available aehicles ");
		allAvailableVehicles.setPreferredSize(new Dimension(200,40));
		filterDataBasedInPrice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = vehicleDataTable.getSelectedRow();
	            double selectedPrice = Double.parseDouble((String) vehicleDataTable.getValueAt(selectedRow,4 ));
				VehicleTransformer vehicleTransformer = new VehicleTransformer();		
				VehicleShopProcessor vehicleShopProcessor = new VehicleShopProcessor();
				VehicleFileManager vehicleFileMenager = new VehicleFileManager(FILE_NAME);
				try {
					List<Vehicle> vehicles = vehicleTransformer.transformDataArrayToVehicleObjects(vehicleShopProcessor.filterDataBasedInPrice(selectedPrice,vehicleTransformer.transformDataArrayToVehicleObjects(getVehicle())));
					vehicleShopProcessor.filterDataBasedInPrice(selectedPrice, vehicles);
					FILE_NAME = "/filtred-vehicle-list.txt";
					vehicleFileMenager.rewriteFile(vehicles, FILE_NAME);
					prepareVehicleDataTable();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		allAvailableVehicles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FILE_NAME = "/vehicle-list.txt";
					prepareVehicleDataTable();
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}	
			}
		});
		filterDataBasedInManufacturer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = vehicleDataTable.getSelectedRow();
	            String seectedManufacturer = (String) vehicleDataTable.getValueAt(selectedRow,1 );
				VehicleTransformer vehicleTransformer = new VehicleTransformer();
				VehicleShopProcessor vehicleShopProcessor = new VehicleShopProcessor();
				VehicleFileManager vehicleFileMenager = new VehicleFileManager(FILE_NAME);
				try {
					List<Vehicle> vehicles = vehicleTransformer.transformDataArrayToVehicleObjects(vehicleShopProcessor.filterDataBasedInManifacturer(seectedManufacturer,vehicleTransformer.transformDataArrayToVehicleObjects(getVehicle())));
					vehicleFileMenager.rewriteFile(vehicles, "/filtred-vehicle-list.txt");
					FILE_NAME = "/filtred-vehicle-list.txt";
					prepareVehicleDataTable();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		sellVehicleButton.addActionListener(new ActionListener() {

			@Override
			   public void actionPerformed(ActionEvent e) {
	            int selectedRow = vehicleDataTable.getSelectedRow();
	            int seectedId = Integer.parseInt((String) vehicleDataTable.getValueAt(selectedRow, 0));
				VehicleShopProcessor vehicleShopProcessor = new VehicleShopProcessor();
				FILE_NAME = "/vehicle-list.txt";
				VehicleFileManager vehicleFileMenager = new VehicleFileManager(FILE_NAME);
				VehicleShopPrinter vehicleShopPrinter = new VehicleShopPrinter();
				VehicleTransformer vehicleTransformer = new VehicleTransformer();
				try {
					List<Vehicle> vehicles = vehicleTransformer.transformDataArrayToVehicleObjects(getVehicle());
					vehicleShopProcessor.sellVehicle(vehicles,seectedId);
					vehicleShopPrinter.printVehicleSoldMessage(seectedId);
					vehicleFileMenager.rewriteFile(vehicles,FILE_NAME);
					prepareVehicleDataTable();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
	        }
		});

		panel.add(sellVehicleButton);
		panel.add(filterDataBasedInManufacturer);
		panel.add(filterDataBasedInPrice);
		panel.add(allAvailableVehicles);
		frame.setResizable(false);
	}

	private List<String> getVehicle() throws IOException, URISyntaxException {
		List<String> vehicleList;
			VehicleFileManager vehicleFileManager = new VehicleFileManager(FILE_NAME);
			vehicleList = vehicleFileManager.importVehiclesFromFile();
		return vehicleList;
	}

	private String[][] prepareVehicleDataTable() throws IOException, URISyntaxException {
		String[] header = { "ID", "Manufacturer", "Model","Horse Power","Price","Color","Clocked_km","Year","Fuel Type","Transmission" };
		String[][] VehicleItemsMulidimensionalArray = createVehicleArray();
		vehicleItemTableModel.setDataVector(VehicleItemsMulidimensionalArray, header);
		return VehicleItemsMulidimensionalArray;
	}

	private String[][] createVehicleArray() throws IOException, URISyntaxException {
		List<String> Vehicle = getVehicle();
		String[][] VehicleItemsMulidimensionalArray = new String[Vehicle.size()][10];
		int i = 0;
		for (String vehicle : Vehicle) {
			String[] vehicleInfo = vehicle.split(",");
			VehicleItemsMulidimensionalArray[i][0] = vehicleInfo[0];
			VehicleItemsMulidimensionalArray[i][1] = vehicleInfo[1];
			VehicleItemsMulidimensionalArray[i][2] = vehicleInfo[2];
			VehicleItemsMulidimensionalArray[i][3] = vehicleInfo[3];
			VehicleItemsMulidimensionalArray[i][4] = vehicleInfo[4];
			VehicleItemsMulidimensionalArray[i][5] = vehicleInfo[5];
			VehicleItemsMulidimensionalArray[i][6] = vehicleInfo[6];
			VehicleItemsMulidimensionalArray[i][7] = vehicleInfo[7];
			VehicleItemsMulidimensionalArray[i][8] = vehicleInfo[8];
			VehicleItemsMulidimensionalArray[i][9] = vehicleInfo[9];
			i++;
		}
		return VehicleItemsMulidimensionalArray;
	}
}