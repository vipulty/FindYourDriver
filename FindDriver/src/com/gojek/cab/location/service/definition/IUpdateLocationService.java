package com.gojek.cab.location.service.definition;

import com.gojek.cab.service.request.definition.IUpdateLocationServiceRequest;
import com.gojek.cab.service.response.definition.IUpdateLocationServiceResponse;

public interface IUpdateLocationService {

	IUpdateLocationServiceResponse updateDriverLocation(IUpdateLocationServiceRequest updateLocationServiceRequest);
	
}
