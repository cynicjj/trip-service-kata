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

	private TripService tripService;

	@BeforeEach
	void init() {
		tripService = new TestableTripService();
	}

	@Test
	void 로그인_유저가_없으면_예외() {
		assertThrows(UserNotLoggedInException.class, () -> {
			tripService.getTripsByUser(ANOTHER_USER, GUEST);
		});
	}

	@Test
	void 친구가_아니면_여행목록_없다() {
		User targetUser = UserBuilder.aUser()
				.friendsWith(ANOTHER_USER)
				.tripsWith(TO_BRAZIL)
				.build();

		List<Trip> trips = tripService.getTripsByUser(targetUser, REGISTERED_USER);

		assertTrue(trips.isEmpty());
	}

	@Test
	void 친구의_여행목록을_가져온다() {
		User targetUser = UserBuilder.aUser()
				.friendsWith(ANOTHER_USER, REGISTERED_USER)
				.tripsWith(TO_BRAZIL, TO_KOREA)
				.build();

		List<Trip> trips = tripService.getTripsByUser(targetUser, REGISTERED_USER);

		assertTrue(trips.size() == 2);

	}

	private class TestableTripService extends TripService {

		@Override
		protected List<Trip> tripsBy(User user) {
			return user.trips();
		}
	}
}
