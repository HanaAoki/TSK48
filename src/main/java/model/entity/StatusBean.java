package model.entity;


/**
 * @author 青木春菜
 */
public class StatusBean {
	private String statusCode;
	private String statusName;
	
	public StatusBean() {
		
	}

	/**
	 * @return statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}



	/**
	 * @param statusCode セットする statusCode
	 */
	public void setStatusCode(String statusCode) {
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
