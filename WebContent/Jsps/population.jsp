<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>人口管理</title>
<link rel="stylesheet" type="text/css"href="easyUI/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="easyUI/css/style.css"/>
<script type="text/javascript" src="easyUI/jquery.min.js"></script>
<script type="text/javascript" src="easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyUI/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
$(function(){
	loadPopulations();
});	

function loadPopulations() {
	$('#datagrid_population').datagrid({  
	    url:'/mySafeArea/residentServlet/getBasic',  
	    queryParams:{  
	       timestamp:(new Date()).valueOf() //加时间戳，解决缓存问题    
	    },
		method:'post'
	});
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
		<%@include file="../inc/help/population.inc"%>
	</div>
	<div data-options="region:'center',title:'人员管理'">
		<table class="easyui-datagrid" id="datagrid_population"
			data-options="toolbar:'#toolbar_population',fit:true,striped:true,checkOnSelect:true,selectOnCheck:false,singleSelect:true">
			<thead>
				<tr>
					<th data-options="field:'selector',checkbox:true"></th>
					<th data-options="field:'cardID',width:100,halign:'center'">身份证号</th>
					<th data-options="field:'name',width:60,halign:'center'">姓名</th>
					<th data-options="field:'nation',width:80,halign:'center'">民族</th>
					<th data-options="field:'phone',width:80,halign:'center'">电话</th>
					<th data-options="field:'adress',width:120,halign:'center'">地址</th>
					<th data-options="field:'houseName',width:200,halign:'center'">现居住地</th>
					<th data-options="field:'work',width:130,halign:'center'">所属单位</th>
					<th data-options="field:'adderName',width:200,halign:'center'">采集人</th>
					<th data-options="field:'createDate',width:130,halign:'center'">采集时间</th>
					
				</tr>
			</thead>
		</table>
		<div id="toolbar_population">
			  <% String key_json = "json/key_app_population.json"; %>
			<%@include file="../inc/panel/query.inc" %>
			<a class="easyui-linkbutton" iconCls="icon-xls">导入</a>
			<a class="easyui-linkbutton" iconCls="icon-ok">导出</a>
		</div>
	</div>

</body>
</html>
