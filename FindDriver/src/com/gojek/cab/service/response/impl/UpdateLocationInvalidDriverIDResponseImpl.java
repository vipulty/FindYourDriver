package com.gojek.cab.service.response.impl;

import com.gojek.cab.service.response.definition.IUpdateLocationServiceResponse;

public class UpdateLocationInvalidDriverIDResponseImpl implements IUpdateLocationServiceResponse{
	
	private final static String EMPTY = "";
	
	private final static String ERROR_CODE = "404";

	public UpdateLocationInvalidDriverIDResponseImpl() {		
	}
	
	public String getCode(){		
		return ERROR_CODE;
	}
	
	public String getHeader(){		
		return "Not Found. the driver ID is invalid (valid driver ids - 1 to 50000)";
	}
	
	public String getBody(){		
		return EMPTY;
	}

}
