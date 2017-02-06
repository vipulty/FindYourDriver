package com.gojek.cab.search.service.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.gojek.cab.inmemory.datagrid.LocationReadInMemoryDG;
import com.gojek.cab.search.service.definition.IFindDriverService;
import com.gojek.cab.service.common.Location;
import com.gojek.cab.service.common.LocationBuilder;
import com.gojek.cab.service.request.definition.IFindDriverServiceRequest;

import com.gojek.cab.service.request.impl.FindDriverServiceRequestImpl;

import com.gojek.cab.service.response.definition.IFindDriverServiceResponse;
import com.gojek.cab.service.response.impl.FindDriverServiceResult;

public class FindDriverServiceImplTest {
	
	private IFindDriverServiceRequest findDriverServiceRequest;
	
	private LocationBuilder locationBuilder = new LocationBuilder();
	
	private IFindDriverService findDriverService = new FindDriverServiceImpl();
	
	private LocationReadInMemoryDG locationReadInMemoryDG = LocationReadInMemoryDG.getLocationInMemoryReadDGInstance();

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
	public void findDriverTest() {
		// Location location = locationBuilder.getLocInstanceForFindDriverService(new BigDecimal("12.97161923"), new BigDecimal("77.59463452"), 500, 10);
		
		Location location =
				locationBuilder.getLocInstanceForFindDriverService(new BigDecimal("91"), new BigDecimal("77.59463452"), 500, 10);

		findDriverServiceRequest = new FindDriverServiceRequestImpl("GET","drivers",location);

		 IFindDriverServiceResponse findDriverServiceResponse = findDriverService.findDriver(findDriverServiceRequest);
		
		assertEquals("400", findDriverServiceResponse.getResponseCode());
		assertEquals("Bad Request", findDriverServiceResponse.getHeader());
		assertEquals("\"errors\":Latitude should be between +/- 90", findDriverServiceResponse.getResponsebody());
		
		 location =
				locationBuilder.getLocInstanceForFindDriverService(new BigDecimal("12.97161923"), new BigDecimal("77.59463452"), 500, 10);
		 
		 findDriverServiceRequest = new FindDriverServiceRequestImpl("GET","drivers",location);
		 
		 findDriverServiceResponse = findDriverService.findDriver(findDriverServiceRequest);
		 
		 
		assertEquals("200", findDriverServiceResponse.getResponseCode());
		assertEquals("OK", findDriverServiceResponse.getHeader());

		
		//System.out.println(findDriverServiceResponse.getResponseCode() + " ==> " + findDriverServiceResponse.getHeader() + " ==> " + findDriverServiceResponse.getResponsebody());
	}

}
