package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.Provider.Service;
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

	private User loggedInUser;
	
	private TestableTripService service;
	
	private class TestableTripService extends TripService {
		@Override
		protected User getLoggedUser() {
			return loggedInUser;
		}

		@Override
		protected List<Trip> findTripsByUser(User user) {
			return user.trips();
		}
	}

	@BeforeEach
	void init() {
		loggedInUser = new User();
		service = new TestableTripService();
	}
	
	@Test
	void 로그인_유저_없으면_예외() {
		loggedInUser = null;
		
		assertThrows(UserNotLoggedInException.class, () -> {
			service.getTripsByUser(null);
		});
	}

	@Test
	void 친구_아니면_여행_목록_없다() {
		User notFriend = new User();
		
		List<Trip> trips = service.getTripsByUser(notFriend);
		
		assertThat(trips.size(), is(0));
	}
	
	@Test
	void 친구면_여행_목록_가져온다() {
		User friend = new User();
		friend.addTrip(new Trip());
		friend.addTrip(new Trip());

		friend.addFriend(loggedInUser);
		
		List<Trip> trips = service.getTripsByUser(friend);
		
		assertThat(trips.size(), is(2));
	}
}
