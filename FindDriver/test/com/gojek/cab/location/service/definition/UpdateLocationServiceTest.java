package com.gojek.cab.location.service.definition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gojek.cab.entity.DriverPersonEntity;
import com.gojek.cab.location.service.impl.UpdateLocationServiceImpl;


public class UpdateLocationServiceTest {

	private static final String DRIVER_ENTITY_CODE = "drivers";

	private static final String PUT = "PUT";
	
	private static final String DRIVER_NAME = "DRIVER_NAME";

	private static final String UPDATE_LOCATION_SERVICE_NAME = "location";

	private List<DriverPersonEntity> drivers = new ArrayList<>();

	public UpdateLocationServiceTest() {

	}

	@Before
	public void setUp() {

		for (int i = 1; i <= 50000; i++) {
			DriverPersonEntity driverPersonEntity = new DriverPersonEntity(i, (DRIVER_NAME + i));
			drivers.add(driverPersonEntity);
		}
	}

	@Test
	public  void updateDriverLocationTest() {
		
		final DriverPersonEntity driverPersonEntity = drivers.get(0);
		
		final IUpdateLocationService updateLocationService = new UpdateLocationServiceImpl();	
		
	}

}
