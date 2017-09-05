package cn.com.topit.hbcj.dao;

import java.util.Date;

public class SmsStat {
	private long id;
	private int statYear;
	private int statMonth;
	private int count;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStatYear() {
		return statYear;
	}
	public void setStatYear(int statYear) {
		this.statYear = statYear;
	}
	public int getStatMonth() {
		return statMonth;
	}
	public void setStatMonth(int statMonth) {
		this.statMonth = statMonth;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
