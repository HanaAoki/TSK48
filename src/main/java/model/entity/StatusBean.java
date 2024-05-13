package model.entity;


/**
 * @author 青木春菜
 */
public class StatusBean {
	private int statusCode;
	private String statusName;
	
	public StatusBean() {
		
	}

	/**
	 * @return statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode セットする statusCode
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName セットする statusName
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	

}
