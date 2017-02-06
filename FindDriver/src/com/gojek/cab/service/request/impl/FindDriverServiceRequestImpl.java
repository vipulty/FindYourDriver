package com.gojek.cab.service.request.impl;

import com.gojek.cab.service.common.Location;
import com.gojek.cab.service.request.definition.IFindDriverServiceRequest;
import com.gojek.cab.service.request.definition.IUpdateLocationServiceRequest;

public class FindDriverServiceRequestImpl implements IFindDriverServiceRequest{
	
	private String httpMethod;

	private String serviceName;

	private Location location;

	public FindDriverServiceRequestImpl(String httpMethod, String serviceName, Location location) {
		super();
		this.httpMethod = httpMethod;
		this.serviceName = serviceName;
		this.location = location;
	}	
	

	@Override
	public String getHttpMethod() {
		return httpMethod;
	}

	@Override
	public String getServiceName() {
		return serviceName;
	}

	@Override
	public Location getLocation() {
		return location;
	}
	

	
}
