package com.zs.ssh.action;

import org.springframework.stereotype.Controller;

import com.zs.ssh.model.Forder;
import com.zs.ssh.model.Status;
import com.zs.ssh.model.User;

@Controller
public class ForderAction extends BaseAction<Forder> {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Forder getModel() {
		model = (Forder) session.get("forder");
		return model;
	}
	
	public String save(){
		model.setUser((User) session.get("user"));
		//状态1未支付
		model.setStatus(new Status(1));
		//保存购物车
		forderService.save(model);
		session.put("oldForder", session.get("forder"));
		session.put("forder", new Forder());
		return "bank";
	}
}
