package com.gojek.cab.search.service.definition;

import org.junit.Before;
import org.junit.Test;

import com.gojek.cab.location.service.definition.IUpdateLocationService;
import com.gojek.cab.location.service.impl.UpdateLocationServiceImpl;
import com.gojek.cab.search.service.impl.FindDriverServiceImpl;
import com.gojek.cab.service.common.Location;
import com.gojek.cab.service.common.LocationBuilder;
import com.gojek.cab.service.request.definition.IFindDriverServiceRequest;
import com.gojek.cab.service.request.impl.FindDriverServiceRequestImpl;
import com.gojek.cab.service.response.definition.IFindDriverServiceResponse;
import com.gojek.cab.service.response.definition.IUpdateLocationServiceResponse;

public class FindDriverServiceTest {

	private static final String GET = "GET";

	private static final String FIND_DRIVER_SERVICE_NAME = "drivers";

	public FindDriverServiceTest() {
	}

	@Before
	public void setUp() {

	}

	@Test
	public void findDriverTest() {

		final Location location = new LocationBuilder().getDefaultLocationInsance();

		location.setLatitude(12.97161923);
		location.setLongitude(77.59463452);

		final IFindDriverServiceRequest findDriverServiceRequest = new FindDriverServiceRequestImpl(GET,
				FIND_DRIVER_SERVICE_NAME, location);

		final IFindDriverService findDriverService = new FindDriverServiceImpl();

		final IFindDriverServiceResponse findDriverServiceResponse = findDriverService
				.findDriver(findDriverServiceRequest);

	}

}
