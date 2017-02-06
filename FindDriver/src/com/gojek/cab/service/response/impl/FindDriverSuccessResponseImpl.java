package com.gojek.cab.service.response.impl;


import java.util.Set;

import com.gojek.cab.service.response.definition.IFindDriverServiceResponse;

public class FindDriverSuccessResponseImpl implements IFindDriverServiceResponse{
	
	private final static String SUCCESS_CODE = "200";
	
	private String header = "OK";	
	
	private String responseBody;	

	public FindDriverSuccessResponseImpl(String responseBody) {
		this.responseBody = responseBody;
	}
	
	@Override
	public String getResponseCode(){		
		return SUCCESS_CODE;
	}
	
	@Override
	public String getHeader(){		
		return header;
	}
	
	@Override
	public String getResponsebody(){		
		return responseBody;
	}	

}
