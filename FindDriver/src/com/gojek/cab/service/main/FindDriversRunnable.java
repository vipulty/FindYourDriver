package com.gojek.cab.service.main;

import java.math.BigDecimal;

import com.gojek.cab.search.service.definition.IFindDriverService;
import com.gojek.cab.search.service.impl.FindDriverServiceImpl;
import com.gojek.cab.service.common.Location;
import com.gojek.cab.service.common.LocationBuilder;
import com.gojek.cab.service.request.definition.IFindDriverServiceRequest;
import com.gojek.cab.service.request.impl.FindDriverServiceRequestImpl;
import com.gojek.cab.service.response.definition.IFindDriverServiceResponse;

public class FindDriversRunnable implements Runnable {

	private final IFindDriverService findDriverService = new FindDriverServiceImpl();

	private static final String GET = "GET";

	private static final String FIND_DRIVER_SERVICE_NAME = "drivers";

	public FindDriversRunnable() {

	}

	@Override
	public void run() {

		final Location location = new LocationBuilder().getDefaultLocationInsance();

		location.setLatitude(new BigDecimal(12.97161923));
		location.setLongitude(new BigDecimal(77.59463452));

		IFindDriverServiceResponse findDriverServiceResponse = null;

		while (true) {

			System.out.println("Find drivers for latitude = " + location.getLatitude() + " longitude = "
					+ location.getLongitude());

			final IFindDriverServiceRequest findDriverServiceRequest = new FindDriverServiceRequestImpl(GET,
					FIND_DRIVER_SERVICE_NAME, location);

			findDriverServiceResponse = findDriverService.findDriver(findDriverServiceRequest);

			if (findDriverServiceResponse.getResponsebody() != null) {

				System.out.println(findDriverServiceResponse.getResponseCode() + " " + findDriverServiceResponse.getHeader());
				System.out.println(findDriverServiceResponse.getResponsebody());

			}
			try {
				Thread.sleep(120000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
