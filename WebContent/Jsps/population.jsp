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
$.post("/mySafeArea/residentServlet/getBasic",
		{
			timestamp:(new Date()).valueOf() //加时间戳，解决缓存问题    
	    },
		function(result){  
       		$('#datagrid_population').datagrid('loadData',{total:result.total,rows:result.datas});
	  	}
	);
}

function searchRecord() {
	var webCondition = $("#cond_hidden").val();
	$.post("/mySafeArea/residentServlet/getBasic",
		{
			webCondition:webCondition
		},
		function(result){
			$('#datagrid_population').datagrid('loadData', {total:result.total, rows:result.datas});
		}
	);
}

//导出exl
function exportExcel() {
	var rows = $('#datagrid_population').datagrid('getRows');
	try{          
        var app = new ActiveXObject('Excel.Application');    }catch(e)   
    {   
        alert(e+', 原因分析: 浏览器安全级别较高导致不能创建Excel对象或者客户端没有安装Excel软件');   
          return;   
    }     
	app.visible = true;
	var book = app.Workbooks.Add();
	var sheet = book.ActiveSheet;
	
	sheet.Cells(1,1).value = "人员管理"; 
	sheet.Range("A1", "I1").MergeCells = true; 
	sheet.Cells(1,1).HorizontalAlignment = 3;
	sheet.Cells(1,1).Font.Size = 18;    
	sheet.Rows(1).RowHeight = 40;
	sheet.Cells(2,1).value = "人口管理类别"; 	
	sheet.Cells(2,2).value = "姓名";
	sheet.Cells(2,3).value = "出生日期（起）";
	sheet.Cells(2,4).value = "出生日期（止）";
	sheet.Cells(2,5).value = "民族";
	sheet.Cells(2,6).value = "证件类型";
	sheet.Cells(2,7).value = "证件号码";
	sheet.Cells(2,8).value = "所属单位";
	sheet.Cells(2,9).value = "身份证号码";
	sheet.Columns(1).ColumnWidth = 10;
	sheet.Columns(2).ColumnWidth = 8;
	sheet.Columns(3).ColumnWidth = 15;
	sheet.Columns(4).ColumnWidth = 15;
	sheet.Columns(5).ColumnWidth = 8;	
	sheet.Columns(6).ColumnWidth = 8;	
	sheet.Columns(7).ColumnWidth = 20;	
	sheet.Columns(8).ColumnWidth = 20;	
	sheet.Columns(9).ColumnWidth = 20;	
	sheet.Rows(2).RowHeight = 25;
	sheet.Range("A2","I2").HorizontalAlignment = 3;//居中
	//sheet.Range("A3","E3").Interior.ColorIndex = 8; 
	
	for(var i=0; i<rows.length; i++){		
		sheet.Cells(i+3,1).value = rows[i].pCategory;	
		sheet.Cells(i+3,2).value = rows[i].name;
		sheet.Cells(i+3,3).value = rows[i].birthay;
		sheet.Cells(i+3,4).value = "";	
		sheet.Cells(i+3,5).value = rows[i].nation;	
		sheet.Cells(i+3,6).value = "身份证";	
		sheet.Cells(i+3,7).value = "'"+rows[i].cardID;	
		sheet.Cells(i+3,8).value = rows[i].work;	
		sheet.Cells(i+3,9).value = "'"+rows[i].cardID;	
		sheet.Cells(i+3,1).WrapText=true;
		sheet.Cells(i+3,2).WrapText=true;
		sheet.Cells(i+3,3).WrapText=true;
		sheet.Cells(i+3,4).WrapText=true;
		sheet.Cells(i+3,5).WrapText=true;
		sheet.Cells(i+3,6).WrapText=true;
		sheet.Cells(i+3,7).WrapText=true;
		sheet.Cells(i+3,8).WrapText=true;
		sheet.Cells(i+3,9).WrapText=true;
	}
	//app.Quit;
}

//导入数据
function importData() {
	var file = $("#file_download").filebox('getText');
	if(!file.endWith(".xls")){
		alert("提示","请选择xls文件");
		return;
	}
	
	$('#form_download').form('submit',{
 		url:"/mySafeArea/residentServlet/importData",
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
		<%@include file="../inc/help/population.inc"%>
	</div>
	<div data-options="region:'center',title:'人员管理'">
		<table class="easyui-datagrid" id="datagrid_population"
			data-options="toolbar:'#toolbar_population',fit:true,striped:true,checkOnSelect:true,selectOnCheck:false,singleSelect:true">
			<thead>
				<tr>
					<th data-options="field:'selector',checkbox:true"></th>
					<th data-options="field:'cardID',width:150,halign:'center'">身份证号</th>
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
			<a class="easyui-linkbutton" iconCls="icon-import" onclick="javascript:$('#dlg_importdata').dialog('open')">导入</a>
			<a class="easyui-linkbutton" iconCls="icon-xls" onclick="exportExcel()">导出</a>
		</div>
	</div>
	<!-- 导入数据dialog -->
	<div id="dlg_importdata" class="easyui-dialog" title="数据导入" data-options="closable:false,closed:true"  style="width:300px;height:150px;padding:10px;">
			<form id="form_download" method="post" enctype="multipart/form-data"  >
				<center>
				<input id="file_download" name="file" class="easyui-filebox" style="width:240px">
				<br/><br/>
				<a href="javascript:void(0)" onclick="importData()"  data-options="iconCls:'icon-import'" class="easyui-linkbutton" >导入数据</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit'"onclick="javascript:$('#dlg_importdata').dialog('close')">关闭</a>
				<br/><br/>
				<label id="msg"></label>
				</center>
			</form>
		</div>
</body>
</html>
