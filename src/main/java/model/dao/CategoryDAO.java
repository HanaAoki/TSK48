package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;

/**
 * m_categoryの中身をリストに入れるDAO
 * @author 青木春菜
 */
public class CategoryDAO {

	public List<CategoryBean> selectCategory() throws ClassNotFoundException, SQLException {
		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();

		String sql = "SELECT category_id, category_name FROM m_category ORDER BY category_id ASC";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();

			// 取り出してリストに入れる
			while (res.next()) {

				CategoryBean categoryBean = new CategoryBean();

				categoryBean.setCategoryId(res.getInt("category_id"));
				categoryBean.setCategoryName(res.getString("category_name"));

				categoryList.add(categoryBean);
			}

			return categoryList;
		}
	}

}
