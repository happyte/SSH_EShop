<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/public/head.jspf" %>
</head>
<body> 
	<a href="send_main_aindex.action">去后台</a>
	<c:forEach items="${applicationScope.bigList }" var="list">
		<div class="products_list products_slider clear">
			<h2 class="sub_title">${list[0].category.type}</h2>
			<ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
				<c:forEach items="${list}" var="product">
					<li>
						<a href="#" class="product_image">
							<img src="${shop}/files/${product.pic}" />
						</a>
						<div class="product_info">
							<h3><a href="#">商品名称：${product.name }</a></h3>
							<small>简单描述：${product.remark}</small>
						</div>
						<div class="price_info">
							<button><span class="pr_add">添加购物车</span></button>
							<button class="price_add" title="" type="button">
		                    	<span class="pr_price">￥${product.price}</span>
		                    </button>
						</div>
					</li>
					
				</c:forEach>
			</ul>
		</div>
	</c:forEach>
</body>
</html>