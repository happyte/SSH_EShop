package com.zs.ssh.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.zs.ssh.model.Forder;
import com.zs.ssh.model.Sorder;
import com.zs.ssh.service.ForderService;

@Service
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements ForderService {

	//BigDecimal是属于什么类型的
	@Override
	public BigDecimal calcTotal(Forder forder) {
		BigDecimal total = new BigDecimal(0.00);
		for(Sorder sorder:forder.getSorders()){
			total = total.add(sorder.getPrice().multiply(new BigDecimal(sorder.getNumber())));
		}
		return total;
	}

}
