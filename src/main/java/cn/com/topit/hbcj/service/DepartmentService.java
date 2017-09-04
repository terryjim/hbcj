package cn.com.topit.hbcj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.topit.hbcj.dao.Department;
import cn.com.topit.hbcj.dao.DepartmentDao;
import cn.com.topit.hbcj.dao.User;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;

	// 根据用户查询所辖部门
	public List<Department> getDeptsByUser(User user) {
		return departmentDao.getDeptsByUser(user);
	}

	public List<Department> getDeptsByToken(String token) {
		return departmentDao.getDeptsByToken(token);
	}
}
