package com.zs.ssh.service;

import com.zs.ssh.model.Forder;
import com.zs.ssh.model.Product;
import com.zs.ssh.model.Sorder;

//购物项接口
public interface SorderService extends BaseService<Sorder> {
	//添加商品，返回新的购物车
	public Forder addSorder(Forder forder,Product product);
	//把商品转换城购物项
	public Sorder productToSorder(Product product);
}
