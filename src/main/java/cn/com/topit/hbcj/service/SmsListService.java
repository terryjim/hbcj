package cn.com.topit.hbcj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.topit.hbcj.dao.Entrance;
import cn.com.topit.hbcj.dao.SmsListDao;


@Service
public class SmsListService {
	@Autowired
	private SmsListDao smsListDao;
	public List getSmsList(String where) {
		return smsListDao.getSmsList(where,"","");
	}
	//分页查询
	public List getSmsListByPage(int page) {
		return smsListDao.getSmsList("","order by created desc"," limit "+(page-1)*10+",10");
	}
	//获取总数
	public int getSmsSum(String where){
		return smsListDao.getSmsSum(where);
	}
}