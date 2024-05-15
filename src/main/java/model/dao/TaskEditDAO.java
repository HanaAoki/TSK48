package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;


public class TaskEditDAO {

	public int edittask(TaskBean confirm)
			throws SQLException, ClassNotFoundException {

		int count = 0;

		String sql = "UPDATE t_task "
				+ "SET item_name = ?, category_code = ?, price = ? "
				+ "WHERE item_code = ? ";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			String itemname = confirm.getItemName();
			int categorycode = confirm.getCategorycode();
			int price = confirm.getPrice();
			int itemcode = confirm.getItemCode();

			pstmt.setString(1, itemname);
			pstmt.setInt(2, categorycode);
			pstmt.setInt(3, price);
			pstmt.setInt(4, itemcode);

			count = pstmt.executeUpdate();
		}

		return count;

	}
}
