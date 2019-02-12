package com.hzy.campus.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzy.campus.entity.User;

public class AuthFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse rep,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;	//ǿתΪ HttpServletRequest �� request
		HttpServletResponse response = (HttpServletResponse)rep;
		HttpSession session = request.getSession(); // ��ȡsession
		
		String path = request.getContextPath();
	//	System.out.println("��ȡ��Ŀ���� " + path);
		String uri = request.getRequestURI();
	//	System.out.println("��ȡ��ǰ����·���� " + uri);
		
		if((path + "/index.jsp").equals(uri)||(path + "/").equals(uri)){
			chain.doFilter(request, response);	//	����
		}else if((path + "/login.action").equals(uri) || (path + "/log.action").equals(uri)){
			chain.doFilter(request, response);	//	����
		}else{
			//�ж��Ƿ��е�¼
			User user = (User)session.getAttribute("user");	//	��ȡuser�ֶ�
			if(user != null){
				chain.doFilter(request, response);	//	����
			}else{
				System.out.println("����·������ " + uri);
			}
		}
	
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
