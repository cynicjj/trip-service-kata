package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

	private static final User GUEST = null;
	private static final User REGISTERED_USER = new User();
	private static final User ANOTHER_USER = new User();

	private static final Trip TO_BRAZIL = new Trip();
	private static final Trip TO_KOREA = new Trip();

	private User loggedInUser;
	private TripService tripService;

	@BeforeEach
	void init() {
		tripService = new TestableTripService();
		loggedInUser = REGISTERED_USER;
	}

	@Test
	void 로그인_유저가_없으면_예외() {
		loggedInUser = GUEST;

		assertThrows(UserNotLoggedInException.class, () -> {
			tripService.getTripsByUser(new User());
		});
	}

	@Test
	void 친구가_아니면_여행목록_없다() {
		User targetUser = UserBuilder.aUser()
				.friendsWith(ANOTHER_USER)
				.tripsWith(TO_BRAZIL)
				.build();

		List<Trip> trips = tripService.getTripsByUser(targetUser);

		assertTrue(trips.isEmpty());
	}

	@Test
	void 친구의_여행목록을_가져온다() {
		User targetUser = UserBuilder.aUser()
				.friendsWith(ANOTHER_USER, loggedInUser)
				.tripsWith(TO_BRAZIL, TO_KOREA)
				.build();

		List<Trip> trips = tripService.getTripsByUser(targetUser);

		assertTrue(trips.size() == 2);

	}

	private class TestableTripService extends TripService {

		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}

		@Override
		protected List<Trip> tripsBy(User user) {
			return user.trips();
		}
	}
}
