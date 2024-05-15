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
	
	public List<CategoryBean> selectCategory () throws ClassNotFoundException, SQLException {
//		返すリストの作成
		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		
//		sql文
		String sql = "SELECT category_id, category_name FROM m_category";
		
//		db接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
//			実行
			ResultSet res = pstmt.executeQuery();
			
//			取り出してリストに入れる
			while (res.next()) {
				
//				categorybeanのインスタンス化
				CategoryBean categoryBean = new CategoryBean();
				
				categoryBean.setCategoryId(res.getInt("category_id"));
				categoryBean.setCategoryName(res.getString("category_name"));
				
				categoryList.add(categoryBean);
			}
			
//			テスト
			for (CategoryBean categoryBean : categoryList) {
				System.out.println(categoryBean);
			}
			
//			リストを返す
			return categoryList;
		}
	}

}
