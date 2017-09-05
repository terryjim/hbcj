package cn.com.topit.hbcj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.topit.hbcj.dao.SmsStatDao;


@Service
public class SmsStatService {
	@Autowired
	private SmsStatDao smsStatDao;
	public List getSmsStat() {
		return smsStatDao.getSmsStat();
	}	
	//更新统计
	public int stat(int year,int month,int count){
		return smsStatDao.stat(year, month, count);
	}
}