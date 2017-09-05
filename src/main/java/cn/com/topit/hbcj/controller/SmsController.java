package cn.com.topit.hbcj.controller;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.com.topit.hbcj.service.SmsListService;
import cn.com.topit.hbcj.service.SmsStatService;
import cn.com.topit.hbcj.service.UsersService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/sms/")
public class SmsController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private SmsListService smsListService;
	@Autowired
	private SmsStatService smsStatService;
	/** 日志实例 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Value("#{APP_PROP['parameterError']}")
	String parameterError;
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String login(@RequestBody JSONObject jsonObj)
			throws JsonProcessingException {
		
		User user = usersService.getSmsUser(jsonObj.getString("userName"), jsonObj.getString("password"));
		String token = "";
		if (user != null) {
			if (user.getToken() == null || user.getToken().equals(""))
				token = usersService.updateSmsToken(user.getId());
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

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "sendSms", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String sendSms(@RequestBody JSONObject jsonObj)
			throws JsonProcessingException {		
		String mobile = jsonObj.getString("tels");
		String content = jsonObj.getString("content");
		String smsUrl = "http://www.oa-sms.com/sendSms.action";
		/*
		 * String keyvalues =
		 * "corpAccount=whtt&userAccount=admin&pwd=d676908756e25ce58c2bbf5725f9f54e&mobile="
		 * + mobile + "&content=" + content + "【湖北城市建设职业技术学院】";
		 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("corpAccount", "whtt");
		map.put("userAccount", "admin");
		map.put("pwd", "365874fc6a12dc4c8b4e08d00e78db78");
		map.put("mobile", mobile);
		map.put("content", content);
		//String ret = CommonUtils.sendPost(smsUrl, map, "UTF-8");
		String ret="1#1";
		if(ret.equals("1#1")){
			//发送成功更新统计信息
			int count=mobile.split(",").length;
			Calendar c = Calendar.getInstance();
			smsStatService.stat(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1, count);
		}
		// http://www.oa-sms.com/sendSms.action?corpAccount=whtt&mobile=18971685188&userAccount=admin&pwd=d676908756e25ce58c2bbf5725f9f54e&content=东区宿舍出现异常情况,请及时处理
		// 【湖北城建】
		System.out.println(ret);
		return ret;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getSmsList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getSmsList(@RequestBody JSONObject jsonObj)
			throws JsonProcessingException {		
		int page = jsonObj.getInt("page");
		List list=smsListService.getSmsListByPage(page);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getSmsSum", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getSmsSum()
			throws JsonProcessingException {		
		int sum=smsListService.getSmsSum("");		
		return "{\"sum\":"+sum+"}";
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "getSmsStat", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getSmsStat()
			throws JsonProcessingException {		
		List list=smsStatService.getSmsStat();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "chgPwd", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public int chgPwd(@RequestBody JSONObject jsonObj)
			throws JsonProcessingException {	
		//返回-1，旧密码不正确,1：修改成功，0：修改失败
		String userName = jsonObj.getString("userName");
		String oldPwd = jsonObj.getString("oldPwd");
		String newPwd = jsonObj.getString("newPwd");
		User user=usersService.getUser(userName, oldPwd);
		if(user==null)
			return -1;
		return usersService.chgPwd(userName, newPwd);		
	}
}
