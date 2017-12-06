package com.zs.ssh.service.impl;

import org.springframework.stereotype.Service;

import com.zs.ssh.model.Forder;
import com.zs.ssh.model.Product;
import com.zs.ssh.model.Sorder;
import com.zs.ssh.service.SorderService;

@Service
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements SorderService {

	@Override
	public Forder addSorder(Forder forder, Product product) {
		//当前购物车是否有该购物项
		boolean isHave = false;
		Sorder sorder = productToSorder(product);
		//订单有很多购物项，需要判断是否已经存在该购物项目，
		//存在的话只需要添加数量，不存在的话订单添加该购物项
		for(Sorder old:forder.getSorders()){
			if(old.getProduct().getId().equals(sorder.getProduct().getId())){
				sorder.setNumber(old.getNumber()+sorder.getNumber());
				isHave = true;
				break;
			}
		}
		if(!isHave){
			forder.getSorders().add(sorder);
		}
		return forder;
	}

	//把商品转换城购物项
	@Override
	public Sorder productToSorder(Product product) {
		Sorder sorder = new Sorder();
		sorder.setName(product.getName());
		sorder.setNumber(1);
		sorder.setPrice(product.getPrice());
		sorder.setProduct(product);
		return sorder;
	}

}
