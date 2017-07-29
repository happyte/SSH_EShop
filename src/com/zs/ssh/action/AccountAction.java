package com.zs.ssh.action;

import com.zs.ssh.model.Account;

public class AccountAction extends BaseAction<Account>{
	
	private static final long serialVersionUID = 1L;

	public String execute(){
		jsonList = accountService.query();
		return "jsonList";
	}
}
