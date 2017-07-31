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
             url:'product_queryJoinCategory.action',  
             loadMsg:'Loading......',  
             queryParams:{name:''},//参数  
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
             	 text:'添加商品',  
             	 handler: function(){
             	
             	}  
             },'-',{
            	 iconCls: 'icon-edit',  
                 text:'更新商品',  
                 handler: function(){
                	   
                 }  
             },'-',{
            	 iconCls: 'icon-remove',  
                 text:'删除商品',  
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
 								$.post("product_deleteByIds",{ids:ids},function(result){
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
			     {field:'id',title:'商品编号',width:200}    //这里的field字段要和数据库中的一样，也就是要跟json数据中的一样             
			 ]],
             //配置datagrid的列字段   
             //field：列字段的名称，与json的key捆绑  
             //title：列标题，是显示给人看的  
             columns:[[                       
            	 {field:'name',title:'商品名称',width:100},      
                 {field:'price',title:'商品价格',width:100},  
                 {field:'remark',title:'简单描述',width:100},  
                 {field:'xremark',title:'详细描述',width:100},  
                 {field:'date',title:'上架时间',width:100},  
                 {field:'commend',title:'推荐商品',width:100,    
                     formatter: function(value,row,index){  
                         if(value) {  
                             return "<input type='checkbox' checked='checked' disabled='true'";  
                         } else {  
                             return "<input type='checkbox' disabled='true'";  
                         }  
                      }  
                 },  
                 {field:'open',title:'有效商品',width:100,    
                     formatter: function(value,row,index){  
                         if(value) {  
                             return "<input type='checkbox' checked='checked' disabled='true'";  
                         } else {  
                             return "<input type='checkbox' disabled='true'";  
                         }  
                     }  
                  },  
                  {field:'category.type',title:'所属商品类别',width:200, //category.type是商品类别  
                      formatter: function(value,row,index){  
                          if(row.category != null && row.category.type != null) {  
                              return row.category.type; //如果商品类别不为空，返回商品类别  
                          } else {  
                              return "此商品暂时未分类";  
                          }  
                       }    
                  }
             ]]  
         });  
         $('#ss').searchbox({ 
				//触发查询事件
				searcher:function(value,name){ 
					$('#dg').datagrid('load',{
						name: value   //发送的请求里有type参数
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