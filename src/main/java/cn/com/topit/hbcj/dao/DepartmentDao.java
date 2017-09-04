package cn.com.topit.hbcj.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {
	/** 日志实例 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * // 根据token信息查询用户 public User getUser(String token) { String sql =
	 * "SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where token='"
	 * + token + "'"; RowMapper<User> rowMapper = new
	 * BeanPropertyRowMapper<User>(User.class); User user =
	 * jdbcTemplate.queryForObject(sql, rowMapper, new Object[] { token });
	 * return user; }
	 */
	// 查询用户所负责部门
	public List<Department> getDeptsByUser(User user) {
		String sql = "select a.groupid as id,b.parentId,b.DepartmentName as depName from t_d_departmentpermission a,t_d_department b where a.OperatorID=? and a.GroupID=b.DepartmentID";
		// sql="SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where OperatorName='administrator' and password='123456' ";
		RowMapper<Department> rowMapper = new BeanPropertyRowMapper<Department>(
				Department.class);
		// System.out.println(jdbcTemplate.queryForObject("select count(1) from t_d_operator",Integer.class));
		List list = null;
		try {
			list = jdbcTemplate.query(sql, rowMapper,
					new Object[] { user.getId() });
		} catch (EmptyResultDataAccessException ex) {
			logger.error("没有用户负责的部门");
		} catch (Exception e) {
			logger.error("getDeptsByUser出错：" + e.toString());
			e.printStackTrace();
		}
		return list;
	}

	// 查询用户所负责部门
	public List<Department> getDeptsByToken(String token) {
		List list = null;
		String sql = "select a.groupid as id,b.parentId,b.DepartmentName as depName from t_d_departmentpermission a,t_d_department b where a.OperatorID=(select c.operatorid from t_d_operator c where c.token=? limit 1) and a.GroupID=b.DepartmentID";
		System.out.println(sql);
		// sql="SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where OperatorName='administrator' and password='123456' ";
		RowMapper<Department> rowMapper = new BeanPropertyRowMapper<Department>(
				Department.class);
		// System.out.println(jdbcTemplate.queryForObject("select count(1) from t_d_operator",Integer.class));

		try {
			list = jdbcTemplate.query(sql, new Object[] { token }, rowMapper);
		} catch (EmptyResultDataAccessException ex) {
			logger.error("没有用户负责的部门");
		} catch (Exception e) {
			logger.error("getDeptsByUser出错：" + e.toString());
			e.printStackTrace();
		}
		return list;
	}

}
