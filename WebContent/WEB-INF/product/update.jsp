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
			var dg =  parent.$("iframe[title='商品管理']").get(0).contentWindow.$("#dg");
			//下拉列表加载
			$("#cc").combobox({
				url:'category_query.action',    
                valueField:'id',  
				textField:'type', 
                panelHeight:'auto', //自适应高度  
                panelWidth:120,//下拉列表是两个组件组成的  
                width:120, //要同时设置两个宽度才行  
                editable:false, //下拉框不允许编辑 
                missingMessage:'请选择所属类别' 
			});
			var rows = dg.datagrid("getSelections");
			//表单回显
			$("#ff").form('load',{
				id:rows[0].id,  
                name:rows[0].name,  
                price:rows[0].price,  
                remark:rows[0].remark,  
                xremark:rows[0].xremark,  
                commend:rows[0].commend,  
                open:rows[0].open,  
                'category.id':rows[0].category.id
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
			//弹出窗口禁止验证
			 $("#ff").form("disableValidation");
			 $("#btn").click(function() {
					//开启用户名的那个验证
					$("#ff").form("enableValidation");
					//如果验证成功，则提交数据
					if($("#ff").form("validate")){			
						
					}			
				});
		});
	</script>
</head>
<body>
	<form title="添加商品" id="ff" action="" method="post" enctype="multipart/form-data">
		<div>     
            <label for="name">商品名称:</label> <input type="text" name="name" />     
        </div>     
        <div>  
            <label for="price">商品价格:</label> <input type="text" name="price" />  
        </div>  
        <div>  
            <label>更新图片:</label> <input type="file" name="fileImage.upload" />  
        </div>  
        <div>     
            <label for="account">所属商品类:</label>  
             <!-- 远程加载管理员数据 -->  
             <input id="cc" name="category.id" />  
        </div>  
        <div>  
            <label for="remark">简单描述:</label>  
            <textarea name="remark" cols="40" rows="4"></textarea>  
        </div>  
        <div>  
            <label for="xremark">详细描述:</label>  
            <textarea name="xremark" cols="40" rows="8"></textarea>  
        </div>  
        <div>  
            <label for="commend">推荐商品:</label>   
                是:<input type="radio" name="commend" value="true" />     
                否:<input type="radio" name="commend" value="false" />   
        </div>  
        <div>  
            <label for="open">有效商品:</label>  
            上架:<input type="radio" name="open" value="true" />  
            下架:<input type="radio" name="open" value="false" />  
                      
        </div>  
          
        <div>  
            <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">更新</a>    
            <input type="hidden" name="id" />  
        </div>  `  
	</form>
</body>
</html>