package com.rahul.assignment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.rahul.assignment.exception.NoParkingLotAvailableException;
import com.rahul.assignment.exception.ParkingSlotNotOccupiedException;
import com.rahul.assignment.model.Car;
import com.rahul.assignment.model.ParkingSlot;
import com.rahul.assignment.service.ParkingService;

public class ParkingServiceImpl implements ParkingService{

	List<Car> cars=new ArrayList<>();
	ParkingSlot parkingSlot[];
	
	@Override
	public int parkCar(Car car) throws NoParkingLotAvailableException{
		int noOfSlots = parkingSlot.length;
		for(int i=0;i<noOfSlots;i++){
			if(parkingSlot[i].isAvailable()){
				ParkingSlot slot = parkingSlot[i];
				slot.setAvailable(false);
				slot.setCar(car);
				cars.add(car);
				return slot.getParkingSlotNumber();
			}
		}
		throw new NoParkingLotAvailableException("Sorry, parking lot is full");
	}

	@Override
	public boolean leaveSlot(int parkingSlotNumber) throws ParkingSlotNotOccupiedException{
		if(parkingSlot[parkingSlotNumber-1].isAvailable()){
			throw new ParkingSlotNotOccupiedException("Parking Slot "+(parkingSlotNumber)+" is already Empty");
		}else{
			parkingSlot[parkingSlotNumber-1].setAvailable(true);
			cars.remove(parkingSlot[parkingSlotNumber-1].getCar());
			parkingSlot[parkingSlotNumber-1].setCar(null);
			return true;
		}
		
	}

	@Override
	public TreeMap<Integer,Car> getAllParkedCarsDetails() {
		TreeMap<Integer,Car> carsMap = new TreeMap<>();
		for(ParkingSlot slot : parkingSlot){
			if(!slot.isAvailable()){
				carsMap.put(slot.getParkingSlotNumber(), slot.getCar());
				
			}
		}
		return carsMap;
	}

	@Override
	public List<String> getRegistrationNumbersWithColor(String color) {
		List<String> registrationNumbers = new ArrayList<>();
		for(Car car :cars){
			if(car.getColor().equals(color)){
				registrationNumbers.add(car.getRegistrationNumber());
			}
		}
		return registrationNumbers;
	}

	@Override
	public int getSlotNumber(String registrationNumber) {
		for(ParkingSlot slot:parkingSlot){
			if(slot.getCar()!=null && slot.getCar().getRegistrationNumber().equals(registrationNumber)){
				return slot.getParkingSlotNumber();
			}
		}
		return -1;
	}

	@Override
	public List<Integer> getSlotNumbersForColor(String color) {
		List<Integer> slots=new ArrayList<>();
		for(ParkingSlot slot:parkingSlot){
			if(slot.getCar()!=null && slot.getCar().getColor().equals(color)){
				slots.add(slot.getParkingSlotNumber());
			}
		}
		return slots;
	}

	@Override
	public void createParkingSlots(int numberOfSlots) {
		
		parkingSlot = new ParkingSlot[numberOfSlots];
		for(int i=0;i<numberOfSlots;i++){
			parkingSlot[i] = new ParkingSlot(i+1);
		}
		
	}

}
