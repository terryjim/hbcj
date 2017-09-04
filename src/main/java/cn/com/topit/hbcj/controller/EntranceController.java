package cn.com.topit.hbcj.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.topit.hbcj.dao.Entrance;
import cn.com.topit.hbcj.service.EntranceService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/")
public class EntranceController {
	@Autowired
	private EntranceService entranceService;
	/** 日志实例 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Value("#{APP_PROP['parameterError']}")
	String parameterError;

	/*
	 * @CrossOrigin(origins = "*")
	 * 
	 * public List<Department> getDeptsByToken(String token) { List list = null;
	 * String sql =
	 * "select a.groupid as id,b.parentId,b.DepartmentName as depName from t_d_departmentpermission a,t_d_department b where a.OperatorID=(select c.operatorid from t_d_operator c where c.token=? limit 1) and a.GroupID=b.DepartmentID"
	 * ; System.out.println(sql); // sql=
	 * "SELECT OperatorName as userName,operatorId as id,operateprivilege as privilege,token,lastLogin from t_d_operator where OperatorName='administrator' and password='123456' "
	 * ; RowMapper<Department> rowMapper = new
	 * BeanPropertyRowMapper<Department>( Department.class); //
	 * System.out.println
	 * (jdbcTemplate.queryForObject("select count(1) from t_d_operator"
	 * ,Integer.class));
	 * 
	 * try { list = jdbcTemplate.query(sql, new Object[] { token }, rowMapper);
	 * } catch (EmptyResultDataAccessException ex) { logger.error("没有用户负责的部门");
	 * } catch (Exception e) { logger.error("getDeptsByUser出错：" + e.toString());
	 * e.printStackTrace(); } return list; }
	 */
	// 门禁统计（按部门、天数、阀值）
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "stat", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getStat(
			@RequestHeader(value = "Authorization", defaultValue = "") String token,
			@RequestParam(value = "depId") int depId,
			@RequestParam(value = "days") int days,
			@RequestParam(value = "threshold") int threshold)
			throws JsonProcessingException {
		if (token.equals("")) {
			return null;
		}

		List list = entranceService.getStat(depId, days, threshold);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);

	}

}
