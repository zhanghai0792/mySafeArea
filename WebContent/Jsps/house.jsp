<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="pojo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int target = Integer.parseInt(request.getParameter("target"));
user user1 = (user)request.getSession().getAttribute("user");
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
<script type="text/javascript">
$(function() {
	loadHouses();
});

function loadHouses() {
	$.post("/mySafeArea/HouseServlet/getBasic",
		{
			timestamp:(new Date()).valueOf()	
		},
		function(result){
			$('#datagrid_house').datagrid('loadData',{total:result.total,rows:result.datas});
		}
	
	);
}
function searchRecord() {
	var webCondition = $("#cond_hidden").val();
	$.post("/mySafeArea/HouseServlet/getBasic",
		{
			webCondition:webCondition
		},
		function(result){
			$('#datagrid_house').datagrid('loadData', {total:result.total, rows:result.datas});
		}
	);
}

//房屋类型转化字符串
function typeFormatter(value,rowData, rowIndex){
	if(value == 0){
		return "自住";
	}else if(value == 1) {
		return "出租";
	}else if(value == 2){
		return "闲置";
	}
}

//导出exl
function exportExcel() {
	var rows = $('#datagrid_house').datagrid('getRows');
	try{          
        var app = new ActiveXObject('Excel.Application');    }catch(e)   
    {   
        alert(e+', 原因分析: 浏览器安全级别较高导致不能创建Excel对象或者客户端没有安装Excel软件');   
          return;   
    }     
	app.visible = true;
	var book = app.Workbooks.Add();
	var sheet = book.ActiveSheet;
	
	sheet.Cells(1,1).value = "房屋信息"; 
	sheet.Range("A1", "E1").MergeCells = true; 
	sheet.Cells(1,1).HorizontalAlignment = 3;
	sheet.Cells(1,1).Font.Size = 18;    
	sheet.Rows(1).RowHeight = 40;
	
	sheet.Cells(2,1).value = "房屋地址"; 	
	sheet.Cells(2,2).value = "房屋用途";
	sheet.Cells(2,3).value = "使用形式";
	sheet.Cells(2,4).value = "房主姓名";
	sheet.Cells(2,5).value = "房主身份证号";
	
	
	sheet.Columns(1).ColumnWidth = 15;
	sheet.Columns(2).ColumnWidth = 8;
	sheet.Columns(3).ColumnWidth = 15;
	sheet.Columns(4).ColumnWidth = 8;
	sheet.Columns(5).ColumnWidth = 15;	
	sheet.Rows(2).RowHeight = 25;
	sheet.Range("A2","E2").HorizontalAlignment = 3;
	//sheet.Range("A3","E3").Interior.ColorIndex = 8; 
	for(var i=0; i<rows.length; i++){		
		sheet.Cells(i+3,1).value = rows[i].address;	
		if(rows[i].type == 0){
			sheet.Cells(i+3,2).value = "自住";
		}else if(rows[i].type == 1) {
			sheet.Cells(i+3,2).value = "出租";
		}else if(rows[i].type == 2){
			sheet.Cells(i+3,2).value = "闲置";
		}
		sheet.Cells(i+3,3).value = rows[i].genre;	
		sheet.Cells(i+3,4).value = rows[i].owner;
		sheet.Cells(i+3,5).value = "'"+rows[i].ownerCardID;
		
		
		sheet.Cells(i+3,1).WrapText=true;
		sheet.Cells(i+3,2).WrapText=true;
		sheet.Cells(i+3,3).WrapText=true;
		sheet.Cells(i+3,4).WrapText=true;
		sheet.Cells(i+3,5).WrapText=true;
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
 		url:"/mySafeArea/HouseServlet/importData",
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


function showResident(rowIndex, rowData) {
	var total = rowData.residents.length;
	$('#dlg_resident').dialog('open');
	$('#datagrid_residents').datagrid('loadData',{total:total, rows:rowData.residents});

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
		<%@include file="../inc/help/house.inc"%>
	</div>
	<div data-options="region:'center',title:'房屋管理'">
		<table class="easyui-datagrid" id="datagrid_house"
			data-options="toolbar:'#toolbar_house',fit:true,striped:true,checkOnSelect:true,selectOnCheck:false,singleSelect:true,onDblClickRow:showResident">
			<thead>
				<tr>
					<th data-options="field:'selector',checkbox:true"></th>
					<th data-options="field:'address',width:150,halign:'center'">房屋地址</th>
					<th data-options="field:'owner',width:100,halign:'center'">房主姓名</th>
					<th data-options="field:'ownerCardID',width:150,halign:'center'">房主身份证号</th>
					<th data-options="field:'phone',width:100,halign:'center'">房主联系方式</th>
					<th data-options="field:'ownerWorks',width:120,halign:'center'">房主工作单位</th>
					<th data-options="field:'genre',width:100,halign:'center'">房屋类型</th>
					<th data-options="field:'type',width:70,halign:'center',formatter:typeFormatter">房屋用途</th>
					<th data-options="field:'adderName',width:70,halign:'center'">采集人</th>
					<th data-options="field:'createDate',width:100,halign:'center'">采集时间</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar_house">
			<%String key_json = "json/key_app_house.json"; %>
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
<!-- 居住人信息dialog -->
	<div id="dlg_resident" class="easyui-dialog" title="居住人信息" data-options="buttons: '#dlg-buttons',closable:false,closed:true"  style="width:850px;height:400px;padding:10px;">
			<table class="easyui-datagrid" id="datagrid_residents"
			data-options="fit:true,striped:true,checkOnSelect:true,selectOnCheck:false,singleSelect:true">
			<thead>
				<tr>
					<th data-options="field:'pCategory',width:80,halign:'center'">人口管理类别</th>
					<th data-options="field:'name',width:70,halign:'center'">姓名</th>
					<th data-options="field:'birthdy',width:100,halign:'center'">出生日期</th>
					<th data-options="field:'nation',width:70,halign:'center'">民族</th>
					<th data-options="field:'cardID',width:150,halign:'center'">身份证号</th>
					<th data-options="field:'work',width:150,halign:'center'">所属单位</th>
					<th data-options="field:'adderName',width:70,halign:'center'">采集人</th>
					<th data-options="field:'createDate',width:100,halign:'center'">采集时间</th>
				</tr>
			</thead>
		</table>
		</div>	
		<div id="dlg-buttons">
			 <a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-exit'"
			onclick="javascript:$('#dlg_resident').dialog('close')">关闭</a>
	</div>
</body>
</html>
