package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.UserBean;

class UserListDAOTest {

	static List<String[]> data = new ArrayList<String[]>();
	static UserListDAO dao = new UserListDAO();
	static List<UserBean> userList = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		String f = ("userList.csv");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] list = line.split(",");
				data.add(list);
			}
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		userList = null;
	}

	@Test
	void testSelectUser() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");

		try {
			userList = dao.userList();
		} catch (SQLException | ClassNotFoundException e) {
		}

		for (int i = 0; i < userList.size(); i++) {
			assertEquals(data.get(i)[0], "\"" + userList.get(i).getPassword() + "\"");
			assertEquals(data.get(i)[1], "\"" + userList.get(i).getUserId() + "\"");
			assertEquals(data.get(i)[2], "\"" + userList.get(i).getUserName() + "\"");
		}
	}
}
