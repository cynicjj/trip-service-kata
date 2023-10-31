package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
		validateAndThrow(loggedUser);

		List<Trip> tripList = new ArrayList<Trip>();
		if (user.isFriend(loggedUser)) {
			tripList = findTripsByUser(user);
		}
		
		return tripList;
	}

	private void validateAndThrow(User loggedUser) {
		if (loggedUser == null)
			throw new UserNotLoggedInException();
	}

	protected List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}
}
