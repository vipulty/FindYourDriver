package com.gojek.cab.service.main;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import com.gojek.cab.entity.DriverPersonEntity;
import com.gojek.cab.location.service.definition.IUpdateLocationService;
import com.gojek.cab.location.service.impl.UpdateLocationServiceImpl;
import com.gojek.cab.service.common.Location;
import com.gojek.cab.service.common.LocationBuilder;
import com.gojek.cab.service.request.definition.IUpdateLocationServiceRequest;
import com.gojek.cab.service.request.impl.UpdateLocationServiceRequestImpl;
import com.gojek.cab.service.response.definition.IUpdateLocationServiceResponse;

public class UpdateLocationRunnable implements Runnable {

	private final List<DriverPersonEntity> drivers;

	private final IUpdateLocationService updateLocationService = new UpdateLocationServiceImpl();

	private static final String DRIVER_ENTITY_CODE = "drivers";

	private static final String PUT = "PUT";

	private static final String UPDATE_LOCATION_SERVICE_NAME = "location";

	private static final BigDecimal RANGE_MAX = new BigDecimal("90.0");

	private static final BigDecimal RANGE_MIN = new BigDecimal("-90.0");


	public UpdateLocationRunnable(List<DriverPersonEntity> drivers) {
		this.drivers = drivers;
	}

	@Override
	public void run() {

		while (true) {

			for (DriverPersonEntity driverPersonEntity : drivers) {

				final Random random = new Random();

				BigDecimal rangeDifference = RANGE_MAX.subtract(RANGE_MIN);

				BigDecimal randomNum = rangeDifference.multiply(new BigDecimal(random.nextDouble()));
				final BigDecimal latitudeValue = randomNum.add(RANGE_MIN);

				randomNum = rangeDifference.multiply(new BigDecimal(random.nextDouble()));
				final BigDecimal longitudeValue = randomNum.add(RANGE_MIN);
				
				final Double accuracyValue = 0 + (1 - 0)*random.nextDouble();

				final Location location = new LocationBuilder().getLocInstanceForUpdateLocService(latitudeValue,
						longitudeValue, accuracyValue);

				final IUpdateLocationServiceRequest updateLocationServiceRequest = new UpdateLocationServiceRequestImpl(
						PUT, DRIVER_ENTITY_CODE, driverPersonEntity.getEntityID(), UPDATE_LOCATION_SERVICE_NAME,
						location);

				final IUpdateLocationServiceResponse updateLocationServiceResponse = updateLocationService
						.updateDriverLocation(updateLocationServiceRequest);

				// System.out.println(
				// updateLocationServiceResponse.getCode() + " " +
				// updateLocationServiceResponse.getHeader());
				// System.out.println(updateLocationServiceResponse.getBody());
			}

			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
