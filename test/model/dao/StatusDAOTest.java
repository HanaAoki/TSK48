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

import model.entity.StatusBean;

class StatusDAOTest {
	
	static List<String[]> data = new ArrayList<String[]>();
	static StatusBean status = new StatusBean();
	static StatusDAO dao = new StatusDAO();
	static List<StatusBean> statusList = null;
	static List<StatusBean> defaultStatusList = new ArrayList<StatusBean>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		String f = ("tsk48.m_status.csv");
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)))){
			String line;
			br.readLine();
			while((line = br.readLine()) != null) {
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
		statusList = null;
		defaultStatusList.clear();
	}

	@Test
	void test() {
		try {
			statusList = dao.selectStatus();
		}catch(SQLException | ClassNotFoundException e) {
		}
		
		for(int i = 0; i < data.size(); i++) {
			assertEquals(data.get(i)[0],"\"" + statusList.get(i).getStatusCode() + "\"");
			assertEquals(data.get(i)[1],"\"" + statusList.get(i).getStatusName() + "\"");
		}
	}

}
