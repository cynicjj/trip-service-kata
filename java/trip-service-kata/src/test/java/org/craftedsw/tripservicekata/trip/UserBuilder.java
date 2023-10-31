package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

public class UserBuilder {

	public static UserBuilder anUser() {
		return new UserBuilder();
	}

	private User[] friends;
	private Trip[] trips;

	public UserBuilder friends(User... users) {
		this.friends = users;
		return this;
	}

	public UserBuilder trips(Trip... trips) {
		this.trips = trips;
		return this;
	}

	public User build() {
		User user = new User();
		
		for(User friend : friends)
			user.addFriend(friend);
		
		for(Trip trip : trips)
			user.addTrip(trip);
		
		return user;
	}

}
