package com.gojek.cab.service.common;

import java.math.BigDecimal;

public final class LocationBuilder {

	private Location location = new Location();

	public Location getDefaultLocationInsance() {
		return location;
	}

	public Location getLocInstanceForUpdateLocService(BigDecimal latitude, BigDecimal longitude, Double accuracy) {

		final Location location = new Location();

		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setAccuracy(accuracy);

		return location;
	}

	public Location getLocInstanceForFindDriverService(BigDecimal latitude, BigDecimal longitude, Integer radius,
			Integer limit) {

		final Location location = new Location();

		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setRadius(radius);
		location.setLimit(limit);

		return location;
	}

	public void setLatitude(BigDecimal latitude) {
		location.setLatitude(latitude);
	}

	public void setLongitude(BigDecimal longitude) {
		location.setLongitude(longitude);
	}

	public void setAccuracy(Double accuracy) {
		location.setAccuracy(accuracy);
	}

	public void setRadius(Integer radius) {
		location.setRadius(radius);
	}

	public void setLimit(Integer limit) {
		location.setLimit(limit);
	}

}
