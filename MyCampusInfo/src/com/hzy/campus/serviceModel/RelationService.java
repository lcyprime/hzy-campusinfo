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

	public void doRefreshRelation() { // ˢ�±��
		Map sess = ActionContext.getContext().getSession(); // ��ȡsession�����ڻ�ȡ�û���
		User user = (User) sess.get("user"); // ��ȡ�û���
		Integer userId = user.getId(); // ��ȡ�û���ID
		List MyIdList = userRelationDAO.findByMyId(userId);

		Map information = null;
		List relationlist = new ArrayList();

		if (MyIdList != null && MyIdList.size() > 0) {
			for (int i = 0; i < MyIdList.size(); i++) {
				UserRelation userRelation = (UserRelation) MyIdList.get(i);

				Integer otherId = userRelation.getOtherId(); // ��ȡ��ϵ�˵�ID
				String relation = userRelation.getRelation(); // ��ȡ����ϵ�˵Ĺ�ϵ
				Short state = userRelation.getState(); // ��ȡ����ϵ�˹�ϵ��״̬

				User otherUser = userDAO.findById(otherId); // ������ϵ�˵�ID��ȡ����Ϣ
				String otherUserName = otherUser.getUsername(); // ��ȡ��ϵ�˵�����

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

	public void doInitRelation() { // ��ȡ����Щ�ɽ�����ϵ����
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		Integer myId = user.getId();

		List userList = userDAO.getAllExceptMe(myId);
		session.put("userList", userList);
	}

	public void doCreateRelation() { // ������ϵ
		
	}

}
