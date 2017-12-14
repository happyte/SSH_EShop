package com.zs.ssh.service;

import java.util.Map;

import com.zs.ssh.model.BackData;
import com.zs.ssh.model.SendData;

public interface PayService {
	// 把加密后的信息存储到requestMap中
	public Map<String, Object> saveDataToRequest(Map<String, Object> request,SendData sendData);
	
	//把返回的数据进行加密得到密文，并与传回来的密文比较
	public boolean checkBackData(BackData backData);
	
	//根据订单号，更新订单状态
	public void updateStatusById(int id,int sid);
}
