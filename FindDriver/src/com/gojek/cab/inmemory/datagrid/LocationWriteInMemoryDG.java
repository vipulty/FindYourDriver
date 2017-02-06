package com.gojek.cab.inmemory.datagrid;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gojek.cab.inmemory.event.IInMemoryDGUpdateEventListener;
import com.gojek.cab.service.common.Location;

public class LocationWriteInMemoryDG {

	private Map<Integer, Location> driversLocation = new ConcurrentHashMap<>();

	private static LocationWriteInMemoryDG locationInMemoryWriteDGInstance = new LocationWriteInMemoryDG();

	private IInMemoryDGUpdateEventListener inMemoryDGUpdateEventListener = LocationReadInMemoryDG.getLocationInMemoryReadDGInstance();

	private LocationWriteInMemoryDG() {
	}

	public static LocationWriteInMemoryDG getLocationInMemoryWriteDGInstance() {
		return locationInMemoryWriteDGInstance;
	}

	public void write(Integer entityID, Location location) {
		
		driversLocation.put(entityID, location);
		inMemoryDGUpdateEventListener.updateReadInMemoryDG(entityID, location);
	}

	public Map<Integer, Location> getDriversLocation() {
		return driversLocation;
	}

}
