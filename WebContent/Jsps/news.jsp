<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int target = Integer.parseInt(request.getParameter("target"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告</title>

<link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
<link href="editor/themes/default/default.css" rel="stylesheet" />
<script type="text/javascript" src="easyUI/jquery.min.js"></script>
<script type="text/javascript" src="easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyUI/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="datagrid_view/datagrid-detailview.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<script src="editor/kindeditor-min.js"></script>
<script src="editor/lang/zh_CN.js"></script>
		
</head>

<body class="easyui-layout">
	<div align="center"  data-options="region:'north',collapsible:false" title='' style="height:100px;background-color:#D6E6DE">
		<%@include file="../../inc/header.inc" %> 	
	</div>
	<div data-options="region:'west',collapsible:true" title='功能导航' style="width:120px;">
		<%@include file="../inc/menu_user.inc"%> 	
	</div>			
	<div data-options="region:'south'" style="height:50px;background-color:#D6E6DE" align="center">
		<%@include file="../inc/footer.inc" %> 	
	</div>				
	<div data-options="region:'east',title:'帮助',collapsible:true,collapsed:true,width:250" style="padding:10px;">
		<%@include file="../inc/help/notices.inc" %>
	</div>
	<div data-options="region:'center',title:'新闻列表'">
		<table  class="easyui-datagrid" id="datagrid_notices" data-options="toolbar:'#toolbar_notice',fit:true,striped:true,checkOnSelect:true,selectOnCheck:false,singleSelect:true,pagination:true">
			<thead>
				<tr>
					<th data-options="field:'title',width:200">新闻标题</th>
					<th data-options="field:'author',width:0,hidden:true">发布者ID</th>
					<th data-options="field:'authorName',width:80">发布者</th>
					<th data-options="field:'date',width:100">日期</th>
					<th data-options="field:'carteenName',width:100">派出所</th>
					
				</tr>
			</thead>
		</table>
		<div id="toolbar_notice" style="height:50px;line-height: 40px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" ">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" ">删除</a>
		</div>	
	</div>		
<!-- 添加公告对话框 -->
<div id="dlg_addNotice" class="easyui-dialog" title="公告" data-options="buttons: '#dlg-buttons',iconCls:'icon-notice2',draggable:false,closed:true,closable:true,modal:true" style="width:450px;height:400px">
		<table cellpadding='5px'>
			<tr >
				<td width=80px>新闻日期:</td>
				<td><input class="easyui-datebox"  id="date_append" name="date_append" /></td>
			</tr>
			<tr >
				<td>新闻标题:</td>
				<td><input class="easyui-textbox" id="title_append" name="title_append"  data-options="required:true"/></td>
			</tr>
			<tr>
				<td>新闻内容:</td>
				<td>
				 <textarea name="content_append" id="content_append" style="width: 100%; height: 200px; visibility: hidden;"></textarea>
				</td>
			</tr>
		</table>
</div>
		<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exchange'">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit'">关闭</a>
	</div>
<!-- 修改公告对话框 -->
<div id="dlg_editNotice" class="easyui-dialog" title="公告" data-options="buttons: '#dlg-buttons',iconCls:'icon-notice2',draggable:false,closed:true,closable:true,modal:true" style="width:450px;height:400px">
		<table cellpadding='5px'>
			<tr >
				<td width=80px>新闻日期:</td>
				<td><input class="easyui-datebox"  id="date_update" /></td>
			</tr>
			<tr >
				<td>新闻标题:</td>
				<td><input class="easyui-textbox" id="title_update" data-options="required:true"/></td>
			</tr>
			<tr >
				<td>新闻内容:</td>
				<td>
				 <textarea id="content_update" style="width: 100%; height: 200px; visibility: hidden;"></textarea>
				</td>
			</tr>
		</table>
</div>
		<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exchange'">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit'">关闭</a>
	</div>
</body>
</html>
