package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {
	
	@Autowired
	private TripDAO tripDao;

	public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
		validate(loggedInUser);

		return user.isFriendWith(loggedInUser)
				? tripsBy(user)
				: noTrips();
	}

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	private void validate(User loggedInUser) {
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	private List<Trip> tripsBy(User user) {
		return tripDao.tripsBy(user);
	}
}