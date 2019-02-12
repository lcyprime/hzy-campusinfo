package com.hzy.campus.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.hzy.campus.serviceModel.LoginService;

@Controller
public class LoginAction{
		
		private final Logger log = Logger.getLogger(LoginAction.class);
		private String username;
		private String password;
		
		@Resource(name="userLoginService")
		private LoginService service;
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String execute(){
			String returnStr = "error";
			String LoginInfo = "error";
			HttpServletRequest request = ServletActionContext.getRequest();
			String ip = request.getRemoteAddr();
			
			returnStr = service.doLogin(username, password); //传入用户名和密码
			
			if(returnStr.equals("error")){
				LoginInfo = "IP：" + ip + " , 以账号：" + username + " , 以密码：" + password + "试图登录失败";
			}else{
				LoginInfo = "IP：" + ip + " , 以账号：" + username + " , 以密码：" + password + "试图登录成功";
			}
			System.out.println(LoginInfo);
			log.info(LoginInfo);
			return returnStr;
		}

}
