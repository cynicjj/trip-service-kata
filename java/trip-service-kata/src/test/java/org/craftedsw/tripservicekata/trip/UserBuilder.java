package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

public class UserBuilder {

	public static UserBuilder aUser() {
		return new UserBuilder();
	}

	private User[] friends = new User[] {};
	private Trip[] trips = new Trip[] {};

	public UserBuilder friendsWith(User... friends) {
		this.friends = friends;
		return this;
	}

	public UserBuilder tripsWith(Trip... trips) {
		this.trips = trips;
		return this;
	}

	public User build() {
		User user = new User();
		addFriendsTo(user);
		addTripsTo(user);
		return user;
	}

	private void addTripsTo(User user) {
		for (Trip trip : trips) {
			user.addTrip(trip);
		}
	}

	private void addFriendsTo(User user) {
		for (User friend : friends) {
			user.addFriend(friend);
		}
	}
}
