package org.craftedsw.tripservicekata.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.craftedsw.tripservicekata.trip.UserBuilder;
import org.junit.jupiter.api.Test;

public class UserTest {

	private static final User BOB = new User();
	private static final User JOHN = new User();

	@Test
	void 친구가_아니면_false() {
		User user = UserBuilder.aUser()
				.friendsWith(BOB)
				.build();
		
		assertThat(user.isFriendWith(JOHN), is(false));	
	}
	
	@Test
	void 친구면_true() {
		User user = UserBuilder.aUser()
				.friendsWith(BOB, JOHN)
				.build();
		
		assertThat(user.isFriendWith(JOHN), is(true));	
	}
}
