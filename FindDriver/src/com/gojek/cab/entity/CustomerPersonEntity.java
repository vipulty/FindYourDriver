package com.gojek.cab.entity;

public class CustomerPersonEntity extends PersonEntity {

	private static final String ENTITY_CODE = "customers";

	public CustomerPersonEntity(Integer ID, String name) {

		super(ID, name);
		this.entityCode = ENTITY_CODE;
	}

}
