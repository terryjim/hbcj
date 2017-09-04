package cn.com.topit.hbcj.controller;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.topit.hbcj.dao.User;
import cn.com.topit.hbcj.service.UsersService;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/")
public class UsersController {
	@Autowired
	private UsersService usersService;
	/** 日志实例 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Value("#{APP_PROP['parameterError']}")
	String parameterError;
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String login(@RequestBody JSONObject jsonObj)
			throws JsonProcessingException {
		
		User user = usersService.getUser(jsonObj.getString("userName"), jsonObj.getString("password"));
		String token = "";
		if (user != null) {
			if (user.getToken() == null || user.getToken().equals(""))
				token = usersService.updateToken(user.getId());
			else
				token = user.getToken();
		}
		return "{\"token\":\"" + token + "\"}";

		// 数据转换
		/*
		 * ObjectMapper mapper = new ObjectMapper(); return
		 * mapper.writeValueAsString(user);
		 */
	}
}
