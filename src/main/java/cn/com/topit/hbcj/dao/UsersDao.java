package cn.com.topit.hbcj.dao;

import java.security.MessageDigest;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.com.topit.utils.StringUtils;

@Repository
public class UsersDao {
	/** 日志实例 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 根据token信息查询用户
	public User getUser(String token) {
		String sql = "SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where token='"
				+ token + "'";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper,
				new Object[] { token });
		return user;
	}

	// 登录
	public User getUser(String userName, String password) {
		String sql = "SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where OperatorName=? and password=?";
		// sql="SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where OperatorName='administrator' and password='123456' ";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		// System.out.println(jdbcTemplate.queryForObject("select count(1) from t_d_operator",Integer.class));
		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, rowMapper, new Object[] {
					userName, password });
		} catch(EmptyResultDataAccessException ex){
			logger.error("用户名密码不对");
		}catch (Exception e){
			logger.error("getUser(String userName, String password) 出错："
					+ e.toString());
			 e.printStackTrace();
		}
		return user;
	}

	// 更新token
	public String updateToken(Long id) {
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		String sql = "update t_d_operator set token=? where operatorId=?";
		int ret = jdbcTemplate.update(sql, new Object[] { token, id });
		if (ret != 1)
			return null;
		else
			return token;
	}
	//////////////////////////////////////////////////////////SMS////////////////////////////////////////////////////////
	// SMS-根据token信息查询用户
		public User getSmsUser(String token) {
			String sql = "SELECT name as userName,id,token,lastLogin from t_d_sms_user where token='"
					+ token + "'";
			RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
			User user = jdbcTemplate.queryForObject(sql, rowMapper,
					new Object[] { token });
			return user;
		}

		// SMS-登录
		public User getSmsUser(String userName, String password) {				
			String sql = "SELECT name as userName,id,token,lastLogin from t_d_sms_user where name=? and password=?";
			// sql="SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where OperatorName='administrator' and password='123456' ";
			RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
			// System.out.println(jdbcTemplate.queryForObject("select count(1) from t_d_operator",Integer.class));
			User user = null;
			try {
				user = jdbcTemplate.queryForObject(sql, rowMapper, new Object[] {
						userName, StringUtils.md5("topit"+password) });
			} catch(EmptyResultDataAccessException ex){
				logger.error("用户名密码不对");
			}catch (Exception e){
				logger.error("getUser(String userName, String password) 出错："
						+ e.toString());
				 e.printStackTrace();
			}
			return user;
		}

		// SMS-更新token
		public String updateSmsToken(Long id) {
			UUID uuid = UUID.randomUUID();
			String token = uuid.toString();
			String sql = "update t_d_sms_user set token=? where id=?";
			int ret = jdbcTemplate.update(sql, new Object[] { token, id });
			if (ret != 1)
				return null;
			else
				return token;
		}
		//更改sms_user密码	
		public int chgPwd(String userName,String newPwd) {		
			String sql = "update t_d_sms_user set password=? where name=?";
			return jdbcTemplate.update(sql, new Object[] { newPwd, userName });			
		}
}
