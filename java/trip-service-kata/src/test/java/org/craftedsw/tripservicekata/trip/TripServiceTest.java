package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
/*

import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
//*/

public class TripServiceTest {

	private TestableTripService service;

	private User loggedInUser;

	private class TestableTripService extends TripService {

		@Override
		protected User getLoggedUser() {
			return loggedInUser;
		}

		@Override
		protected List<Trip> findTripByUser(User user) {
			return user.trips();
		}
	}

	@BeforeEach
	private void init() {
		service = new TestableTripService();
		loggedInUser = new User();
	}

//	@Test
	void learn() {
		TripService service = new TripService();
		service.getTripsByUser(null);

	}

	@Test
	void 로그인_유저_없으면_예외() {
		loggedInUser = null;

		assertThrows(UserNotLoggedInException.class, () -> {
			service.getTripsByUser(null);
		});
	}

	@Test
	void 친구_아니면_여행_목록_없음() {
		loggedInUser = new User();

		List<Trip> trips = service.getTripsByUser(new User());

		assertThat(trips.size(), is(0));
	}

	@Test
	void 친구면_여행_목록_가져온다() {
		loggedInUser = new User();

		User friend = new User();
		friend.addFriend(loggedInUser);
		friend.addTrip(new Trip());
		friend.addTrip(new Trip());

		List<Trip> trips = service.getTripsByUser(friend);

		assertThat(trips.size(), is(2));
	}
}
