package com.gojek.cab.service.request.impl;

import com.gojek.cab.service.common.Location;
import com.gojek.cab.service.request.definition.IFindDriverServiceRequest;
import com.gojek.cab.service.request.definition.IUpdateLocationServiceRequest;

public class UpdateLocationServiceRequestImpl implements IUpdateLocationServiceRequest {

	private String httpMethod;

	private String entityCode;

	private Integer entityID;

	private String serviceName;

	private Location location;

	public UpdateLocationServiceRequestImpl(String httpMethod, String entityCode, Integer entityID, String serviceName,
			Location location) {
		super();
		this.httpMethod = httpMethod;
		this.entityCode = entityCode;
		this.entityID = entityID;
		this.serviceName = serviceName;
		this.location = location;
	}

	@Override
	public String getHttpMethod() {
		return httpMethod;
	}

	@Override
	public String getEntityCode() {
		return entityCode;
	}

	@Override
	public Integer getEntityID() {
		return entityID;
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
