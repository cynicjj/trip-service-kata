package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

	private static final User GUEST = null;
	private static final User REGISTERED_USER = new User();
	private static final User ANOTHER_USER = new User();
	
	private User loggedInUser;

	@Test
	void 로그인_유저가_없으면_예외() {
		TripService tripService = new TestableTripService();
		loggedInUser = GUEST;
		
		assertThrows(UserNotLoggedInException.class, () -> {
			tripService.getTripsByUser(new User());
		});
	}
	
	@Test
	void 친구가_아니면_여행목록_없다() {
		TripService tripService = new TestableTripService();
		loggedInUser = REGISTERED_USER;
		
		User targetUser = new User();
		targetUser.addFriend(ANOTHER_USER);
		targetUser.addTrip(new Trip());

		List<Trip> trips = tripService.getTripsByUser(targetUser);
		
		assertTrue(trips.isEmpty());
	}
	
	@Test
	void 친구의_여행목록을_가져온다() {
		TripService tripService = new TestableTripService();
		loggedInUser = REGISTERED_USER;

		User targetUser = new User();
		targetUser.addFriend(ANOTHER_USER);
		targetUser.addFriend(loggedInUser);
		targetUser.addTrip(new Trip());
		targetUser.addTrip(new Trip());
		targetUser.addTrip(new Trip());

		List<Trip> trips = tripService.getTripsByUser(targetUser);

		assertTrue(trips.size() == 3);
		
	}

	private class TestableTripService extends TripService {

		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}
		
		@Override
		protected List<Trip> tripsByUser(User user) {
			return user.trips();
		}
	}
}
