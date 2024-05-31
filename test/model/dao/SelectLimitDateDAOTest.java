package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SelectLimitDateDAOTest {
	static List<String[]> data = new ArrayList<String[]>();
	static SelectLimitDateDAO dao = new SelectLimitDateDAO();
	static List<LocalDate> limitDateList = null;
	static List<LocalDate> defaultLimitDateList = new ArrayList<LocalDate>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		String f = ("aoki-limt_date.csv");
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
		limitDateList = null;
		defaultLimitDateList.clear();
	}

	@Test
	void test() {

		try {
			limitDateList = dao.selectLimitDate("aoki");
		}catch(SQLException | ClassNotFoundException e) {
		}
		
		for(int i = 0; i < data.size(); i++) {
			assertEquals(LocalDate.parse(data.get(i)[0], DateTimeFormatter.ofPattern("yyyy/MM/dd")),limitDateList.get(i));
		}
	}

}
