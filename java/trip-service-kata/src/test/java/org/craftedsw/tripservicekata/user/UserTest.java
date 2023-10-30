package org.craftedsw.tripservicekata.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTest {

	@Test
	void testIsFriend() {
		User user = new User();
		
		User friend = new User();
		user.addFriend(friend);
		
		User notFriend = new User();
		
		assertTrue(user.isFriend(friend));
		assertFalse(user.isFriend(notFriend));
	}
}
