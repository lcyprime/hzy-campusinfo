package com.hzy.campus.serviceModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.hzy.campus.dao.UserOrderDAO;
import com.hzy.campus.entity.User;
import com.hzy.campus.entity.UserOrder;
import com.opensymphony.xwork2.ActionContext;

@Service("orderService")
public class OrderService {
	
	@Resource(name="UserOrderDAO")
	private UserOrderDAO dao;
	
	public void doInit(){
		Map sess = ActionContext.getContext().getSession();	//	��ȡsession�����ڻ�ȡ�û���
		User user = (User)sess.get("user");	//	��ȡ�û���
		Integer userId = user.getId();	//	��ȡ�û���ID
		 List myOrder = dao.findByUserid(userId);	//	ͨuserId��ȡ�û���Ϣ
		 sess.put("myOrder", myOrder);		//	�Ѹ��û���Ϣ�ŵ�session��
	}

	public void doSubmit() {
		HttpServletRequest request = ServletActionContext.getRequest();	
		String[] business = request.getParameterValues("business_key");	//	��ȡǰ�ε�ѡ�� business_key
		
		if(business!=null && business.length > 0){
			Map sess = ActionContext.getContext().getSession();
			User user = (User)sess.get("user");		//	ͨ��session��ȡ�û�������ȡ�û���Ϣ
			Integer userId  = user.getId();	//	��ȡ�û���ID
			Date now = new Date();
			Timestamp orderTime = new Timestamp(now.getTime());	//	��ʱ��ת��ΪTimestamp����
			Short validState = 1;	//	������UserOrder������һ��
			
			for(int i=0; i < business.length; i++){		//	ѭ������Dao������Ϣ
				String orderBusiness = business[i];
				UserOrder uo = new UserOrder();
				uo.setOrderBusiness(orderBusiness);
				uo.setOrderState(validState);
				uo.setOrderTime(orderTime);
				uo.setUserid(userId);
				
				dao.save(uo);	//	����Ϣ���浽���ݿ�
				
			}
		}
	}

	public void doCancel() {
		HttpServletRequest request = ServletActionContext.getRequest();	
		String[] business = request.getParameterValues("business_name");	//	��ȡǰ�ε�ѡ�� business_key
		
		if(business!=null && business.length > 0){
			Map sess = ActionContext.getContext().getSession();	//	��ȡsession�����ڻ�ȡ�û���
			User user = (User)sess.get("user");	//	��ȡ�û���
			Integer userId = user.getId();	//	��ȡ�û���ID
			
			Short cancelOrder = 0;
			String busi = "";
			for(int i=0; i < business.length; i++){		
				String orderBusiness = business[i]; 	//	ƴ��business
				busi = busi + "'" + orderBusiness + "'";
				if(i != business.length-1){
					busi = busi + ",";
				}
			}
			System.out.println("busi = " + busi);
			dao.updateOrderStateByUseridAndBusiness(busi, userId, cancelOrder);
		}
	
	}
}
