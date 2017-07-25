package cn.com.topit.hbcj.dao;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User getUser(String token) {
		String sql = "SELECT OperatorName as userName,operatorId as id,privilege,token,lastLogin from t_d_operator where token='"
				+ token + "'";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper,
				new Object[] { token });
		return user;
	}

	public User getUser(String userName, String password) {
		String sql = "SELECT OperatorName as userName,operatorId as id,privilege,token,lastLogin from t_d_operator where OperatorName=? and password=?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, new Object[] {
				userName, password });
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
}
