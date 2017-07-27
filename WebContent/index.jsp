<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="category_update.action?id=2&type=haha&hot=true">访问update</a><br>  
	<a href="category_save.action?id=4&type=hello&hot=true">访问save</a><br>
	<a href="category_query.action">查询所有类别</a><br/> 
	
	<c:forEach items="${requestScope.categoryList }" var="category">
		${category.id }--${category.type }--${category.hot }
	</c:forEach> 
	
	
</body>
</html>