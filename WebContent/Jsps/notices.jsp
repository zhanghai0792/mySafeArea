<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="pojo.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int target = Integer.parseInt(request.getParameter("target"));
	user user1 = (user)request.getSession().getAttribute("user");
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



<script type="text/javascript">
var editIndex = undefined;
$(function(){
	loadNotices();
});
//加载所有的公告
function loadNotices() {
	$.post("/mySafeArea/NoticeServlet/getBasic",
	{
		
		timestamp:(new Date()).valueOf()
	},
	function(result){
		$('#datagrid_notices').datagrid('loadData',{total:result.total, rows:result.datas});
	}
	);
}
//是否修改结束
function endEditing(){
	if (editIndex == undefined){
		return true;
	}

	if ($('#datagrid_notices').datagrid('validateRow', editIndex)){
		$('#datagrid_notices').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

//添加双击一行公告事件
function edit(rowIndex,rowData){
	$('#dlg_editNotice').dialog('open');
	$("#date_update").datebox("setValue", rowData.releaseTime);
	$("#title_update").textbox("setValue",rowData.title);
	editor_update.html(rowData.content);
	if (editIndex != rowIndex && endEditing()){
		$('#datagrid_notices').datagrid('beginEdit', rowIndex);
		editIndex = rowIndex;
	}
}
//保存修改
function saveEdit() {
	var title = $("#title_update").val();
	var content = editor_update.html();
	var date = $('#date_update').datebox('getValue');
	if(content == ""){
		alert("请输入公告的内容");
		return;
	}
$('#datagrid_notices').datagrid('updateRow',{
	index: editIndex,
	row: {
		title:title,
		releaseName:"<%=user1.getName()%>",		
		releaseTime:date,
		releaseID:"<%=user1.getId()%>",
		policeName:"<%=user1.getPoliceName()%>",
		content:content
	}
});
var json_updated = $('#datagrid_notices').datagrid('getSelected');
//提交后台执行变更
	$.post("/mySafeArea/NoticeServlet/update",
		{
			data:JSON.stringify(json_updated),
	       	timestamp:(new Date()).valueOf() //加时间戳，解决缓存问题    
       },
       function(result){
	       	//提交变更数据  
	        $('#datagrid_notices').datagrid('acceptChanges');
       		//提示
       		$.messager.show({
				title:'公告维护',
				timeout:3000,
				msg:'保存成功',
				width:200,
				showType:'slide'
			});
	  }
	);	
$('#dlg_editNotice').dialog('close');
}

//添加一条新的公告
function addNotice() {
	$('#dlg_addNotice').dialog('open');
	$("#date_append").datebox("setValue", new Date().toDateString());
	$("#title_append").textbox("setValue","");
	document.getElementById('content_append').value = "";
}
//保存添加的公告
function saveAppend() {
	var title = $("#title_append").val();
	var content = editor_append.html();
	var date = $('#date_append').datebox('getValue');
	if(content == ""){
		alert("请输入公告的内容");
		return;
	}
	if (endEditing()){

		$('#datagrid_notices').datagrid('insertRow',{index:0,row:{
			title:title,
			releaseName:"<%=user1.getName()%>",		
			releaseTime:date,
			releaseID:"<%=user1.getId()%>",
			policeName:"<%=user1.getPoliceName()%>",
			content:content
			}
		});
		editIndex = 0;
		$('#datagrid_notices').datagrid('selectRow', editIndex).datagrid('beginEdit', 0);
	}
	var json_inserted = $('#datagrid_notices').datagrid('getSelected');
	//提交后台执行变更
	$.post("/mySafeArea/NoticeServlet/add",
		{
			data:JSON.stringify(json_inserted),
	       	timestamp:(new Date()).valueOf() //加时间戳，解决缓存问题    
       },
       function(result){
	       	//提交变更数据  
	        $('#datagrid_notices').datagrid('acceptChanges');
       		//提示
       		$.messager.show({
				title:'公告维护',
				timeout:3000,
				msg:'保存成功',
				width:200,
				showType:'slide'
			});
	  }
	);	
	$('#dlg_addNotice').dialog('close');
}

function deleteNotice(){
	var msg = "确定要删除吗？";
	if(confirm(msg) == false){
		return;
	}
	var json_deleted = $('#datagrid_notices').datagrid('getSelected');
	var index = $('#datagrid_notices').datagrid('getRowIndex',json_deleted);
	$('#datagrid_notices').datagrid('deleteRow',index);
	//提交后台执行变更
	$.post("/mySafeArea/NoticeServlet/delete",
		{
			data:JSON.stringify(json_deleted),
	       	timestamp:(new Date()).valueOf() //加时间戳，解决缓存问题    
       },
       function(result){
	       	//提交变更数据  
	        $('#datagrid_notices').datagrid('acceptChanges');
       		//提示
       		$.messager.show({
				title:'公告维护',
				timeout:3000,
				msg:'保存成功',
				width:200,
				showType:'slide'
			});
	  }
	);	
}

 //编辑器
var editor_append;
KindEditor.ready(function (K) {
     editor_append = K.create('textarea[id="content_append"]', {
         allowFileManager: true,
         resizeType: 1,
        allowPreviewEmoticons: false,
         items: [
             'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
            'insertunorderedlist', '|', 'emoticons', 'image', 'link']
    });
});
var editor_update;
KindEditor.ready(function (K) {
     editor_update = K.create('textarea[id="content_update"]', {
         allowFileManager: true,
         resizeType: 1,
        allowPreviewEmoticons: false,
         items: [
             'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
            'insertunorderedlist', '|', 'emoticons', 'image', 'link']
    });
});
</script>		
		
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
	<div data-options="region:'center',title:'公告列表'">
		<table  class="easyui-datagrid" id="datagrid_notices" data-options="toolbar:'#toolbar_notice',fit:true,striped:true,checkOnSelect:true,selectOnCheck:false,singleSelect:true,pagination:true,onDblClickRow: edit">
			<thead>
				<tr>
					<th data-options="field:'title',width:300">公告标题</th>
					<th data-options="field:'releaseName',width:80">发布者</th>
					<th data-options="field:'releaseID',width:0,hidden:true">发布者ID</th>
					<th data-options="field:'releaseTime',width:100">日期</th>
					<th data-options="field:'policeName',width:100">派出所</th>
					
				</tr>
			</thead>
		</table>
		<div id="toolbar_notice" style="height:50px;line-height: 40px;">
			&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addNotice()">添加</a>
			&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteNotice()" >删除</a>
		</div>	
	</div>		
<!-- 添加公告对话框 -->
<div id="dlg_addNotice" class="easyui-dialog" title="公告" data-options="buttons: '#dlg-buttons',iconCls:'icon-notice2',draggable:false,closed:true,closable:true,modal:true" style="width:450px;height:400px">
		<table cellpadding='5px'>
			<tr >
				<td width=80px>公告日期:</td>
				<td><input class="easyui-datebox"  id="date_append" name="date_append" /></td>
			</tr>
			<tr >
				<td>公告标题:</td>
				<td><input class="easyui-textbox" id="title_append" name="title_append"  data-options="required:true"/></td>
			</tr>
			<tr>
				<td>公告内容:</td>
				<td>
				 <textarea name="content_append" id="content_append" style="width: 100%; height: 200px; visibility: hidden;"></textarea>
				</td>
			</tr>
		</table>
</div>
		<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exchange'"onclick="saveAppend()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit'"onclick="javascript:$('#dlg_addNotice').dialog('close')">关闭</a>
	</div>
<!-- 修改公告对话框 -->
<div id="dlg_editNotice" class="easyui-dialog" title="公告" data-options="buttons: '#dlg-buttons',iconCls:'icon-notice2',draggable:false,closed:true,closable:true,modal:true" style="width:450px;height:400px">
		<table cellpadding='5px'>
			<tr >
				<td width=80px>公告日期:</td>
				<td><input class="easyui-datebox"  id="date_update" /></td>
			</tr>
			<tr >
				<td>公告标题:</td>
				<td><input class="easyui-textbox" id="title_update" data-options="required:true"/></td>
			</tr>
			<tr >
				<td>公告内容:</td>
				<td>
				 <textarea id="content_update" style="width: 100%; height: 200px; visibility: hidden;"></textarea>
				</td>
			</tr>
		</table>
</div>
		<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exchange'"onclick="saveEdit()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit'"onclick="javascript:$('#dlg_editNotice').dialog('close')">关闭</a>
	</div>
</body>
</html>
