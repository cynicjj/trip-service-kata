package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {
	
	@Autowired
	private TripDAO tripDAO;

	public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
		validateAndThrow(loggedInUser);

		List<Trip> tripList = new ArrayList<Trip>();
		if (user.isFriend(loggedInUser)) {
			tripList = findTripsByUser(user);
		}

		return tripList;
	}

	private void validateAndThrow(User loggedUser) {
		if (loggedUser == null)
			throw new UserNotLoggedInException();
	}

	protected List<Trip> findTripsByUser(User user) {
		return tripDAO.tripsByUser(user);
	}
}
