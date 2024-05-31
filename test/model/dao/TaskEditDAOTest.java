package model.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

class TaskEditDAOTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			String str = "UPDATE t_task SET task_name = '担当青木', category_id = 2, limit_date = '2024-06-30', status_code = '55', user_id = 'aoki', memo = '初代' WHERE task_id = 5";
			stmt.executeUpdate(str);
		}
	}

	@Test
	void test() {
		TaskEditDAO DAO = new TaskEditDAO();
		TaskBean tb  = new TaskBean();
		tb.setTaskId(5);
		tb.setTaskName("aa");
		tb.setCategoryId(3);
		tb.setLimitDate(null);
		tb.setUserId("aoki");
		tb.setStatusCode("55");
		tb.setMemo("初代");
		
		int count = 0;
		try {
			count = DAO.editTask(tb);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		assertEquals(1, count);
	}

}
