package cn.com.topit.hbcj.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.topit.hbcj.service.DepartmentService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

@RestController
@RequestMapping(value = "/")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	/** 日志实例 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Value("#{APP_PROP['parameterError']}")	String parameterError;
	@Value("#{APP_PROP['unauthorized']}") String unauthorized;
	@CrossOrigin(origins = "*")

	@RequestMapping(value = "getDeptsByToken", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getDeptsByToken(@RequestHeader (value = "Authorization", defaultValue = "") String token)
			throws JsonProcessingException {
		if(token.equals("")){
    		return null;
    	}
		List list = departmentService.getDeptsByToken(token);		
		
		
		//数据转换
		ObjectMapper mapper = new ObjectMapper();
	
		return mapper.writeValueAsString(list);

		// 数据转换
		/*
		 * ObjectMapper mapper = new ObjectMapper(); return
		 * mapper.writeValueAsString(user);
		 */
	}
		

	
}
