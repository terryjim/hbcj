package cn.com.topit.hbcj.dao;

import java.util.Date;

public class Entrance {
	private String cardNo;
	private String userName;
	private String departmentName;
	private int entranceCount;
	private long depId;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getEntranceCount() {
		return entranceCount;
	}

	public void setEntranceCount(int entranceCount) {
		this.entranceCount = entranceCount;
	}

	public long getDepId() {
		return depId;
	}

	public void setDeptId(long depId) {
		this.depId = depId;
	}

}
