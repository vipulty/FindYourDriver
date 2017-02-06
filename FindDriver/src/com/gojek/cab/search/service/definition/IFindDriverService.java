package com.gojek.cab.search.service.definition;

import com.gojek.cab.service.request.definition.IFindDriverServiceRequest;
import com.gojek.cab.service.response.definition.IFindDriverServiceResponse;


public interface IFindDriverService {
	
	IFindDriverServiceResponse findDriver(IFindDriverServiceRequest findDriverServiceRequest);
}
