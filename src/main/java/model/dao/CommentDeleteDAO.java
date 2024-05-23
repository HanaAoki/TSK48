package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * コメントを削除するためのdaoです。
 * @author 青木春菜
 */
public class CommentDeleteDAO {
	
	public int deleteComment(int[] commentId)throws SQLException, ClassNotFoundException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM t_comment WHERE comment_id = ?");
		for(int i = 1; i < commentId.length; i++) {
			sql.append(" OR comment_id = ?");
		}
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString())){
			
			for(int i = 0; i < commentId.length; i++) {
				pstmt.setInt(i+1, commentId[i]);
			}
			
			return pstmt.executeUpdate();
		}
	}

}
