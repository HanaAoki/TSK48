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

import model.entity.CategoryBean;

class CategoryDAOTest {

	static List<String[]> data = new ArrayList<String[]>();
	static CategoryBean category = new CategoryBean();
	static CategoryDAO dao = new CategoryDAO();
	static List<CategoryBean> categoryList = null;
	static List<CategoryBean> defaultCategoryList = new ArrayList<CategoryBean>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		String f = ("m_category.csv");
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
		categoryList = null;
		defaultCategoryList.clear();
	}

	@Test
	void testCategoryDAOTest() {

		try {
			categoryList = dao.selectCategory();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < data.size(); i++) {
			assertEquals(Integer.parseInt(data.get(i)[0]),categoryList.get(i).getCategoryId());
			assertEquals(data.get(i)[1],"\"" + categoryList.get(i).getCategoryName() + "\"");
		}
	}
}
