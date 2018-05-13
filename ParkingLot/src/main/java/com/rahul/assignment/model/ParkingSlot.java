package com.rahul.assignment.model;

public class ParkingSlot {
	private int parkingSlotNumber;
	private boolean available = true;
	private Car car;
	
	public ParkingSlot(int parkingSlotNumber) {
		super();
		this.parkingSlotNumber = parkingSlotNumber;
	}
	public ParkingSlot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getParkingSlotNumber() {
		return parkingSlotNumber;
	}
	public void setParkingSlotNumber(int parkingSlotNumber) {
		this.parkingSlotNumber = parkingSlotNumber;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
}
