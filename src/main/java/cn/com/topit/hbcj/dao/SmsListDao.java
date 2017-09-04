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
public class SmsListDao {
	/** 日志实例 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 门禁统计
		public List getSmsList(String where,String order,String limit) {
			/*
			 * private String cardNo; private String userName; private String
			 * departmentName; private int entranceCount;
			 */
			List list = null;
			String sql = "SELECT * from t_d_sms_list where 1=1 "+where +order+limit;

			// sql="SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where OperatorName='administrator' and password='123456' ";
			RowMapper<SmsList> rowMapper = new BeanPropertyRowMapper<SmsList>(
					SmsList.class);
			// System.out.println(jdbcTemplate.queryForObject("select count(1) from t_d_operator",Integer.class));

			try {
				list = jdbcTemplate.query(sql, rowMapper);
			} catch (EmptyResultDataAccessException ex) {
				logger.error("没有查询到相关记录");
			} catch (Exception e) {
				logger.error("getSmsList出错：" + e.toString());
				e.printStackTrace();
			}
			//System.out.println(list);
			return list;
		}
	//获取记录数
		public int getSmsSum(String where){
			String sql = "SELECT count(1) from t_d_sms_list where 1=1 "+where ;
			return(jdbcTemplate.queryForObject(sql, Integer.class));
		}
}
