<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/public/head.jspf" %>
	<style type="text/css">
		<style type="text/css">
		body {
			margin: 1px;
		}
		.searchbox {
		  margin: -3px;
		}
	</style>
	<script type="text/javascript">
	 $(function(){  
         $('#dg').datagrid({     
             //请求数据的url地址，后面会改成请求我们自己的url  
             url:'category_queryJoinAccount.action',  
             loadMsg:'Loading......',  
             queryParams:{type:''},//参数  
             //width:300,  
             fitColumns:true,//水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置  
             //显示斑马线  
             striped:true,  
             //当数据多的时候不换行  
             nowrap:true,  
             singleSelect:false, //如果为真，只允许单行显示，全选功能失效  
             //设置分页  
             pagination:true, 
             //pageSize要与pageList一起使用 
             pageSize:5,  
             //设置可选的每页记录数，供用户选择，默认是10,20,30,40...  
             pageList:[5,10,15,20],  
             toolbar:[{
             	 iconCls: 'icon-add',  
             	 text:'添加类别',  
             	 handler: function(){
             		//锁定全屏，因为win这个div是在aindex.jsp中创建的
             		parent.$("#win").window({
						title:"添加类别",
						width:350,
						height:200,
						content:'<iframe src="send_category_save.action" frameborder="0" width="100%" height="100%" />'
					});
             	}  
             },'-',{
            	 iconCls: 'icon-edit',  
                 text:'更新类别',  
                 handler: function(){
                	 alert('--更新类别--');
                 }  
             },'-',{
            	 iconCls: 'icon-remove',  
                 text:'删除类别',  
                 handler: function(){
                	 //找出checked的行数
                	 var rows = $("#dg").datagrid("getSelections"); 
                	 if(rows.length == 0){
                		 //右下角弹出来的提示
                		 $.messager.show({
                			 title:"错误提示",
                			 msg:"至少要选择一条记录",
                			 timeout:2000, 
                			 showType:'slide',
                		 });
                	 }
                	 else {
						$.messager.confirm("删除的确认对话框","确认要删除此项吗?",function(r){
							if(r){
								var ids = "";
								for(var i=0; i<rows.length; i++){
									ids += rows[i].id+",";
								}
								ids = ids.substring(0,ids.length-1);
								$.post("category_deleteByIds",{ids:ids},function(result){
									if(result == "true"){
										//reload刷新当前页
										$("#dg").datagrid("reload");
									}
									else {
										 $.messager.show({
				                			 title:"删除失败",
				                			 msg:"删除失败，请重新检查",
				                			 timeout:2000, 
				                			 showType:'slide',
				                		 });
									}
								});
							}
						});
					}
                 } 
             },{ //查询按钮不是LinkButton，它有语法，但是也支持解析HTML标签
				text:"<input id='ss' name='serach' />"
             }],
             
             rowStyler: function(index,row){
			    	console.info("index" + index + "," + row)
			    	if(index % 2 == 0) {
			    		return 'background-color:#fff;';
			    	} else {
			    		return 'background-color:#c4e1e1;';
			    	}
			    	
			    },
             //同列属性，但是这些列将会冻结在左侧,大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内  
             frozenColumns:[[
			     {field:'checkbox',checkbox:true},
			     {field:'id',title:'编号',width:200}    //这里的field字段要和数据库中的一样，也就是要跟json数据中的一样             
			 ]],
             //配置datagrid的列字段   
             //field：列字段的名称，与json的key捆绑  
             //title：列标题，是显示给人看的  
             columns:[[                       
                 {field:'type',title:'类别名称',width:100,  
                     //用来格式化当前列的值，返回的是最终的数据  
                     	formatter: function(value,row,index){  
                     		return "<span title=" +　value + ">" + value + "</span>";  
                 	}  
             	 },      
             	 {field:'hot',title:'热卖',width:100,  
                     //用来格式化当前列的值，返回的是最终的数据  
                     	formatter: function(value,row,index){ 
                     		//都不允许被勾选
                     		if(value){
                     			 return "<input type='checkbox' checked='checked' disabled='true'"; //勾选 
                     		}
                     		else {
                     			 return "<input type='checkbox' disabled='true'";
							}  
                 		}  
             	 },      
             	 {field:'account.login',title:'所属管理员',width:100,  
             		formatter: function(value,row,index){ 
             			if(row.account != null && row.account.login != null){
             				return  row.account.login;
             			}
             			else {
							return "没有管理员";
						}
             		}  
             	 },      
             ]]  
         });  
         $('#ss').searchbox({ 
				//触发查询事件
				searcher:function(value,name){ 
					//value表示输入的值,name为search
					//alert(value + "," + name);
					//获取当前查询的关键字，通过DataGrid加载相应的信息，使用load加载和显示第一页的所有行。
					//如果指定了参数，它将取代'queryParams'属性。通常可以通过传递一些参数执行一次查询，通过调用这个方法会向上面url指定的action去发送请求，从服务器加载新数据。
					$('#dg').datagrid('load',{
						type: value   //发送的请求里有type参数
					});
				}, 
				prompt:'请输入搜索关键字' 
		}); 
     });  
	</script>
</head>
<body>
	<table id="dg"></table> 
</body>
</html>