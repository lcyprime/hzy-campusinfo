package com.hzy.campus.serviceModel;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hzy.campus.dao.LogDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

@Service("logService")
public class LogService {
	
	@Resource(name="LogDAO")
	private LogDAO dao;
	
	public void queryLog(){
		List logs = dao.findAll();
		Map session = ActionContext.getContext().getSession();
		session.put("logs", logs);
		
	}
}
