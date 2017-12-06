package com.zs.ssh.service;

import java.math.BigDecimal;

import com.zs.ssh.model.Forder;

public interface ForderService extends BaseService<Forder> {
	//计算购物车总价格
	public BigDecimal calcTotal(Forder forder);
}
