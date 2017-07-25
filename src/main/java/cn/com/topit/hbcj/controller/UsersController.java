package cn.com.topit.hbcj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@Value("#{APP_PROP['parameterError']}") String parameterError;
	@RequestMapping(value = "/login", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public @ResponseBody User login(			
			@RequestParam (value = "userName", defaultValue = "") String userName,
			@RequestParam (value = "password", defaultValue = "") String password
			) throws JsonProcessingException {	
		
		return usersService.getUser(userName,password);
	}
	/*@RequestMapping(value = "/user/usersInOrgs", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getUsersInOrgs(
			@PathVariable(value = "version") String version,
			@RequestHeader (value = "Authorization", defaultValue = "") String authorization,
			@RequestParam (value = "uids", defaultValue = "") String uids
			) throws JsonProcessingException {
		//验证token以及版本号
		String verify = CommonUtils.verifyToken(authorization, version);
		if(!verify.equals("")){
			return verify;
		}
		if(uids==null || uids.equals("")){
			return parameterError;
		}
		
		return usersService.getUsers(uids);
	}*/
}
