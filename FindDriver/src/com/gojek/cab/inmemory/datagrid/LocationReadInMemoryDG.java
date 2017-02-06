package com.gojek.cab.inmemory.datagrid;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.gojek.cab.inmemory.event.IInMemoryDGUpdateEventListener;
import com.gojek.cab.service.common.DistanceCalculator;
import com.gojek.cab.service.common.FindDriverServiceResultDistanceComparator;
import com.gojek.cab.service.common.FindDriverServiceResultMainComparator;
import com.gojek.cab.service.common.Location;
import com.gojek.cab.service.response.impl.FindDriverServiceResult;

public class LocationReadInMemoryDG implements IInMemoryDGUpdateEventListener {

	private final FindDriverServiceResultMainComparator findDriverServiceResultMainComparator = new FindDriverServiceResultMainComparator();

	private final FindDriverServiceResultDistanceComparator findDriverServiceResultDistanceComparator = new FindDriverServiceResultDistanceComparator();

	private final TreeSet<FindDriverServiceResult> driversLocation = new TreeSet<>(
			findDriverServiceResultMainComparator);

	private static LocationReadInMemoryDG locationInMemoryReadDGInstance = new LocationReadInMemoryDG();
	
	private static BigDecimal SMALL_NUM = new BigDecimal(".00000001");

	private LocationReadInMemoryDG() {
	}

	public static LocationReadInMemoryDG getLocationInMemoryReadDGInstance() {
		return locationInMemoryReadDGInstance;
	}

	@Override
	public void updateReadInMemoryDG(Integer entityID, Location location) {

		final FindDriverServiceResult findDriverServiceResult = new FindDriverServiceResult(entityID,
				location.getLatitude(), location.getLongitude());
		driversLocation.add(findDriverServiceResult);
	}

	/*
	 * 
	 */
	public Set<FindDriverServiceResult> searchInMemoryData(Location location) {

		final int resultSetCount = location.getLimit();

		final FindDriverServiceResult findDriverServiceResult = new FindDriverServiceResult(location.getLatitude(),
				location.getLongitude());

		final TreeSet<FindDriverServiceResult> findDriverServiceResults = new TreeSet<>(
				findDriverServiceResultDistanceComparator);

		searchInMemoryDataCeiling(findDriverServiceResults, findDriverServiceResult, resultSetCount);

		searchInMemoryDataFloor(findDriverServiceResults, findDriverServiceResult, resultSetCount);
		int count = 0;

		TreeSet<FindDriverServiceResult> results = new TreeSet<>(findDriverServiceResultDistanceComparator);

		for (FindDriverServiceResult result : findDriverServiceResults) {

			if (count >= resultSetCount) {
				break;
			}

			results.add(result);
			count++;
		}
		return results;
	}

	/*
	 * 
	 */
	public void searchInMemoryDataCeiling(TreeSet<FindDriverServiceResult> findDriverServiceResults,
			FindDriverServiceResult findDriverServiceResult, int resultSetCount) {

		if (findDriverServiceResult == null) {
			return;
		}

		FindDriverServiceResult tempFindDriverServiceResult = findDriverServiceResult;

		for (int i = 0; i < resultSetCount; i++) {

			final FindDriverServiceResult ceiling = driversLocation.ceiling(tempFindDriverServiceResult);

			if (ceiling != null) {

				Double distance = DistanceCalculator.distance(ceiling.getLatitude().doubleValue(),
						ceiling.getLongitude().doubleValue(), findDriverServiceResult.getLatitude().doubleValue(),
						findDriverServiceResult.getLongitude().doubleValue(), "K");				
				
				ceiling.setDistance(new BigDecimal(Double.toString(distance)));

				findDriverServiceResults.add(ceiling);
				tempFindDriverServiceResult = new FindDriverServiceResult(ceiling.getLatitude().add(SMALL_NUM),ceiling.getLongitude().add(SMALL_NUM));
			} else {
				break;
			}
		}
	}

	/*
	 * 
	 */
	public void searchInMemoryDataFloor(TreeSet<FindDriverServiceResult> findDriverServiceResults,
			FindDriverServiceResult findDriverServiceResult, int resultSetCount) {

		if (findDriverServiceResult == null) {
			return;
		}

		FindDriverServiceResult tempFindDriverServiceResult = findDriverServiceResult;

		for (int i = 0; i < resultSetCount; i++) {

			final FindDriverServiceResult floor = driversLocation.floor(tempFindDriverServiceResult);

			if (floor != null) {

				Double distance = DistanceCalculator.distance(floor.getLatitude().doubleValue(),
						floor.getLongitude().doubleValue(), findDriverServiceResult.getLatitude().doubleValue(),
						findDriverServiceResult.getLongitude().doubleValue(), "K");
				floor.setDistance(new BigDecimal(Double.toString(distance)));

				findDriverServiceResults.add(floor);

				tempFindDriverServiceResult = floor;
				tempFindDriverServiceResult = new FindDriverServiceResult(floor.getLatitude().subtract(SMALL_NUM),floor.getLongitude().subtract(SMALL_NUM));
			} else {
				break;
			}
		}
	}

	public TreeSet<FindDriverServiceResult> getDriversLocation() {
		return driversLocation;
	}

	public FindDriverServiceResultDistanceComparator getFindDriverServiceResultDistanceComparator() {
		return findDriverServiceResultDistanceComparator;
	}

}
