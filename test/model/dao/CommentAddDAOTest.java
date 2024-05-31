package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.CommentBean;

class CommentAddDAOTest {
	static CommentAddDAO dao = new CommentAddDAO();
	static CommentBean cmt = new CommentBean();
	static int taskID = 1;
	static String userId = "arino";
	static String comment = "本日は晴天なり";
	
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
		String sql = "DELETE FROM t_comment WHERE task_id = ? AND user_id = ? AND `comment` = ?";
		
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, taskID);
			pstmt.setString(2, userId);
			pstmt.setString(3, comment);
			
			pstmt.executeUpdate();
		}
	}

	@Test
	void testCommentAddDAO() {
		int count = 0;
		
		cmt.setTaskId(taskID);
		cmt.setUserId(userId);
		cmt.setComment(comment);
		
		try {
			count = dao.addTask(cmt);
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		assertEquals(1,count);
	}

}
