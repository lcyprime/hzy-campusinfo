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
		Map sess = ActionContext.getContext().getSession();	//	获取session，用于获取用户名
		User user = (User)sess.get("user");	//	获取用户名
		Integer userId = user.getId();	//	获取用户的ID
		 List myOrder = dao.findByUserid(userId);	//	通userId获取用户信息
		 sess.put("myOrder", myOrder);		//	把该用户信息放到session里
	}

	public void doSubmit() {
		HttpServletRequest request = ServletActionContext.getRequest();	
		String[] business = request.getParameterValues("business_key");	//	获取前段的选项 business_key
		
		if(business!=null && business.length > 0){
			Map sess = ActionContext.getContext().getSession();
			User user = (User)sess.get("user");		//	通过session获取用户名，获取用户信息
			Integer userId  = user.getId();	//	获取用户的ID
			Date now = new Date();
			Timestamp orderTime = new Timestamp(now.getTime());	//	把时间转换为Timestamp类型
			Short validState = 1;	//	类型与UserOrder的类型一致
			
			for(int i=0; i < business.length; i++){		//	循环放入Dao保存信息
				String orderBusiness = business[i];
				UserOrder uo = new UserOrder();
				uo.setOrderBusiness(orderBusiness);
				uo.setOrderState(validState);
				uo.setOrderTime(orderTime);
				uo.setUserid(userId);
				
				dao.save(uo);	//	把信息保存到数据库
				
			}
		}
	}

	public void doCancel() {
		HttpServletRequest request = ServletActionContext.getRequest();	
		String[] business = request.getParameterValues("business_name");	//	获取前段的选项 business_key
		
		if(business!=null && business.length > 0){
			Map sess = ActionContext.getContext().getSession();	//	获取session，用于获取用户名
			User user = (User)sess.get("user");	//	获取用户名
			Integer userId = user.getId();	//	获取用户的ID
			
			Short cancelOrder = 0;
			String busi = "";
			for(int i=0; i < business.length; i++){		
				String orderBusiness = business[i]; 	//	拼凑business
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
