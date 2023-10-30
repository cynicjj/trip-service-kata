package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.TripService_Original;
import org.craftedsw.tripservicekata.user.User;
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

	private class TestableTripService extends TripService {

		@Override
		protected User getLoggedUser() {
			// TODO
			return null;
		}
	}
	
//	@Test
	void learn() {
		TripService service = new TripService();
		service.getTripsByUser(null);

	}
	
	@Test
	void learn2() {
		TestableTripService service = new TestableTripService();
		service.getTripsByUser(null);
	}
}
