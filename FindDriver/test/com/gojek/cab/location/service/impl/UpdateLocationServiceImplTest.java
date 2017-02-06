package com.gojek.cab.location.service.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.gojek.cab.location.service.definition.IUpdateLocationService;
import com.gojek.cab.service.common.LocationBuilder;
import com.gojek.cab.service.request.definition.IUpdateLocationServiceRequest;
import com.gojek.cab.service.request.impl.UpdateLocationServiceRequestImpl;
import com.gojek.cab.service.response.definition.IUpdateLocationServiceResponse;

public class UpdateLocationServiceImplTest {
	
	private IUpdateLocationServiceRequest updateLocationServiceRequest;
	
	private LocationBuilder locationBuilder = new LocationBuilder();
	
	private IUpdateLocationService updateLocationService = new UpdateLocationServiceImpl();

	@Before
	public void setUp() {
		
	}
	
	@Test
	public  void updateDriverLocationTest() {
		updateLocationServiceRequest = new UpdateLocationServiceRequestImpl("PUT","drivers",0,"location",
				locationBuilder.getLocInstanceForUpdateLocService(new BigDecimal("12.97161923"), new BigDecimal("77.59463452"), .7));
		
		 IUpdateLocationServiceResponse updateLocationServiceResponse = updateLocationService.updateDriverLocation(updateLocationServiceRequest);
		
		assertEquals("404", updateLocationServiceResponse.getCode());
		assertEquals("Not Found. the driver ID is invalid (valid driver ids - 1 to 50000)", updateLocationServiceResponse.getHeader());	
		
		updateLocationServiceRequest = new UpdateLocationServiceRequestImpl("PUT","drivers",1,"location",
				locationBuilder.getLocInstanceForUpdateLocService(new BigDecimal("91"), new BigDecimal("77.59463452"), .7));
		
		updateLocationServiceResponse = updateLocationService.updateDriverLocation(updateLocationServiceRequest);		
		
		
		assertEquals("422", updateLocationServiceResponse.getCode());
		assertEquals("Unprocessable Entity", updateLocationServiceResponse.getHeader());
		assertEquals("\"errors\":Latitude should be between +/- 90", updateLocationServiceResponse.getBody());
		
		
		updateLocationServiceRequest = new UpdateLocationServiceRequestImpl("PUT","drivers",100,"location",
				locationBuilder.getLocInstanceForUpdateLocService(new BigDecimal("12.97161923"), new BigDecimal("77.59463452"), .7));
		
		updateLocationServiceResponse = updateLocationService.updateDriverLocation(updateLocationServiceRequest);
		
		//System.out.println(updateLocationServiceResponse.getCode() + " ==> " + updateLocationServiceResponse.getHeader() + " ==> " + updateLocationServiceResponse.getBody());
		
		assertEquals("200", updateLocationServiceResponse.getCode());
		assertEquals("OK on successful update", updateLocationServiceResponse.getHeader());		
		
	}

}
