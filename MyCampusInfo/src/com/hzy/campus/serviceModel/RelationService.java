package com.hzy.campus.serviceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hzy.campus.dao.UserDAO;
import com.hzy.campus.dao.UserRelationDAO;
import com.hzy.campus.entity.User;
import com.hzy.campus.entity.UserRelation;
import com.opensymphony.xwork2.ActionContext;

@Service("relationService")
public class RelationService {

	@Resource(name = "UserDAO")
	private UserDAO userDAO;

	@Resource(name = "UserRelationDAO")
	private UserRelationDAO userRelationDAO;

	public void doRefreshRelation() { // 刷新表格
		Map sess = ActionContext.getContext().getSession(); // 获取session，用于获取用户名
		User user = (User) sess.get("user"); // 获取用户名
		Integer userId = user.getId(); // 获取用户的ID
		List MyIdList = userRelationDAO.findByMyId(userId);

		Map information = null;
		List relationlist = new ArrayList();

		if (MyIdList != null && MyIdList.size() > 0) {
			for (int i = 0; i < MyIdList.size(); i++) {
				UserRelation userRelation = (UserRelation) MyIdList.get(i);

				Integer otherId = userRelation.getOtherId(); // 获取联系人的ID
				String relation = userRelation.getRelation(); // 获取与联系人的关系
				Short state = userRelation.getState(); // 获取与联系人关系的状态

				User otherUser = userDAO.findById(otherId); // 根据联系人的ID获取其信息
				String otherUserName = otherUser.getUsername(); // 获取联系人的名字

				information = new HashMap();
				information.put("otherUserName", otherUserName);
				information.put("relation", relation);
				information.put("state", state);
				information.put("otherId", otherId);

				relationlist.add(i, information);
			}
			sess.put("relationlist", relationlist);
		}
	}

	public void doInitRelation() { // 获取有哪些可建立关系的人
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		Integer myId = user.getId();

		List userList = userDAO.getAllExceptMe(myId);
		session.put("userList", userList);
	}

	public void doCreateRelation() { // 建立关系
		
	}

}
