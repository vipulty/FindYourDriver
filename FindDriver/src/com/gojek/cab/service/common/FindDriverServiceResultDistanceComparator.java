package com.gojek.cab.service.common;

import java.util.Comparator;

import com.gojek.cab.service.response.impl.FindDriverServiceResult;

public class FindDriverServiceResultDistanceComparator implements Comparator<FindDriverServiceResult> {

	public FindDriverServiceResultDistanceComparator() {

	}

	@Override
	public int compare(FindDriverServiceResult o1, FindDriverServiceResult o2) {

		int compareValue = o1.getDistance().compareTo(o2.getDistance());

		if (compareValue == 1) {
			return -1;
		} else if (compareValue == -1) {
			return 1;
		}

		return 0;

	}

}
