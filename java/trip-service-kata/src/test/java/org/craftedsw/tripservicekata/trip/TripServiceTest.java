package org.craftedsw.tripservicekata.trip;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

	private static final User JOHN = new User();
	private static final User SMITH = new User();

	private static final Trip KOREA = new Trip();
	private static final Trip USA = new Trip();

	private User loggedInUser;

	@Mock
	private TripDAO tripDAO = new TripDAO();

	@InjectMocks
	@Spy
	private TripService service = new TripService();

	@BeforeEach
	void init() {
		loggedInUser = new User();
	}

	@Test
	void 로그인_유저_없으면_예외() {
		assertThrows(UserNotLoggedInException.class, () -> {
			service.getTripsByUser(null, null);
		});
	}

	@Test
	void 친구_아니면_여행_목록_없다() {
		User notFriend = new User();

		List<Trip> trips = service.getTripsByUser(notFriend, loggedInUser);

		assertThat(trips.size(), is(0));
	}

	@Test
	void 친구면_여행_목록_가져온다() {
		User friend = UserBuilder.anUser().friends(JOHN, SMITH).trips(KOREA, USA).build();

		friend.addFriend(loggedInUser);

		given(tripDAO.tripsByUser(friend)).willReturn(friend.trips());

		List<Trip> trips = service.getTripsByUser(friend, loggedInUser);

		assertThat(trips.size(), is(2));
	}
}
