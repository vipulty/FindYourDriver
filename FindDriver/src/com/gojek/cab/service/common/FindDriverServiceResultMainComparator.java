package com.gojek.cab.service.common;

import java.util.Comparator;

import com.gojek.cab.service.response.impl.FindDriverServiceResult;

public class FindDriverServiceResultMainComparator implements Comparator<FindDriverServiceResult> {

	public FindDriverServiceResultMainComparator() {
		
	}

	@Override
	public int compare(FindDriverServiceResult o1, FindDriverServiceResult o2) {
		int compareResult;
		compareResult = o1.getLatitude().compareTo(o2.getLatitude());
		if (compareResult == 0)
			compareResult = o1.getLongitude().compareTo(o2.getLongitude());

		return compareResult;
	}

}
