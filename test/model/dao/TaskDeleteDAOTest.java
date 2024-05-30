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

class TaskDeleteDAOTest {

	static TaskDeleteDAO dao = new TaskDeleteDAO();
	
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
		String taskSql = "INSERT INTO t_task(task_id,task_name,category_id,limit_date,user_id,status_code,memo) VALUES (?,?,?,?,?,?,?)";
		String cmtSql = "INSERT INTO t_comment(comment_id,user_id,task_id,comment) VALUES (?,?,?,?)";
		
		Object[][] insertTask = {{1,"なにかしら",1,"2024/06/30","comsize","50","なにか"},{2,"どこかしら",1,"2024/07/20","tanaka","50","あれ"}};
		Object[][] insertCmt = 	{{1, "comsize",1, "テストコメント"},{2,"tanaka",1, "hi"},{3, "arino",1, "SO RE NA"},{4, "shige",1, "テストコメント"},{5, "aoki",1, "hi"},
								{6, "comsize",2, "テストコメント"},{7, "tanaka",2, "hi"},{8, "arino",2, "SO RE NA"},{9, "shige",2, "テストコメント"},{10, "aoki",2, "hi"}};

		try(Connection con = ConnectionManager.getConnection()){
			try(PreparedStatement pstmt = con.prepareStatement(taskSql)){
				for(int i = 0; i < insertTask.length; i++) {
					pstmt.setInt(1, (int)insertTask[i][0]);
					pstmt.setString(2,(String)insertTask[i][1]);
					pstmt.setInt(3, (int)insertTask[i][2]);
					pstmt.setString(4,(String)insertTask[i][3]);
					pstmt.setString(5,(String)insertTask[i][4]);
					pstmt.setString(6,(String)insertTask[i][5]);
					pstmt.setString(7,(String)insertTask[i][6]);
					pstmt.addBatch();
				}
				pstmt.executeBatch();
			}
			try(PreparedStatement pstmt = con.prepareStatement(cmtSql)){
				for(int i = 0; i < insertCmt.length; i++) {
					pstmt.setInt(1, (int)insertCmt[i][0]);
					pstmt.setString(2,(String)insertCmt[i][1]);
					pstmt.setInt(3, (int)insertCmt[i][2]);
					pstmt.setString(4, (String)insertCmt[i][3]);
					pstmt.addBatch();
				}
				pstmt.executeBatch();
			}
		}
	}

	@Test
	void testTaskDeleteDAO() {
		String[] taskId = {"1","2"};
		int count = 0;
		
		try {
			count = dao.deleteTask(taskId);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(2,count);
	}
}
