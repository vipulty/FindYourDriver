package com.gojek.cab.service.request.definition;

import com.gojek.cab.service.common.Location;

public interface IFindDriverServiceRequest {	
	 
	 String getHttpMethod();
	 
	 String getServiceName();

	 Location getLocation();

}
