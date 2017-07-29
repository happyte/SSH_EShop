<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/public/head.jspf" %>
	<style type="text/css">
		form div {
			margin: 5px;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			//强制用户输入类别名称
			$("input[type=text]").validatebox({
				required:true,
				missingMessage:"请输入类别名称"
			});
			$("#cc").combobox({
				url:'account_query.action',    
                valueField:'id',  
				textField:'login', //我们下拉列表中显示的是管理员的登录名  
                panelHeight:'auto', //自适应高度  
                panelWidth:120,//下拉列表是两个组件组成的  
                width:120, //要同时设置两个宽度才行  
                editable:false //下拉框不允许编辑 
			});
			 //窗体弹出默认是禁用验证，因为刚弹出的窗口，用户还没填就显示的话，太丑  
            $("#ff").form("disableValidation");
			$("#btn").click(function() {
				//开启用户名的那个验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")){			
					$("#ff").form('submit', {
						url:'category_save.action',
						success: function(){
							//如果成功了，关闭当前窗口  
                            parent.$("#win").window("close");  
                            //刷新页面，刚刚添加的就显示出来了。  
                            //获取aindex-->iframe-->datagrid  
                            parent.$("iframe[title='类别管理']").get(0).contentWindow.$("#dg").datagrid("reload");  	
						}
					});
				}			
			});
		});
	</script>
</head>
<body>
	<form id="ff" action="" method="post">
		<div>
			<label for="name">类别名称:</label>
			<input type="text" name="type">
		</div>
		<div>
			<label>所属管理员:</label>
			<input id="cc" name="account.id">
		</div>
		<div>
			<label for="hot">热点</label>
			是<input type="radio" name="hot" value="true">&nbsp;
			否<input type="radio" name="hot" value="true">
		</div>
		 <div>
	    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
	    </div>  
	</form>
</body>
</html>