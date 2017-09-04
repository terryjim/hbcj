package cn.com.topit.hbcj.dao;

import java.util.Date;
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
public class EntranceDao {
	/** 日志实例 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * SELECT `t_d_employinfo`.`CardNO` as cardNo,`t_d_employinfo`.`EmployName`
	 * as userName, t_d_department.`DepartmentName` as departmentName, COUNT(*)
	 * AS entranceCount FROM `t_d_validcardevent` INNER JOIN `t_d_employinfo` ON
	 * `t_d_employinfo`.`CardNO`=`t_d_validcardevent`.`CardNO` INNER JOIN
	 * `t_d_department` ON
	 * `t_d_employinfo`.`GroupID`=t_d_department.`DepartmentID` WHERE
	 * `t_d_validcardevent`.`RecordDateTime` >= '2015-07-23 00:00:00' AND
	 * `t_d_validcardevent`.`RecordDateTime` <= '2017-07-26 23:59:59' GROUP BY
	 * `t_d_employinfo`.`CardNO` HAVING entranceCount > 3
	 */
	// 门禁统计
	public List getStat(int depId, int days, int threshold) {
		/*
		 * private String cardNo; private String userName; private String
		 * departmentName; private int entranceCount;
		 */
		List list = null;
		String sql = "SELECT t_d_employinfo.CardNO as cardNo,t_d_employinfo.EmployName as userName, t_d_department.DepartmentName as departmentName, t_d_department.DepartmentID as depId, COUNT(1) AS entranceCount ";
		sql += " FROM t_d_validcardevent INNER JOIN t_d_employinfo ON t_d_employinfo.CardNO=t_d_validcardevent.CardNO ";
		sql += " INNER JOIN t_d_department ON t_d_employinfo.GroupID=t_d_department.DepartmentID ";
		sql += " WHERE t_d_validcardevent.RecordDateTime>= DATE_ADD(CURDATE(),INTERVAL -? day) AND t_d_validcardevent.RecordDateTime <= now() ";
		sql+="  AND find_in_set(t_d_department.DepartmentID, fun_get_children(?)) GROUP BY t_d_employinfo.CardNO HAVING  entranceCount < ?";

		// sql="SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where OperatorName='administrator' and password='123456' ";
		RowMapper<Entrance> rowMapper = new BeanPropertyRowMapper<Entrance>(
				Entrance.class);
		// System.out.println(jdbcTemplate.queryForObject("select count(1) from t_d_operator",Integer.class));

		try {
			list = jdbcTemplate.query(sql, new Object[] {days,depId,
					threshold }, rowMapper);
		} catch (EmptyResultDataAccessException ex) {
			logger.error("没有查询到相关记录");
		} catch (Exception e) {
			logger.error("getStat出错：" + e.toString());
			e.printStackTrace();
		}
		return list;
	}

}
