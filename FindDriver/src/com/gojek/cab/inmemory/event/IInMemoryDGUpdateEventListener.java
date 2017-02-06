package com.gojek.cab.inmemory.event;

import com.gojek.cab.service.common.Location;

public interface IInMemoryDGUpdateEventListener {
	
	void updateReadInMemoryDG(Integer entityID, Location location);
}
