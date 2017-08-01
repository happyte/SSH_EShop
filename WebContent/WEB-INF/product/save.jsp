<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/public/head.jspf" %>
	<style type="text/css">
		form div {
			margin: 10px;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			$.extend($.fn.validatebox.defaults.rules,{  
                //函数的名称：{函数的实现体(又是一个json对象，里面包括函数的实现，和错误消息的设置)}   
                format:{  
                    //函数实现，如果返回为false，则验证失败  
                    validator: function(value,param){  
                        //获取当前文件的后缀名  
                        var ext = value.substring(value.lastIndexOf('.') + 1);  
                        //获取支持的文件后缀名，然后比较即可  
                        var arr = param[0].split(","); 
                        for(var i = 0; i < arr.length; i++) {  
                            if(ext == arr[i])  
                                return true;  
                        }  
                        return false;  
                    },  
                    //错误消息  
                    message: '文件后缀必须为:{0}'  
                }  
            });  
			//下拉列表加载
			$("#cc").combobox({
				url:'category_query.action',    
                valueField:'id',  
				textField:'type', //我们下拉列表中显示的是管理员的登录名  
                panelHeight:'auto', //自适应高度  
                panelWidth:120,//下拉列表是两个组件组成的  
                width:120, //要同时设置两个宽度才行  
                editable:false, //下拉框不允许编辑 
                missingMessage:'请选择所属类别' 
			});
			$("input[name=name]").validatebox({
				required:true,
				missingMessage:'请输入商品名称'
			});
			$("input[name=price]").validatebox({
				required:true,
				missingMessage:'请输入商品价格',
				min:0,
				precision:2,  //两位小数
				prefix:'¥'
			});
			$("input[name='fileImage.upload']").validatebox({  
	                required:true,  
	                missingMessage:'请上传商品图片',  
	                //设置自定义方法  
	                validType:"format['gif,jpg,jpeg,png']"//中括号里面是参数  
	         });  
			$("input[name=remark]").validatebox({
				required:true,
				missingMessage:'请输入商品简单描述'
			});
			$("input[name=xremark]").validatebox({
				required:true,
				missingMessage:'请输入商品详细描述'
			});
			//窗体弹出默认时禁用验证  
            $("#ff").form("disableValidation"); 
			//添加商品按键
			$("#submit").click(function() {
				 $("#ff").form("enableValidation"); 
				//如果验证成功，则提交数据  
	             if($("#ff").form("validate")){
	            	 $("#ff").form('submit',{
	            		 url:'product_save.action',
	            		 success:function(){
	            			  parent.$("#win").window("close");  
	            			  parent.$("iframe[title='商品管理']").get(0).contentWindow.$("#dg").datagrid("reload");  	
	            		 }
	            	 });
	             }
			});
			//重置按键功能
			$("#reset").click(function() {
				 $("#ff").form("disableValidation"); 
				 $("#ff").form("reset");
			});
		});
	</script>
</head>
<body>
	<form title="添加商品" id="ff" action="" method="post" enctype="multipart/form-data">
		<div>
			<label>商品名称:</label>
			<input type="text" name="name">
		</div>
		<div>
			<label>商品类型:</label>
			<input type="text" name="price">
		</div>
		<div>
			<label>图片上传:</label>
			<input type="file" name="fileImage.upload">
		</div>
		<div>
			<label>所属类别:</label>
			<input id="cc" name="category.id">
		</div>
		<div>
			<label>加入推荐:</label>
			推荐:<input type="radio" name="commend" checked="checked" value="true">
			不推荐:<input type="radio" name="commend" value="true">
		</div>
		<div>
			<label>是否有效:</label>
			上架:<input type="radio" name="open" checked="checked" value="true">
			下架:<input type="radio" name="open" value="true">
		</div>
		<div>
			<label>简单描述:</label>
			<textarea rows="4" cols="40" name="remark"></textarea>
		</div>
		<div>
			<label>详细描述:</label>
			<textarea rows="8" cols="40" name="xremark"></textarea>
		</div>
		<div>
	    	<a id="submit" href="#" class="easyui-linkbutton">添加</a>
	    	<a id="reset"  href="#" class="easyui-linkbutton">重置</a>
	    </div>  
	</form>
</body>
</html>