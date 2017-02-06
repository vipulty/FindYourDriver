package com.gojek.cab.location.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gojek.cab.inmemory.datagrid.LocationWriteInMemoryDG;
import com.gojek.cab.location.service.definition.IUpdateLocationService;
import com.gojek.cab.service.common.Location;
import com.gojek.cab.service.request.definition.IUpdateLocationServiceRequest;
import com.gojek.cab.service.response.definition.IUpdateLocationServiceResponse;
import com.gojek.cab.service.response.impl.UpdateLocationInvalidDriverIDResponseImpl;
import com.gojek.cab.service.response.impl.UpdateLocationSuccessResponseImpl;
import com.gojek.cab.service.response.impl.UpdateLocationUnprocessableEntityResponseImpl;

/**
 * @author vipul
 *
 */
public class UpdateLocationServiceImpl implements IUpdateLocationService {
	

	public UpdateLocationServiceImpl() {
	}

	/**
	 * @param updateLocationServiceRequest
	 * @return IUpdateLocationServiceResponse
	 */
	@Override
	public IUpdateLocationServiceResponse updateDriverLocation(
			IUpdateLocationServiceRequest updateLocationServiceRequest) {

		final Integer entityID = updateLocationServiceRequest.getEntityID();

		if (entityID == null || !entityIDValidation(entityID)) {
			
			final IUpdateLocationServiceResponse updateLocationServiceResponse = new UpdateLocationInvalidDriverIDResponseImpl();
			return updateLocationServiceResponse;
		}

		final Location location = updateLocationServiceRequest.getLocation();

		final LocationValidationResult locationValidationResult = locationValidation(location);

		if (!locationValidationResult.getValidationStatus()) {

			final IUpdateLocationServiceResponse updateLocationServiceResponse = new UpdateLocationUnprocessableEntityResponseImpl(
					locationValidationResult.getErrorMessage());
			return updateLocationServiceResponse;
		}

		LocationWriteInMemoryDG.getLocationInMemoryWriteDGInstance().write(entityID, location);

		final IUpdateLocationServiceResponse updateLocationServiceResponse = new UpdateLocationSuccessResponseImpl();

		return updateLocationServiceResponse;
	}

	/**
	 * @param entityID
	 * @return Boolean
	 */
	private Boolean entityIDValidation(Integer entityID) {

		if (entityID <= 50000 && entityID >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * @param location
	 * @return
	 */
	private LocationValidationResult locationValidation(Location location) {

		if (location == null) {
			return new LocationValidationResult(false, "Location cannot be null");
		}			

		if (location.getLatitude().floatValue() <= 90 && location.getLatitude().floatValue() >= -90) {

			return new LocationValidationResult(true);
		} else if (location.getLatitude().floatValue() > 90 || location.getLatitude().floatValue() < -90) {
			return new LocationValidationResult(false, "Latitude should be between +/- 90");
		}
		return new LocationValidationResult(false);
	}


	/**
	 *
	 */
	private class LocationValidationResult {

		private Boolean validationStatus;

		private String errorMessage = "";

		public LocationValidationResult(Boolean validationStatus) {
			super();
			this.validationStatus = validationStatus;
		}

		public LocationValidationResult(Boolean validationStatus, String errorMessage) {
			super();
			this.validationStatus = validationStatus;
			this.errorMessage = errorMessage;
		}

		public Boolean getValidationStatus() {
			return validationStatus;
		}
		
		public String getErrorMessage() {
			return errorMessage;
		}

	}

}
