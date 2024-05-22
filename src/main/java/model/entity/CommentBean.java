package model.entity;

/**
 * @author 青木春菜
 */
public class CommentBean {
	private int commentId;
	private int taskId;
	private String userId;
	private String userName;
	private String comment;
	
	public CommentBean() {
		
	}

	/**
	 * @return commentId
	 */
	public int getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId セットする commentId
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return taskId
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId セットする taskId
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment セットする comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
