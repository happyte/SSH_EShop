package com.zs.ssh.action;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;

import com.zs.ssh.model.Forder;
import com.zs.ssh.model.Product;
import com.zs.ssh.model.Sorder;

@Controller
public class SorderAction extends BaseAction<Sorder> {
	public String addSorder(){
//		System.out.println("加入购物车操作..........");
		//1.根据product.id获取商品
		Product product = productService.get(model.getProduct().getId());
		//2.判断session中是否有购物车，没有就创建
		if (session.get("forder") == null) {
			//创建新的购物车并添加到session中
			session.put("forder", new Forder(new ArrayList<Sorder>()));
		}
		//3.把商品信息添加到购物车中,并判断重复
		Forder forder = (Forder) session.get("forder");
		forder = sorderService.addSorder(forder, product);
		//4.计算总价格
		forder.setTotal(forderService.calcTotal(forder));
		//5.把新的购物车添加到session中
		session.put("forder", forder);
		return "showCart";
	}
}
