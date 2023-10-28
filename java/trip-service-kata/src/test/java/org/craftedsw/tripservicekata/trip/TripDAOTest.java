package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

public class TripDAOTest {

	@Test
	void 테스트에서_호출하면_예외를_던짐() {
		assertThrows(CollaboratorCallException.class, () -> {
			new TripDAO().tripsBy(new User());
		});
	}
}
