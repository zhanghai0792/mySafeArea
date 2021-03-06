<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="pojo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int target = Integer.parseInt(request.getParameter("target"));
user user1 = (user) request.getSession().getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <title>房屋统计信息</title>
<link rel="stylesheet" type="text/css"href="easyUI/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="easyUI/css/style.css"/>
<script type="text/javascript" src="easyUI/jquery.min.js"></script>
<script type="text/javascript" src="easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyUI/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	loadHouseSummary();
});

//加载所有的汽车统计
function loadHouseSummary() {
	$.post("/mySafeArea/AreaSummary/house",
	{
		timestamp:(new Date()).valueOf()
	},
	function(result){
		$('#datagrid_houseSumnmary').datagrid('loadData',{total:result.total,rows:result.datas});
	}
		
	);
}




</script>
 
  </head>
  
  <body class="easyui-layout">
	<div align="center" data-options="region:'north',collapsible:false"
		title='' style="height:100px;background-color:#D6E6DE">
		<%@include file="../inc/header.inc"%>
	</div>
	<div data-options="region:'west',collapsible:true" title='功能导航' style="width:120px;">
		<%@include file="../inc/menu_user.inc"%>
	</div>
	<div data-options="region:'south'"
		style="height:50px;background-color:#D6E6DE" align="center">
		<%@include file="../../inc/footer.inc"%>
	</div>
	<div data-options="region:'east',title:'帮助',collapsible:true,collapsed:true,width:250"
		style="padding:10px;">
		<%@include file="../inc/help/houseSummary.inc"%>
	</div>
	<div data-options="region:'center',title:'房屋统计信息'">
		<table class="easyui-datagrid" id="datagrid_houseSumnmary"
			data-options="fit:true,striped:true,checkOnSelect:true,selectOnCheck:false,singleSelect:true">
			<thead>
				<tr>
					<th data-options="field:'areaName',width:150,halign:'center'">小区名称</th>
					<th data-options="field:'historyTotal',width:80,halign:'center'">历史统计量</th>
					<th data-options="field:'currentMonth',width:80,halign:'center'">本月统计量</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>
