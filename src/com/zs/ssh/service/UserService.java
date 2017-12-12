package com.zs.ssh.service;

import com.zs.ssh.model.User;

public interface UserService extends BaseService<User>  {
	//用户登陆，成功返回该user
	public User login(User user);
}
