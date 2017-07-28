<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/public/head.jspf" %>
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
             singleSelect:true, //如果为真，只允许单行显示，全选功能失效  
             //设置分页  
             pagination:true,  
             rowStyler: function(index,row){  
                 console.info("index" + index + "," + row)  
                 if(index % 2 == 0) {  
                     return 'background-color:#fff;';  
                 } else {  
                     return 'background-color:#ff0;';  
                 }  
                   
             },  
             //同列属性，但是这些列将会冻结在左侧,大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内  
             frozenColumns:[[  
                 {field:'checkbox',checkbox:true},  
                 {field:'id',title:'编号',width:200}                   
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
     });  
	</script>
</head>
<body>
	<table id="dg"></table> 
</body>
</html>