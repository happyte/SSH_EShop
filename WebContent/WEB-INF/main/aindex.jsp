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
	<script type="text/javascript">
		$(function() {
			$("a[title]").click(function() {
				var text = $(this).text();
				var href = $(this).attr("title");
				//判断右边是否已经有tab
				if($("#tt").tabs("exists",text)){
					$("#tt").tabs("select",text);
				}
				else {
					$("#tt").tabs("add",{
	                    title:text,
						closable:true,
						content:'<iframe title=' + text + ' src=' + href + ' frameborder="0" width="100%" height="100%" />'
					});
				}
			});
		});
	</script>
</head>
<body>
	<body class="easyui-layout">  
		<!-- 顶部 -->
        <div data-options="region:'north',title:'欢迎来到易购后台系统',split:true" style="height:100px;"></div>   
        <!-- 左侧 -->  
        <div data-options="region:'west',title:'系统操作',split:true" style="width:200px;">  
            <!-- 此处显示的是系统菜单 -->  
            <div id="menu" class="easyui-accordion" data-options="fit:true">        
                <div title="基本操作" data-options="iconCls:'icon-reload'">     
                    <ul>
						<li><a href="#" title="send_category_query.action">类别管理</a></li>
						<li><a href="#" title="send_product_query.action">商品管理</a></li>
					</ul>    
                </div>    
                <div title="其它操作" data-options="iconCls:'icon-reload'"> 
                	 <ul>
						<li><a href="#">商品管理</a></li>
						<li><a href="#">类别管理</a></li>
					</ul>   
                </div> 
            </div>     
        </div>     
        <!-- 右侧 -->
        <div data-options="region:'center',title:'后台操作页面'" style="padding:5px;background:#eee;">
        	<div id="tt" class="easyui-tabs" data-options="fit:true"> 
        		<div title="系统缺省页面" style="padding:10px;">
    				此处以后显示相应的系统信息（当前操作系统的类型，当前项目的域名，硬件的相关配置或者显示报表
    			</div>   
        	</div>
        </div>
        <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>        
    </body>    
</body>
</html>