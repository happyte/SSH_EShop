package com.zs.ssh.action;

import org.springframework.stereotype.Controller;

import com.zs.ssh.model.User;

@Controller
public class UserAction extends BaseAction<User> {
	public String login(){
		model = userService.login(model);
		if (model == null) {
			System.out.println("登陆失败");
			session.put("error", "登陆失败");
			return "login";
		}
		else {
			//登陆成功的话，将用户存储到session中
			session.put("user", model);
			if(session.get("goURL") == null){
				System.out.println("没有goURL");
				return "index";
			}
			else {
				System.out.println("有goURL:"+session.get("goURL"));
				return "goURL";
			}
		}
	}
}
