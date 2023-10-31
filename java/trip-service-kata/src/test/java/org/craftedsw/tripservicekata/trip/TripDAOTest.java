package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

public class TripDAOTest {

	@Test
	void testTripsByUser() {
		TripDAO dao = new TripDAO();

		assertThrows(CollaboratorCallException.class, () -> {
			dao.tripsByUser(new User());
		});
	}
}
