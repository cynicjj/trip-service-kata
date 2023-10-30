package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

	public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
		validateAndThrow(loggedInUser);

		List<Trip> tripList = new ArrayList<Trip>();
		if (user.isFriend(loggedInUser)) {
			tripList = findTripByUser(user);
		}

		return tripList;
	}

	private void validateAndThrow(User loggedInUser) {
		if (loggedInUser == null)
			throw new UserNotLoggedInException();
	}

	protected List<Trip> findTripByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}
}
