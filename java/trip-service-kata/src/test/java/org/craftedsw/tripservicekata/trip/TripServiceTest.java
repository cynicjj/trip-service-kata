package org.craftedsw.tripservicekata.trip;

import org.junit.jupiter.api.Test;

/*
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
//*/

public class TripServiceTest {

	@Test
	void learn() {
		TripService service = new TripService();
		service.getTripsByUser(null);
	}
}
