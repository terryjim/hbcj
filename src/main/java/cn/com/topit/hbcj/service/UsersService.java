package cn.com.topit.hbcj.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import cn.com.topit.hbcj.dao.User;
import cn.com.topit.hbcj.dao.UsersDao;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class UsersService {
	@Autowired
	private UsersDao usersDao;

	public User getUser(String token) {		
		return usersDao.getUser(token);
	}

	public User getUser(String userName, String password) {		
		return usersDao.getUser(userName, password);
	}

	// 更新token
	public String updateToken(Long id) {
		return usersDao.updateToken(id);
	}	
}
