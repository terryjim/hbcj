package cn.com.topit.hbcj.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.topit.hbcj.dao.Entrance;
import cn.com.topit.hbcj.dao.EntranceDao;


@Service
public class EntranceService {
	@Autowired
	private EntranceDao entranceDao;

	/*public List<Entrance> getStat(Date start, Date end, String token,
			int threshold) {
		return entranceDao.getStat(start, end, token, threshold);
	}*/
	public List<Entrance> getStat(int depId,int days,
			int threshold) {
		return entranceDao.getStat(depId,days, threshold);
	}
}