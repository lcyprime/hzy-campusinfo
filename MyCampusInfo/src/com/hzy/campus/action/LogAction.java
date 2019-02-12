package com.hzy.campus.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.hzy.campus.dao.LogDAO;
import com.hzy.campus.serviceModel.LogService;

@Controller
public class LogAction {
	
	@Resource(name="logService")
	private LogService service;
	
	public String execute(){
		service.queryLog();
		return "log";
		
	}
}
