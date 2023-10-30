package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();

		User loggedUser = getLoggedUser();

		validateAndThrow(loggedUser);

//		boolean isFriend = user.isFriend(loggedUser);
		
		boolean isFriend = false;
		for (User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				isFriend = true;
				break;
			}
		}

		if (isFriend) {
			tripList = findTripByUser(user);
		}

		return tripList;
	}

	private void validateAndThrow(User loggedUser) {
		if (loggedUser == null)
			throw new UserNotLoggedInException();
	}

	protected List<Trip> findTripByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}
}
