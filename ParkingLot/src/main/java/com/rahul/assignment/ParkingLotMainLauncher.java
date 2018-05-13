package com.rahul.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import com.rahul.assignment.exception.NoParkingLotAvailableException;
import com.rahul.assignment.exception.NoServiceFoundException;
import com.rahul.assignment.exception.ParkingSlotNotOccupiedException;
import com.rahul.assignment.model.Car;
import com.rahul.assignment.service.ParkingFactory;
import com.rahul.assignment.service.ParkingService;

public class ParkingLotMainLauncher {

	static ParkingService parkingService;


	public static void main(String[] args) {
		ParkingLotMainLauncher launcher = new ParkingLotMainLauncher();
		ParkingFactory factory = ParkingFactory.getInstance();

		BufferedReader br = null;
		FileReader fr = null;

		try {
			parkingService = factory.getService(ParkingService.class);
			if(args!= null && args.length!=0){
				fr = new FileReader(args[0]);
				br = new BufferedReader(fr);
				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					String arr[]=sCurrentLine.split("\\s+");
					launcher.performOperation(arr);
				}
			}else{
				Scanner sc=new Scanner(System.in);
				while(true){
					if(sc.hasNextLine()){
						String currentLine = sc.nextLine();
						String arr[]=currentLine.split("\\s+");
						launcher.performOperation(arr);
					}
				}
			}

		} catch (IOException e) {
			System.out.println("File Not Found");
		} catch (NoServiceFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No Service Found");
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
	}

	public void performOperation(String arr[]){
		String color;
		String registrationNo;
		switch(arr[0]){
		case "create_parking_lot" : int noOfSlots = Integer.parseInt(arr[1]);
		createParkingSlots(noOfSlots);
		break;

		case "park"	: registrationNo = arr[1];
		color = arr[2];
		parkCar(registrationNo, color);
		break;

		case "leave" : int slotNo = Integer.parseInt(arr[1]);
		leave(slotNo);
		break;

		case "status" : 
		getAllParkedCarsDetails();	
		break;

		case "registration_numbers_for_cars_with_colour" : color = arr[1];
		printRegistrationNumbersForCarsWithColour(color);
		break;

		case "slot_numbers_for_cars_with_colour" : color = arr[1];
		printSlotNumbersForCarsWithColour(color);
		break;

		case "slot_number_for_registration_number" : registrationNo = arr[1];
		printSlotNumberForRegistrationNumber(registrationNo);
		break;

		default : System.out.println("Invalid command");
		break;
		}
	}
	
	
	public void createParkingSlots(int noOfSlots){
		parkingService.createParkingSlots(noOfSlots);
		System.out.println("Created a parking lot with "+noOfSlots+" slots");
	}

	public void parkCar(String registrationNo, String color){
		Car car = new Car(registrationNo, color);
		try {
			int slotNo = parkingService.parkCar(car);
			System.out.println("Allocated slot number: "+slotNo);
		} catch (NoParkingLotAvailableException e) {
			System.out.println(e.getMessage());
		}

	}

	public void leave(int slotNo){
		try {
			boolean status = parkingService.leaveSlot(slotNo);
			if(status){
				System.out.println("Slot number "+slotNo+" is free");
			}
		} catch (ParkingSlotNotOccupiedException e) {
			System.out.println(e.getMessage());
		}

	}

	public void getAllParkedCarsDetails(){
		TreeMap<Integer,Car> carMap = parkingService.getAllParkedCarsDetails();
		if(carMap.size()!=0)
			System.out.println("Slot No. Registration No Colour");
		for(int slotNo: carMap.keySet()){
			Car car=carMap.get(slotNo);
			System.out.printf("%-8d %-15s %s",slotNo,car.getRegistrationNumber(),car.getColor());
			System.out.println();
		}
	}

	public void printRegistrationNumbersForCarsWithColour(String color){
		List<String> registrationNumbers=parkingService.getRegistrationNumbersWithColor(color);
		System.out.println(registrationNumbers);
	}

	public void printSlotNumbersForCarsWithColour(String color){
		List<Integer> slots= parkingService.getSlotNumbersForColor(color);
		System.out.println(slots);
	}

	public void printSlotNumberForRegistrationNumber(String registrationNumber){
		int slotNo = parkingService.getSlotNumber(registrationNumber);
		if(slotNo!= -1){
			System.out.println(slotNo);
		}else{
			System.out.println("Not Found");
		}
	}

}
