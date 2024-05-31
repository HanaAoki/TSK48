package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import model.entity.UserBean;

public class UserDAOTest {
	@Test
	public void testLoginSuccess() throws SQLException, ClassNotFoundException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.login("aoki", "aoki");
		assertNotNull(user);
		assertEquals("aoki", user.getUserId());
		assertEquals("aoki", user.getPassword());
		assertEquals("青木", user.getUserName());
	}

	@Test
	public void testLoginFailure() throws SQLException, ClassNotFoundException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.login("illegalUser", "illegalPass");
		assertNull(user);
	}
}