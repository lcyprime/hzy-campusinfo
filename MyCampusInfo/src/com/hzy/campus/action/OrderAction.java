package com.hzy.campus.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.hzy.campus.serviceModel.OrderService;

@Controller
public class OrderAction {

	@Resource(name = "orderService")
	private OrderService service;

	public String initOrder() {
		service.doInit();
		return "order";
	}

	public String submitOrder() { // 添加订阅
		service.doSubmit(); // 把订阅信息保存到数据库
		initOrder(); // 刷新表
		return "order";
	}

	public String cancelOrder() { // 取消订阅
		service.doCancel();
		initOrder(); // 刷新表
		return "order";
	}
}
