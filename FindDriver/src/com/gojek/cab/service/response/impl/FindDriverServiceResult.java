package com.gojek.cab.service.response.impl;

import java.math.BigDecimal;

public class FindDriverServiceResult {

	private Integer driverID;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private BigDecimal distance;

	public FindDriverServiceResult(BigDecimal latitude, BigDecimal longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public FindDriverServiceResult(Integer driverID, BigDecimal latitude, BigDecimal longitude) {
		super();
		this.driverID = driverID;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public FindDriverServiceResult(Integer driverID, BigDecimal latitude, BigDecimal longitude, BigDecimal distance) {
		super();
		this.driverID = driverID;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
	}

	public Integer getDriverID() {
		return driverID;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((driverID == null) ? 0 : driverID.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FindDriverServiceResult other = (FindDriverServiceResult) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (driverID == null) {
			if (other.driverID != null)
				return false;
		} else if (!driverID.equals(other.driverID))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{id: " + driverID + " , latitude: " + latitude + " , longitude: " + longitude + ", distance: "
				+ distance + "}";
	}

}
