package model.entity;

public class ScheduleBean {
	private String userName;
	private String[] room ;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String[] getRoom() {
		return room;
	}
	public void setRoom(String[] room) {
		this.room = room;
	}
}
