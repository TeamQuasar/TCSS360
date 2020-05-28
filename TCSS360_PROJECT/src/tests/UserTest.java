package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import objects.User;
import info.UserSettings;

class UserTest {

	@Test
	void testUserStringStringBoolean() {
		User user = new User("Test", "Pass", true);
		assertNotNull(user);
		assertTrue(user.getAdminStat());
	}

	@Test
	void testUserStringString() {
		User user = new User("Test", "Pass");
		assertNotNull(user);
		assertFalse(user.getAdminStat());
	}

	@Test
	void testGetAdminStat() {
		User user1 = new User("Test", "Pass");
		User user2 = new User("Test", "Pass", true);
		assertTrue(user2.getAdminStat());
		assertFalse(user1.getAdminStat());
	}

	@Test
	void testVerifyCredentials() {
		User user = new User("Test", "Pass");
		assertTrue(user.verifyCredentials("Test", "Pass"));
		assertFalse(user.verifyCredentials("NotTest", "NotPass"));
		assertFalse(user.verifyCredentials("test", "pass"));
		assertFalse(user.verifyCredentials("Test", "notPass"));
	}

//	Not implemented yet.
//	@Test
//	void testGetSettings() {
//		
//	}

}
