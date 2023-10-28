package com.multicampus.cleancode.tripservice;

import java.util.List;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.user.User;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}

}