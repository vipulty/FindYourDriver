package com.gojek.cab.service.request.definition;

import com.gojek.cab.service.common.Location;

public interface IUpdateLocationServiceRequest {

	String getHttpMethod();

	String getEntityCode();

	Integer getEntityID();

	String getServiceName();

	Location getLocation();

}
