<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/public/head.jspf" %>
	<style type="text/css">
		#menu{
			width: 200px;
		}
		#menu ul{
			list-style: none;   /* 取消前面的两点   */   
			padding: 0px;
			margin: 0px;
		}
		#menu ul li {  
            border-bottom: 1px solid #fff;   /*两个li之间加一个横线*/
              
        }  
		#menu ul li a{
			display: block;   /*先要转换成块级标签，才能内置元素*/
			background-color: #00a6ac;
			color: #fff;
			padding: 5px;
			text-decoration: none; /*去掉下划线*/
		}
		#menu ul li a:hover {  
            background-color: #008792;  
        }  
	</style>
</head>
<body>
	<div id="menu">
		<ul>
			<li><a href="#">商品管理</a></li>
			<li><a href="#">类别管理</a></li>
		</ul>
	</div>
</body>
</html>