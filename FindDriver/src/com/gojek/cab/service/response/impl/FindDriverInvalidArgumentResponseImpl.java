package com.gojek.cab.service.response.impl;

import java.util.Set;

import com.gojek.cab.service.response.definition.IFindDriverServiceResponse;

public class FindDriverInvalidArgumentResponseImpl implements IFindDriverServiceResponse {

	private String responseBody;
	
	private final static String ERROR_CODE = "400";
	
	private String header = "Bad Request";


	public FindDriverInvalidArgumentResponseImpl(String responseBody) {	
	  this.responseBody = responseBody;
	}	
	
	
	@Override
	public String getHeader(){		
		return header;
	}	
	

	@Override
	public String getResponseCode() {		
		return ERROR_CODE;
	}

	@Override
	public String getResponsebody() {
		return "\"errors\":" + responseBody;
	}
	
	

}
