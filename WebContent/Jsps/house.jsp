<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int target = Integer.parseInt(request.getParameter("target"));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>房屋管理</title>
	<link rel="stylesheet" type="text/css"href="easyUI/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="easyUI/css/style.css"/>
	<script type="text/javascript" src="easyUI/jquery.min.js"></script>
	<script type="text/javascript" src="easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyUI/locale/easyui-lang-zh_CN.js"></script>
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
	<div data-options="region:'center',title:'房屋管理'">
		<table class="easyui-datagrid" id="datagrid_employees"
			data-options="toolbar:'#toolbar_house',fit:true,striped:true,checkOnSelect:true,selectOnCheck:false,singleSelect:true">
			<thead>
				<tr>
					<th data-options="field:'selector',checkbox:true"></th>
					<th data-options="field:'ID',width:50,halign:'center'">房屋地址</th>
					<th data-options="field:'name',width:60,halign:'center'">房主姓名</th>
					<th data-options="field:'password',width:80,halign:'center'">房主身份证号</th>
					<th data-options="field:'phone',width:80,halign:'center'">房主联系方式</th>
					<th data-options="field:'company',width:120,halign:'center'">房主工作单位</th>
					<th data-options="field:'workshop',width:200,halign:'center'">房屋类型</th>
					<th data-options="field:'department',width:200,halign:'center'">房屋使用类型</th>
					<th data-options="field:'placeIDs',width:130,halign:'center'">居住人</th>
					<th data-options="field:'role',width:70,halign:'center'">采集人</th>
					<th data-options="field:'workTypeID',width:70,halign:'center'">采集时间</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar_house">
		<%@include file="../inc/panel/query.inc" %>
		</div>
	</div>

</body>
</html>
