package com.gojek.cab.inmemory.datagrid;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import com.gojek.cab.service.common.LocationBuilder;
import com.gojek.cab.service.response.impl.FindDriverServiceResult;

public class LocationWriteInMemoryDGTest {
	
	private LocationWriteInMemoryDG locationWriteInMemoryDG = LocationWriteInMemoryDG.getLocationInMemoryWriteDGInstance();
	
	private LocationBuilder locationBuilder = new LocationBuilder();

	@Before
	public void setUp() {
	
	}
	
	@Test
	public  void writeTest() {
		locationWriteInMemoryDG.write(100, locationBuilder.getLocInstanceForUpdateLocService(new BigDecimal("12.97161923"), new BigDecimal("77.59463452"), .7));
		locationWriteInMemoryDG.write(101, locationBuilder.getLocInstanceForUpdateLocService(new BigDecimal("12.98161923"), new BigDecimal("77.60463452"), .7));
		
		assertEquals(2, locationWriteInMemoryDG.getDriversLocation().size());		
	}	

}
