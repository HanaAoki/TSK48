package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CommentBean;


/**
 * コメントをリストに格納し返すクラス
 * @author Arino
 */
public class CommentListDAO{
	
	/**
	 * @param taskId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<CommentBean> selectCommentList(int taskId) throws SQLException, ClassNotFoundException{
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		String sql = "SELECT t1.comment_id, t1.task_id, t2.user_id, t2.user_name, t1.comment, t3.task_name "
				+ "FROM t_comment t1 "
				+ "JOIN m_user t2 ON t1.user_id = t2.user_id "
				+ "JOIN t_task t3 ON t1.task_id = t3.task_id "
				+ "WHERE t1.task_id = ?";
		
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, taskId);
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				CommentBean comment = new CommentBean();
				comment.setCommentId(res.getInt("t1.comment_id"));
				comment.setTaskId(res.getInt("t1.task_id"));
				comment.setTaskName(res.getString("t3.task_name"));
				comment.setUserId(res.getString("t2.user_id"));
				comment.setUserName(res.getString("t2.user_name"));
				comment.setComment(res.getString("t1.comment"));
				
				commentList.add(comment);
			}
		}
		
		return commentList;
	}
}
