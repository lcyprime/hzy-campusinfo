package com.hzy.campus.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.hzy.campus.dao.UserOrderDAO;
import com.hzy.campus.dao.UserRelationDAO;
import com.hzy.campus.serviceModel.RelationService;

@Controller
public class RelationAction {

	@Resource(name = "relationService")
	private RelationService service;

	public void RefreshRelation() { // 刷新已有好友的列表
		service.doRefreshRelation();
	}

	public String initRelation() { // 页面刚进入的时候
		RefreshRelation();
		service.doInitRelation();
		return "relation";
	}

	public String createRelation() { // 解除关系
		RefreshRelation();
		service.doCreateRelation();
		return "relation";
	}

}
