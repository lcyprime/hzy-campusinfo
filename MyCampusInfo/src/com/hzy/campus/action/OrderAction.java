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

	public String submitOrder() { // ��Ӷ���
		service.doSubmit(); // �Ѷ�����Ϣ���浽���ݿ�
		initOrder(); // ˢ�±�
		return "order";
	}

	public String cancelOrder() { // ȡ������
		service.doCancel();
		initOrder(); // ˢ�±�
		return "order";
	}
}
