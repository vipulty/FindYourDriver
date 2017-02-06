package com.gojek.cab.entity;

public class DriverPersonEntity extends PersonEntity {

	private static final String ENTITY_CODE = "drivers";

	public DriverPersonEntity(Integer ID, String name) {

		super(ID, name);
		this.entityCode = ENTITY_CODE;
	}

}
