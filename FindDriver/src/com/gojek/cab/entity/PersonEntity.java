package com.gojek.cab.entity;

public abstract class PersonEntity {
  
	private Integer entityID;
	
	private String entityName;
	
	protected String entityCode;
	
	public PersonEntity(Integer entityID, String entityName) {
		super();
		this.entityID = entityID;
		this.entityName = entityName;
	}

	public Integer getEntityID() {
		return entityID;
	}	

	public String getEntityName() {
		return entityName;
	}
	

	public String getEntityCode() {
		return entityCode;
	}

}
