package com.gojek.cab.service.response.impl;

import com.gojek.cab.service.response.definition.IUpdateLocationServiceResponse;

public class UpdateLocationUnprocessableEntityResponseImpl implements IUpdateLocationServiceResponse{	
 
	private String body;
	
	private final static String ERROR_CODE = "422";
	
	private String header = "Unprocessable Entity";


	public UpdateLocationUnprocessableEntityResponseImpl(String body) {	
	  this.body = body;
	}
	
	@Override
	public String getCode(){		
		return ERROR_CODE;
	}
	
	@Override
	public String getHeader(){		
		return header;
	}
	
	@Override
	public String getBody(){		
		return "\"errors\":" + body;
	}
	

}
