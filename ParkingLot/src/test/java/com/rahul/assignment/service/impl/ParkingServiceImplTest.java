package com.rahul.assignment.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.rahul.assignment.exception.NoParkingLotAvailableException;
import com.rahul.assignment.exception.ParkingSlotNotOccupiedException;
import com.rahul.assignment.model.Car;
import com.rahul.assignment.service.ParkingService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingServiceImplTest {
	
	static ParkingService parkingService;
	
	@BeforeClass
	public static void setUp() throws Exception {
		parkingService= new ParkingServiceImpl();
		
	}

	@Test
	public void testCreateParkingSlots() {
		parkingService.createParkingSlots(3);
	}
	
	@Test
	public void testFirstParkCar() throws NoParkingLotAvailableException {
		Car car=new Car("DL-12-AA-9999", "White");
		assertEquals(1,parkingService.parkCar(car));
		Car car1=new Car("KA-01-HH-9999", "White");
		assertEquals(2,parkingService.parkCar(car1));
		Car car2=new Car("KA-01-BB-0001", "Black");
		assertEquals(3,parkingService.parkCar(car2));
	}

	@Test
	public void testLeaveSlot() throws ParkingSlotNotOccupiedException {
		assertTrue(parkingService.leaveSlot(2));
	}

	@Test
	public void testGetAllParkedCarsDetails() {
		assertNotNull(parkingService.getAllParkedCarsDetails());
	}

	@Test
	public void testGetRegistrationNumbersWithColor() {
		assertEquals(2, parkingService.getRegistrationNumbersWithColor("White").size());
	}

	@Test
	public void testGetSlotNumber() {
		assertEquals(1, parkingService.getSlotNumber("DL-12-AA-9999"));
	}

	@Test
	public void testGetSlotNumbersForColor() {
		assertEquals(2, parkingService.getSlotNumbersForColor("White").size());
	}



}
