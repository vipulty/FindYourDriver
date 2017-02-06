package com.gojek.cab.search.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.gojek.cab.inmemory.datagrid.LocationReadInMemoryDG;
import com.gojek.cab.search.service.definition.IFindDriverService;
import com.gojek.cab.service.common.Location;
import com.gojek.cab.service.request.definition.IFindDriverServiceRequest;
import com.gojek.cab.service.request.definition.IUpdateLocationServiceRequest;
import com.gojek.cab.service.request.impl.UpdateLocationServiceRequestImpl;
import com.gojek.cab.service.response.definition.IFindDriverServiceResponse;
import com.gojek.cab.service.response.definition.IUpdateLocationServiceResponse;
import com.gojek.cab.service.response.impl.FindDriverInvalidArgumentResponseImpl;
import com.gojek.cab.service.response.impl.FindDriverServiceResult;
import com.gojek.cab.service.response.impl.FindDriverSuccessResponseImpl;
import com.gojek.cab.service.response.impl.UpdateLocationUnprocessableEntityResponseImpl;

public class FindDriverServiceImpl implements IFindDriverService {

	public FindDriverServiceImpl() {

	}

	@Override
	public IFindDriverServiceResponse findDriver(IFindDriverServiceRequest findDriverServiceRequest) {

		final LocationValidationResult locationValidationResult = locationValidation(
				findDriverServiceRequest.getLocation());

		if (!locationValidationResult.getValidationStatus()) {

			final IFindDriverServiceResponse findDriverServiceResponse = new FindDriverInvalidArgumentResponseImpl(
					locationValidationResult.getErrorMessage());
			return findDriverServiceResponse;
		}

		final Set<FindDriverServiceResult> findDriverServiceResults = LocationReadInMemoryDG
				.getLocationInMemoryReadDGInstance().searchInMemoryData(findDriverServiceRequest.getLocation());
		
		
		final StringBuilder result = new StringBuilder();
		
		if(findDriverServiceResults.size() > 0){
			

			for (FindDriverServiceResult findDriverServiceResult : findDriverServiceResults) {
				result.append(",").append(findDriverServiceResult);
			}
		} else {
			result.append("No Driver Found.Try again after some time");
		}

		

		final IFindDriverServiceResponse findDriverServiceResponse = new FindDriverSuccessResponseImpl(
				result.toString());

		return findDriverServiceResponse;
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
