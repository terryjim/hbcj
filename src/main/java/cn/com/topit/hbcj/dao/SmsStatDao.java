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
public class SmsStatDao {
	/** 日志实例 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 短信统计数据
	public List getSmsStat(String where, String order, String limit) {
		/*
		 * private String cardNo; private String userName; private String
		 * departmentName; private int entranceCount;
		 */
		List list = null;
		String sql = "SELECT * from t_d_sms_stat where 1=1 " + where + order
				+ limit;

		// sql="SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where OperatorName='administrator' and password='123456' ";
		RowMapper<SmsStat> rowMapper = new BeanPropertyRowMapper<SmsStat>(
				SmsStat.class);
		// System.out.println(jdbcTemplate.queryForObject("select count(1) from t_d_operator",Integer.class));

		try {
			list = jdbcTemplate.query(sql, rowMapper);
		} catch (EmptyResultDataAccessException ex) {
			logger.error("没有查询到相关记录");
		} catch (Exception e) {
			logger.error("getSmsList出错：" + e.toString());
			e.printStackTrace();
		}
		// System.out.println(list);
		return list;
	}

	public List getSmsStat() {
		return getSmsStat("", "order by stat_year desc,stat_month desc", "");
	}

	// 插入或修改统计值，返回修改记录数
	public int stat(int year, int month, int count) {
		String sql = "update t_d_sms_stat set count=count+" + count
				+ " where stat_year=" + year + " and stat_month=" + month;
		int ret = jdbcTemplate.update(sql);
		if (ret < 1) {
			sql = "insert into t_d_sms_stat(stat_year,stat_month,count) values("
					+ year + "," + month + "," + count + ")";
			ret = jdbcTemplate.update(sql);
		}
		return ret;
	}
}
