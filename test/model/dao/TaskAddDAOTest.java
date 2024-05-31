package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

class TaskAddDAOTest {

	static TaskAddDAO dao = new TaskAddDAO();
	static TaskBean task = new TaskBean();
	
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
		String sql = "DELETE FROM t_task WHERE task_name = ? AND category_id = ? AND limit_date = ? AND user_id = ? AND status_code = ? AND memo = ?";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, task.getTaskName());
			pstmt.setInt(2, task.getCategoryId());
			pstmt.setDate(3, task.getLimitDate());
			pstmt.setString(4, task.getUserId());
			pstmt.setString(5, task.getStatusCode());
			pstmt.setString(6, task.getMemo());
			
			pstmt.executeUpdate();
		}
	}

	@Test
	void test() {
		task.setTaskName("テスト用タスク");
		task.setCategoryId(1);
		task.setLimitDate(Date.valueOf("2024-07-31"));
		task.setUserId("arino");
		task.setStatusCode("00");
		task.setMemo("テスト用です。");
		
		int count = 0;
		
		try {
			count = dao.addTask(task);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		assertEquals(1,count);
	}

}
