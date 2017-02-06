package com.gojek.cab.service.response.definition;

import java.util.List;
import java.util.Set;

import com.gojek.cab.service.response.impl.FindDriverServiceResult;

public interface IFindDriverServiceResponse {
	
	String getResponseCode();
	
	String getHeader();
	
	String getResponsebody();
}
