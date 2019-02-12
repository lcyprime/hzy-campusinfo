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
		List list = dao.findByUsername(username);	//�����ݿ���  �����Ƿ��и��û�  �����ŵ�һ��������
		if(list != null && list.size()>0){
			User user = (User)list.get(0);		//	��ȡ��һ������
			String pwd = user.getPassword();	//	��ȡ�����б�� ����
			
			String pword = MD5.toMD5(password);	//	����	
			
//			System.out.println("pwd : " + pwd);
//			System.out.println("pword : " + pword);
			
			if (pwd.equals(pword)) {
				Map sess = ActionContext.getContext().getSession();		//��֤ͨ�����user��Ϣ�ŵ�session��
				sess.put("user", user);
				
				String role = user.getRole();	//	��ȡ�����б� ���û�����
				checkString = role;	//���ڷ����û�����
			}
		}
		return checkString;
	}

	
}
