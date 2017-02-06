package com.gojek.cab.inmemory.datagrid;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import com.gojek.cab.service.common.LocationBuilder;
import com.gojek.cab.service.response.impl.FindDriverServiceResult;

public class LocationReadInMemoryDGTest {
	
	private LocationReadInMemoryDG locationReadInMemoryDG = LocationReadInMemoryDG.getLocationInMemoryReadDGInstance();
	
	private LocationBuilder locationBuilder = new LocationBuilder();

	@Before
	public void setUp() {
		 FindDriverServiceResult findDriverServiceResult = new FindDriverServiceResult(100,
				new BigDecimal("12.96161923"), new BigDecimal("77.58463452"));
		locationReadInMemoryDG.getDriversLocation().add(findDriverServiceResult);
		
		findDriverServiceResult = new FindDriverServiceResult(100,
				new BigDecimal("12.95161923"), new BigDecimal("77.57463452"));
		locationReadInMemoryDG.getDriversLocation().add(findDriverServiceResult);
		
		findDriverServiceResult = new FindDriverServiceResult(100,
				new BigDecimal("12.98161923"), new BigDecimal("77.60463452"));
		locationReadInMemoryDG.getDriversLocation().add(findDriverServiceResult);
		
		findDriverServiceResult = new FindDriverServiceResult(100,
				new BigDecimal("12.99161923"), new BigDecimal("77.61463452"));
		locationReadInMemoryDG.getDriversLocation().add(findDriverServiceResult);
	}
	
	@Test
	public  void updateReadInMemoryDGTest() {
		locationReadInMemoryDG.updateReadInMemoryDG(100, locationBuilder.getLocInstanceForUpdateLocService(new BigDecimal("12.97161923"), new BigDecimal("77.59463452"), .7));
		
		assertEquals(5, locationReadInMemoryDG.getDriversLocation().size());		
	}
	
	@Test
	public  void searchInMemoryDataCeilingTest() {
		
		final FindDriverServiceResult findDriverServiceResult = new FindDriverServiceResult(new BigDecimal("12.97161923"), new BigDecimal("77.59463452"));		
		
		
		final TreeSet<FindDriverServiceResult> findDriverServiceResults = new TreeSet<>(
				locationReadInMemoryDG.getFindDriverServiceResultDistanceComparator());
		
		locationReadInMemoryDG.searchInMemoryDataCeiling(findDriverServiceResults, findDriverServiceResult, 6);
		
		assertEquals(2, findDriverServiceResults.size());
		
	}
	
	@Test
	public  void searchInMemoryDataFloorTest() {
		
		final FindDriverServiceResult findDriverServiceResult = new FindDriverServiceResult(new BigDecimal("12.97161923"), new BigDecimal("77.59463452"));		
		
		
		final TreeSet<FindDriverServiceResult> findDriverServiceResults = new TreeSet<>(
				locationReadInMemoryDG.getFindDriverServiceResultDistanceComparator());
		
		locationReadInMemoryDG.searchInMemoryDataFloor(findDriverServiceResults, findDriverServiceResult, 6);
		
		assertEquals(2, findDriverServiceResults.size());
		
	}
	

	@Test
	public  void searchInMemoryDataTest() {
		Set<FindDriverServiceResult> results = 	locationReadInMemoryDG.searchInMemoryData(locationBuilder.getLocInstanceForFindDriverService(new BigDecimal("12.97161923"), new BigDecimal("77.59463452"), 500, 10));
		assertEquals(4, results.size());		
	}


}
