package com.rahul.assignment.service;

import java.util.HashMap;
import java.util.Map;

import com.rahul.assignment.exception.NoServiceFoundException;
import com.rahul.assignment.service.impl.ParkingServiceImpl;

public class ParkingFactory {

	private static ParkingFactory parkingFactory = null;
	
	private final Map<Class<?>, Object> values = new HashMap<>();
	
	private ParkingFactory(){}
	
	public static ParkingFactory getInstance(){
		if(parkingFactory==null){
			synchronized (ParkingFactory.class) {
				if(parkingFactory==null){
					return new ParkingFactory();
				}else{
					return parkingFactory;
				}
			}
		}else{
			return parkingFactory;
		}
	}
	
	public <T> T getService(Class<T> key)  throws NoServiceFoundException
	{
		if (values.get(key) == null) {
			if(key==ParkingService.class)
			{
				values.put(key, new ParkingServiceImpl());
			}
			else
			{
				throw new NoServiceFoundException("No Service class with name "+key.getName()+" found.");
			}
		}
		return key.cast(values.get(key));
	}
	
}
