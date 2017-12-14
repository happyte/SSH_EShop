package com.zs.ssh.action;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.stereotype.Controller;

import com.zs.ssh.model.BackData;
import com.zs.ssh.model.Forder;
import com.zs.ssh.model.SendData;
import com.zs.ssh.model.User;


@Controller
public class PayAction extends BaseAction<Object> implements ParameterAware{
	
	private Map<String, String[]> parameters = null;

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	
	//重写返回的model
	@Override
	public Object getModel() {
		System.out.println("pd_FrpId:"+parameters.get("pd_FrpId"));
		if(parameters.get("pd_FrpId") != null){
			model = new SendData();
		}
		else {
			model = new BackData();
		}
		return model;
	}
	
	public String goBank(){
		//1. 补全参数:P2 p3 pd Pa需要从session中获取
		SendData sendData = (SendData) model;
		Forder forder = (Forder) session.get("oldForder");
		User user = (User) session.get("user");
		sendData.setP2_Order(forder.getId().toString());//订单号
		sendData.setP3_Amt(forder.getTotal().toString());//订单金额
		sendData.setPa_MP(user.getEmail()+","+user.getPhone());//订单扩展信息
		System.out.println("model:"+model);
		//2. 对参数进行追加		
		//3. 加密获取签名		
		//4. 存储到request域中
		//5. 跳转到支付页面
		payService.saveDataToRequest(request, sendData);//完成2,3,4
		System.out.println("model:"+model);
		return "pay";
	}
	
	//接受数据的方法
	public void backBank(){
		BackData backData = (BackData) model;
		System.out.println("back model:"+model);
		boolean isOK = payService.checkBackData(backData);
		if (isOK) {
			//更新订单状态
			//payService.updateStatusById(10, 2);
			System.out.println("-----success-----");
		}
		else {
			System.out.println("-----false-----");
		}
	}

}
