package com.hzy.campus.serviceModel;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hzy.campus.dao.UserDAO;
import com.hzy.campus.entity.User;
import com.hzy.campus.util.MD5;
import com.opensymphony.xwork2.ActionContext;

@Service("userLoginService")
public class LoginService {
	
	@Resource(name="UserDAO")
	private UserDAO dao;
	
	
	public String doLogin(String username, String password){
		String checkString = "error";
		List list = dao.findByUsername(username);	//从数据库中  查找是否有该用户  把它放到一个集合里
		if(list != null && list.size()>0){
			User user = (User)list.get(0);		//	获取第一条数据
			String pwd = user.getPassword();	//	获取数据列表的 密码
			
			String pword = MD5.toMD5(password);	//	加密	
			
//			System.out.println("pwd : " + pwd);
//			System.out.println("pword : " + pword);
			
			if (pwd.equals(pword)) {
				Map sess = ActionContext.getContext().getSession();		//验证通过后把user信息放到session中
				sess.put("user", user);
				
				String role = user.getRole();	//	获取数据列表 的用户类型
				checkString = role;	//用于返回用户类型
			}
		}
		return checkString;
	}

	
}
