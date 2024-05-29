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

import model.entity.TaskBean;

class TaskListDAOTest {

	static List<String[]> data = new ArrayList<String[]>();
	static TaskListDAO dao = new TaskListDAO();
	static List<TaskBean> taskList = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;
		
		try {
			fi = new FileInputStream("taskList.csv");
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
		taskList = null;
	}

	@Test
	void testSelectAllTask() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		
		try {
			taskList = dao.selectAllTask();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < taskList.size(); i++) {
			assertEquals(Integer.parseInt(data.get(i)[0]), taskList.get(i).getTaskId());
			assertEquals(data.get(i)[1], "\"" + taskList.get(i).getTaskName() + "\"");
			assertEquals(Integer.parseInt(data.get(i)[2]), taskList.get(i).getCategoryId());
			assertEquals(data.get(i)[3], "\"" + taskList.get(i).getCategoryName() + "\"");
			assertEquals(data.get(i)[4], dateformat.format(taskList.get(i).getLimitDate()));
			assertEquals(data.get(i)[5], "\"" + taskList.get(i).getUserId() + "\"");
			assertEquals(data.get(i)[6], "\"" + taskList.get(i).getUserName() + "\"");
			assertEquals(data.get(i)[7], "\"" + taskList.get(i).getStatusCode() + "\"");
			assertEquals(data.get(i)[8], "\"" + taskList.get(i).getStatusName() + "\"");
			assertEquals(data.get(i)[9], "\"" + taskList.get(i).getMemo() + "\"");
			assertEquals(Integer.parseInt(data.get(i)[10]), taskList.get(i).getCommentNum());
		}
	}
	
	@Test
	void testSelectSomeTask() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		
		int n = 0;
		
		try {
			taskList = dao.selectSomeTask();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < taskList.size(); i++) {
			assertEquals(Integer.parseInt(data.get(i)[0]), taskList.get(i).getTaskId());
			assertEquals(data.get(i)[1], "\"" + taskList.get(i).getTaskName() + "\"");
			assertEquals(Integer.parseInt(data.get(i)[2]), taskList.get(i).getCategoryId());
			assertEquals(data.get(i)[3], "\"" + taskList.get(i).getCategoryName() + "\"");
			assertEquals(data.get(i)[4], dateformat.format(taskList.get(i).getLimitDate()));
			assertEquals(data.get(i)[5], "\"" + taskList.get(i).getUserId() + "\"");
			assertEquals(data.get(i)[6], "\"" + taskList.get(i).getUserName() + "\"");
			assertEquals(data.get(i)[7], "\"" + taskList.get(i).getStatusCode() + "\"");
			assertEquals(data.get(i)[8], "\"" + taskList.get(i).getStatusName() + "\"");
			assertEquals(data.get(i)[9], "\"" + taskList.get(i).getMemo() + "\"");
			assertEquals(Integer.parseInt(data.get(i)[10]), taskList.get(i).getCommentNum());
		}
	}
	
	@Test
	void testSelectSomeTaskNoVoid() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");

		for (int n = 0; n <= 1; n++) {
			try {
				taskList = dao.selectSomeTask(n);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			for (int i = n * 2; i < 2; i++) {
				assertEquals(Integer.parseInt(data.get(i)[0]), taskList.get(i).getTaskId());
				assertEquals(data.get(i)[1], "\"" + taskList.get(i).getTaskName() + "\"");
				assertEquals(Integer.parseInt(data.get(i)[2]), taskList.get(i).getCategoryId());
				assertEquals(data.get(i)[3], "\"" + taskList.get(i).getCategoryName() + "\"");
				assertEquals(data.get(i)[4], dateformat.format(taskList.get(i).getLimitDate()));
				assertEquals(data.get(i)[5], "\"" + taskList.get(i).getUserId() + "\"");
				assertEquals(data.get(i)[6], "\"" + taskList.get(i).getUserName() + "\"");
				assertEquals(data.get(i)[7], "\"" + taskList.get(i).getStatusCode() + "\"");
				assertEquals(data.get(i)[8], "\"" + taskList.get(i).getStatusName() + "\"");
				assertEquals(data.get(i)[9], "\"" + taskList.get(i).getMemo() + "\"");
				assertEquals(Integer.parseInt(data.get(i)[10]), taskList.get(i).getCommentNum());
			}
		}
	}

}
