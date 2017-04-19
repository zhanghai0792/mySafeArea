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
   <title>系统维护</title>
<link rel="stylesheet" type="text/css"href="easyUI/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="easyUI/css/style.css"/>
<script type="text/javascript" src="easyUI/jquery.min.js"></script>
<script type="text/javascript" src="easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyUI/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">


//导入小区数据
function importData1() {
	var file = $("#file_download1").filebox('getText');
	if(!file.endWith(".xls") && !file.endWith(".xlsx")){
		alert("提示","请选择xls文件");
		return;
	}
	
	$('#form_download1').form('submit',{
 		url:"/mySafeArea/AreaServlet/importData",
		success:function(result){
			var data = JSON.parse(result);
			var success = data.success;
			if(success){
				$("#msg").html(data.msg);
			}else{
				$("#msg").html(data.msg);
			}
		}
	});
	
}
//导入用户数据
function importData2() {
	var file = $("#file_download2").filebox('getText');
	if(!file.endWith(".xls") && !file.endWith(".xlsx")){
		alert("提示","请选择xls文件");
		return;
	}
	
	$('#form_download2').form('submit',{
 		url:"/mySafeArea/UserServlet/importData",
		success:function(result){
			var data = JSON.parse(result);
			var success = data.success;
			if(success){
				$("#msg").html(data.msg);
			}else{
				$("#msg").html(data.msg);
			}
		}
	});
	
}
//导入派出所数据
function importData3() {
	var file = $("#file_download3").filebox('getText');
	if(!file.endWith(".xls") && !file.endWith(".xlsx")){
		alert("提示","请选择xls文件");
		return;
	}
	
	$('#form_download3').form('submit',{
 		url:"/mySafeArea/PoliceServlet/importData",
		success:function(result){
			var data = JSON.parse(result);
			var success = data.success;
			if(success){
				$("#msg").html(data.msg);
			}else{
				$("#msg").html(data.msg);
			}
		}
	});
	
}
 String.prototype.endWith=function(str){  
    if(str==null||str==""||this.length==0||str.length>this.length)  
      return false;  
    if(this.substring(this.length-str.length)==str)  
      return true;  
    else  
      return false; 
};

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
		<%@include file="../inc/help/car.inc"%>
	</div>
	<div data-options="region:'center',title:'系统维护管理'">
		
		<div id="toolbar_car">
			<a class="easyui-linkbutton" iconCls="icon-import"onclick="javascript:$('#dlg_importdata1').dialog('open')">小区导入</a>
			<a class="easyui-linkbutton" iconCls="icon-import"onclick="javascript:$('#dlg_importdata2').dialog('open')">用户导入</a>
			<a class="easyui-linkbutton" iconCls="icon-import"onclick="javascript:$('#dlg_importdata3').dialog('open')">派出所导入</a>
		</div>	
	</div>
<!-- 导入数据dialog -->
	<div id="dlg_importdata1" class="easyui-dialog" title="数据导入" data-options="closable:false,closed:true"  style="width:300px;height:150px;padding:10px;">
			<form id="form_download1" method="post" enctype="multipart/form-data"  >
				<center>
				<input id="file_download1" name="file" class="easyui-filebox" style="width:240px">
				<br/><br/>
				<a href="javascript:void(0)" onclick="importData1()"  data-options="iconCls:'icon-import'" class="easyui-linkbutton" >导入数据</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit'"onclick="javascript:$('#dlg_importdata').dialog('close')">关闭</a>
				<br/><br/>
				<label id="msg"></label>
				</center>
			</form>
		</div>
<!-- 导入数据dialog -->
	<div id="dlg_importdata2" class="easyui-dialog" title="数据导入" data-options="closable:false,closed:true"  style="width:300px;height:150px;padding:10px;">
			<form id="form_download2" method="post" enctype="multipart/form-data"  >
				<center>
				<input id="file_download2" name="file" class="easyui-filebox" style="width:240px">
				<br/><br/>
				<a href="javascript:void(0)" onclick="importData2()"  data-options="iconCls:'icon-import'" class="easyui-linkbutton" >导入数据</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit'"onclick="javascript:$('#dlg_importdata').dialog('close')">关闭</a>
				<br/><br/>
				<label id="msg"></label>
				</center>
			</form>
		</div>
<!-- 导入数据dialog -->
	<div id="dlg_importdata3" class="easyui-dialog" title="数据导入" data-options="closable:false,closed:true"  style="width:300px;height:150px;padding:10px;">
			<form id="form_download3" method="post" enctype="multipart/form-data"  >
				<center>
				<input id="file_download3" name="file" class="easyui-filebox" style="width:240px">
				<br/><br/>
				<a href="javascript:void(0)" onclick="importData3()"  data-options="iconCls:'icon-import'" class="easyui-linkbutton" >导入数据</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit'"onclick="javascript:$('#dlg_importdata').dialog('close')">关闭</a>
				<br/><br/>
				<label id="msg"></label>
				</center>
			</form>
		</div>
</body>
</html>
