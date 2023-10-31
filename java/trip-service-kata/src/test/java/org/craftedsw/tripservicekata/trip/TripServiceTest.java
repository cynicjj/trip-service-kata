package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
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
//		service = new TestableTripService();
		@Override
		protected User getLoggedUser() {
			// TODO Auto-generated method stub
			return null;
		}
	}

//	@Test
	void learn() {
		TripService service = new TripService();
		service.getTripsByUser(null);
	}

	@Test
	void 로그인_유저_없으면_예외() {
		TripService service = new TestableTripService();

		assertThrows(UserNotLoggedInException.class, () -> {
			service.getTripsByUser(null);
		});
	}

	@Test
	void 친구_아니면_여행_목록_없다() {

	}
}
