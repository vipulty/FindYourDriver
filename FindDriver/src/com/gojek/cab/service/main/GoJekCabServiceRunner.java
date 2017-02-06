package com.gojek.cab.service.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.gojek.cab.entity.DriverPersonEntity;
import com.gojek.cab.location.service.definition.IUpdateLocationService;
import com.gojek.cab.location.service.impl.UpdateLocationServiceImpl;
import com.gojek.cab.service.common.Location;
import com.gojek.cab.service.common.LocationBuilder;

public class GoJekCabServiceRunner {

	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	
	private List<DriverPersonEntity> drivers = new ArrayList<>();
	
	private static final String DRIVER_NAME = "DRIVER_NAME";
	
	public GoJekCabServiceRunner() {	
	}
	
	public static void main (String[] args) throws java.lang.Exception {
		
		final GoJekCabServiceRunner goJekCabServiceRunner = new GoJekCabServiceRunner();
		
		goJekCabServiceRunner.addNewDrivers();		
		goJekCabServiceRunner.updateDriversLocation();		
		goJekCabServiceRunner.findDrivers();	
		
	}
	
	private void addNewDrivers(){
		
		System.out.println("Starting to add drivers");
		
		for (int i = 1; i <= 50000; i++) {
			DriverPersonEntity driverPersonEntity = new DriverPersonEntity(i, (DRIVER_NAME + i));
			drivers.add(driverPersonEntity);
		}	
		
		System.out.println("Ended to add drivers");
	}
	
	private void updateDriversLocation(){
		
		System.out.println("Starting to update drivers location");
		
		final UpdateLocationRunnable updateLocationRunnable = new UpdateLocationRunnable(drivers);
		executor.execute(updateLocationRunnable);
	}	
	
	private void findDrivers(){
		
		System.out.println("Starting to find nearBuy drivers");
		
		final FindDriversRunnable findDriversRunnable = new FindDriversRunnable();
		executor.execute(findDriversRunnable);
	}	
}
