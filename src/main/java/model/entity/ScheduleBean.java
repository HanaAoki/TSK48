package model.entity;

import java.time.LocalDate;
import java.util.List;

public class ScheduleBean {
	private String userName;
	private String[] room;
	
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
	
	public void makeSchedule(String userName, List<TaskBean> taskList, LocalDate date) {
		int[] dayOfTask = new int[7];
		String[] room = new String[7];
		LocalDate limitDate = null;
		
		for(TaskBean task : taskList) {
			if(task.getUserName().equals(userName)) {
				for(int i = 0; i < 7; i++) {
					if(task.getLimitDate() != null) {
						limitDate = task.getLimitDate().toLocalDate();
						if(limitDate.isAfter(date)) {
							dayOfTask[i]++;
						}
					}
					if(task.getLimitDate() == null) {
						dayOfTask[i]++;
					}
					date = date.plusDays(1);
				}
			}
		}
		
		for(int i = 0; i < dayOfTask.length; i++) {
			switch(dayOfTask[i]) {
			case 0:
				room[i] = "〇";
				break;
			case 1:
				room[i] = "△";
				break;
			default:
				room[i] = "×";
				break;
			}
		}

		setUserName(userName);
		setRoom(room);
	}
}
