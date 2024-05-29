package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.CommentBean;

class CommentListDAOTest {
	
	static List<String[]> data = new ArrayList<String[]>();
	static CommentListDAO dao = new CommentListDAO();
	static List<CommentBean> commentList = new ArrayList<CommentBean>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;
		
		try {
			fi = new FileInputStream("commentList.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);
			
			String line;
			
			int i = 0;
			
			while((line = br.readLine()) != null) {
				if(i > 0) {
					
					String[] list = line.split(",");
					System.out.println();
					data.add(list);
				}
				i++;
			}
		}catch(Exception e) {
		}finally {
			try {
				br.close();
			}catch (Exception e) {
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
		commentList = null;
	}

	@Test
	void testGetCommentList() {
		try {
			commentList = dao.selectCommentList(1);
		}catch(SQLException | ClassNotFoundException e){
		}
		
		for (int i = 0; i < commentList.size(); i++) {
			assertEquals(Integer.parseInt(data.get(i)[0]), commentList.get(i).getCommentId());
			assertEquals(Integer.parseInt(data.get(i)[1]), commentList.get(i).getTaskId());
			assertEquals(data.get(i)[2], "\"" + commentList.get(i).getUserId() + "\"");
			assertEquals(data.get(i)[3], "\"" + commentList.get(i).getUserName() + "\"");
			assertEquals(data.get(i)[4], "\"" + commentList.get(i).getComment() + "\"");
			assertEquals(data.get(i)[5], "\"" + commentList.get(i).getTaskName() + "\"");
		}
	}

}
