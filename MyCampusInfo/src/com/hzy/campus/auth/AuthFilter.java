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
		HttpServletRequest request = (HttpServletRequest)req;	//强转为 HttpServletRequest 的 request
		HttpServletResponse response = (HttpServletResponse)rep;
		HttpSession session = request.getSession(); // 获取session
		
		String path = request.getContextPath();
	//	System.out.println("获取项目名： " + path);
		String uri = request.getRequestURI();
	//	System.out.println("获取当前请求路径： " + uri);
		
		if((path + "/index.jsp").equals(uri)||(path + "/").equals(uri)){
			chain.doFilter(request, response);	//	放行
		}else if((path + "/login.action").equals(uri) || (path + "/log.action").equals(uri)){
			chain.doFilter(request, response);	//	放行
		}else{
			//判断是否有登录
			User user = (User)session.getAttribute("user");	//	获取user字段
			if(user != null){
				chain.doFilter(request, response);	//	放行
			}else{
				System.out.println("请求路径错误： " + uri);
			}
		}
	
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
