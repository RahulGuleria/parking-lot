package com.rahul.assignment.service;

import java.util.List;
import java.util.TreeMap;

import com.rahul.assignment.exception.NoParkingLotAvailableException;
import com.rahul.assignment.exception.ParkingSlotNotOccupiedException;
import com.rahul.assignment.model.Car;

public interface ParkingService {
	
	//public static List<Car> cars=new ArrayList<>();
	public void createParkingSlots(int numberOfSlots);
	public int parkCar(Car car) throws NoParkingLotAvailableException;
	public boolean leaveSlot(int parkingSlotNumber) throws ParkingSlotNotOccupiedException;
	public TreeMap<Integer,Car> getAllParkedCarsDetails();
	public List<String> getRegistrationNumbersWithColor(String color);
	public int getSlotNumber(String registrationNumber);
	public List<Integer> getSlotNumbersForColor(String color);
}
