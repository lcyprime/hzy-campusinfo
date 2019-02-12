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

	public void RefreshRelation() { // ˢ�����к��ѵ��б�
		service.doRefreshRelation();
	}

	public String initRelation() { // ҳ��ս����ʱ��
		RefreshRelation();
		service.doInitRelation();
		return "relation";
	}

	public String createRelation() { // �����ϵ
		RefreshRelation();
		service.doCreateRelation();
		return "relation";
	}

}
