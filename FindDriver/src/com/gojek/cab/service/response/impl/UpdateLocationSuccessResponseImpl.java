package com.gojek.cab.service.response.impl;

import com.gojek.cab.service.response.definition.IUpdateLocationServiceResponse;

public class UpdateLocationSuccessResponseImpl implements IUpdateLocationServiceResponse{
	
	private final static String EMPTY = "";
	
	private final static String SUCCESS_CODE = "200";
	
	private String header = "OK on successful update";

	public UpdateLocationSuccessResponseImpl() {
	}
	
	public String getCode(){		
		return SUCCESS_CODE;
	}
	
	public String getHeader(){		
		return header;
	}
	
	public String getBody(){		
		return EMPTY;
	}

}
