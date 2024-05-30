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

class CommentDeleteDAOTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			String sql = "DROP TABLE t_comment";
			String sql2 = "CREATE TABLE t_comment(comment_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, task_id INT NOT NULL, FOREIGN KEY (task_id) REFERENCES t_task(task_id), user_id VARCHAR (24) NOT NULL, FOREIGN KEY (user_id) REFERENCES m_user(user_id), `comment` VARCHAR (100) NOT NULL, update_datetime TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT current_timestamp)";
			String sql3 = "INSERT INTO tsk48.t_comment(task_id, user_id, `comment`) VALUES (1, 'comsize', 'テストコメント'), (1, 'tanaka', 'hi'), (1, 'arino', 'SO RE NA'), (1, 'shige', 'テストコメント'), (1, 'aoki', 'hi'), (2, 'comsize', 'テストコメント'), (2, 'tanaka', 'hi'), (2, 'arino', 'SO RE NA'), (2, 'shige', 'テストコメント'), (2, 'aoki', 'hi'), (3, 'comsize', 'テストコメント'), (3, 'tanaka', 'hi'), (3, 'arino', 'SO RE NA'), (3, 'arino', 'SO RE NA!'), (3, 'arino', 'SO RE NA!!'), (3, 'shige', 'テストコメント'), (3, 'aoki', 'hi'), (4, 'comsize', 'テストコメント'), (4, 'tanaka', 'hi'), (4, 'arino', 'SO RE NA'), (4, 'shige', 'テストコメント'), (4, 'shige', 'テストコメント!'), (4, 'shige', 'テストコメント!!'), (4, 'aoki', 'hi'), (1, 'comsize', 'テストコメント'), (5, 'tanaka', 'hi'), (5, 'arino', 'SO RE NA'), (5, 'shige', 'テストコメント'), (5, 'aoki', 'hi'), (5, 'aoki', 'hi!'), (5, 'aoki', 'hi!!')";
			
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
		}
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		CommentDeleteDAO cdDAO = new CommentDeleteDAO();
		
		int count = 0;
		int[] arr = {1, 2};
		try {
			count = cdDAO.deleteComment(arr);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		assertEquals(2, count);
	}

}
