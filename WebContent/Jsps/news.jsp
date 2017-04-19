<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="pojo.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	int target = Integer.parseInt(request.getParameter("target"));
	user user1 = (user) request.getSession().getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻</title>

<link rel="stylesheet" type="text/css"
	href="easyUI/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
<link href="editor/themes/default/default.css" rel="stylesheet" />
<script type="text/javascript" src="easyUI/jquery.min.js"></script>
<script type="text/javascript" src="easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyUI/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/qiniu.js"></script>
<script type="text/javascript" src="js/moxie.js"></script>
<script type="text/javascript" src="js/plupload.dev.js"></script>
<script src="editor/kindeditor-min.js"></script>
<script src="editor/lang/zh_CN.js"></script>


<script type="text/javascript">
var editIndex = undefined;
$(function(){
	loadNews();
});
//加载所有的新闻
function loadNews() {
	$.post("/mySafeArea/NewsServlet/getBasic",
		{
			timestamp:(new Date()).valueOf()
		},
		function(result){
			$('#datagrid_news').datagrid('loadData',{total:result.total, rows:result.datas});
		}
	
	);

}
//是否修改结束
function endEditing(){
	if (editIndex == undefined){
		return true;
	}

	if ($('#datagrid_news').datagrid('validateRow', editIndex)){
		$('#datagrid_news').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

//双击修改
function edit(rowIndex, rowData) {
	$('#dlg_editNew').dialog('open');
	$('#date_update').datebox("setValue",rowData.releaseTime);
	$('#title_update').textbox("setValue",rowData.title);
	editor_update.html(rowData.content);
	if (editIndex != rowIndex && endEditing()){
		$('#datagrid_news').datagrid('beginEdit', rowIndex);
		editIndex = rowIndex;
	}
}
//保存修改信息
function saveEdit() {
 var Qiniu_upload = function(f, token, key) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', "http://upload.qiniu.com/", true);
            var formData, startDate;
            formData = new FormData();
            if (key !== null && key !== undefined) formData.append('key', key);
            formData.append('token', token);
            formData.append('file', f);
            var taking;
            xhr.onreadystatechange = function(response) {
                if (xhr.readyState == 4 && xhr.status == 200 && xhr.responseText != "") {
                    var blkRet = JSON.parse(xhr.responseText);
                    /* console && console.log(blkRet);
                    console.log("上传成功OK!");
                    console.log(blkRet.hash);
                    console.log(blkRet.key);
                    console.log("上传完成OK!");*/
                    //$("#dialog").html(xhr.responseText).dialog(); 
                    //console.info(xhr);//返回的数据
                    var title = $('#title_update').val();
						var content = editor_update.html();
						var releaseTime = $('#date_update').datebox('getValue');
						if(content == ""){
							alert("请输入公告的内容");
							return;
						}
						$('#datagrid_news').datagrid('updateRow',{
						index: editIndex,
						row: {
							title:title,
							releaseName:"<%=user1.getName()%>",		
							releaseTime:releaseTime,
							releaseID:"<%=user1.getId()%>",
							policeName:"<%=user1.getPoliceName()%>",
							content:content
						}
					});
					var json_updated = $('#datagrid_news').datagrid('getSelected');
					json_updated["photos"]=[blkRet.key];
					//提交后台执行变更
						$.post("/mySafeArea/NewsServlet/update",
							{
								data:JSON.stringify(json_updated),
						       	timestamp:(new Date()).valueOf() //加时间戳，解决缓存问题    
					       },
					       function(result){
						       	//提交变更数据  
						        $('#datagrid_news').datagrid('acceptChanges');
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
					$('#dlg_editNew').dialog('close');
                    return blkRet;//返回上传成功后的数据
                    
				} else if (xhr.status != 200 && xhr.responseText) {
					return "";
				}
			};
			startDate = new Date().getTime();
			xhr.send(formData);
		};
		var message = "image/"+new Date().getTime() + ".jpg";
		$.post("/mySafeArea/servlet/TokenServlet", {
			message : message

		}, function(result) {
			var token1 = JSON.parse(result);
			var token = token1.token;
			//var token = "TFhMO08-YFcR5u1qg_rfX8O0Rm9rMX4OXsUigOob:4X3XGgJVO-sQsFT8Bk0ewm091-4=:eyJzY29wZSI6InhpYW95aS10ZXN0IiwiZGVhZGxpbmUiOjMyOTMyMjAzNzl9";
			if ($("#file2")[0].files.length > 0 && token != "") {
				Qiniu_upload($("#file2")[0].files[0], token, message);
				//$("#key").val() 资源名称 可以设置为需要的图片的名字，注意需要修改，方便从七牛获取
			} else {
				console && console.log("form input error");
				var title = $('#title_update').val();
					var content = editor_update.html();
					var releaseTime = $('#date_update').datebox('getValue');
					if(content == ""){
						alert("请输入新闻的内容");
						return;
					}
					$('#datagrid_news').datagrid('updateRow',{
					index: editIndex,
					row: {
						title:title,
						releaseName:"<%=user1.getName()%>",		
						releaseTime:releaseTime,
						releaseID:"<%=user1.getId()%>",
						policeName:"<%=user1.getPoliceName()%>",
						content:content
					}
				});
				var json_updated = $('#datagrid_news').datagrid('getSelected');
				//提交后台执行变更
					$.post("/mySafeArea/NewsServlet/update",
						{
							data:JSON.stringify(json_updated),
					       	timestamp:(new Date()).valueOf() //加时间戳，解决缓存问题    
				       },
				       function(result){
					       	//提交变更数据  
					        $('#datagrid_news').datagrid('acceptChanges');
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
				$('#dlg_editNew').dialog('close');
			//图片上传后，发送ajax 请求，将表单的数据各个获取并提交到服务器 
				//图片上传后，发送ajax 请求，将表单的数据各个获取并提交到服务器    

			}
		}

		);
	
}
//添加一条新闻
function append() {
	$('#dlg_addNew').dialog('open');
	$("#date_append").datebox("setValue",new Date().toDateString());
	$("#title_append").textbox("setValue","");
	document.getElementById('content_append').value = "";
}
//保存添加的新闻
$(document).ready(function() {
    var Qiniu_UploadUrl = "http://up.qiniu.com";
    var progressbar = $("#progressbar"),
        progressLabel = $(".progress-label");
    progressbar.progressbar({
        value: false,
        change: function() {
            progressLabel.text(progressbar.progressbar("value") + "%");
        },
        complete: function() {
            progressLabel.text("Complete!");
        }
    });
    $("#btn_upload").click(function() {
        
    })
});
function upLoad(){
	//API https://developer.qiniu.com/kodo/manual/1272/form-upload
	//例子    http://jsfiddle.net/gh/get/jquery/1.9.1/icattlecoder/jsfiddle/tree/master/ajaxupload?ref=developer.qiniu.com
		//普通上传
		// 资源下载 获取上传成功后的url 接口 https://developer.qiniu.com/kodo/manual/1655/download-public
        var Qiniu_upload = function(f, token, key) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', "http://upload.qiniu.com/", true);
            var formData, startDate;
            formData = new FormData();
            if (key !== null && key !== undefined) formData.append('key', key);
            formData.append('token', token);
            formData.append('file', f);
            var taking;
            xhr.onreadystatechange = function(response) {
                if (xhr.readyState == 4 && xhr.status == 200 && xhr.responseText != "") {
                    var blkRet = JSON.parse(xhr.responseText);
                    /* console && console.log(blkRet);
                    console.log("上传成功OK!");
                    console.log(blkRet.hash);
                    console.log(blkRet.key);
                    console.log("上传完成OK!");*/
                    //$("#dialog").html(xhr.responseText).dialog(); 
                    //console.info(xhr);//返回的数据
                    var title = $("#title_append").val();
					var content = editor_append.html();
					var date = $('#date_append').datebox('getValue');
					if(content == ""){
						alert("请输入公告的内容");
						return;
					}
					if (endEditing()){
				
						$('#datagrid_news').datagrid('insertRow',{index:0,row:{
							title:title,
							releaseName:"<%=user1.getName()%>",		
							releaseTime:date,
							releaseID:"<%=user1.getId()%>",
							policeName:"<%=user1.getPoliceName()%>",
							content : content
							//photos:"["+blkRet.key+"]"
							}
						});
						editIndex = 0;
						$('#datagrid_news').datagrid('selectRow', editIndex)
								.datagrid('beginEdit', 0);
					}
					var json_inserted = $('#datagrid_news').datagrid(
							'getSelected');
							json_inserted["photos"]=[blkRet.key];
					var target = JSON.stringify(json_inserted);
					
					//提交后台执行变更
					$.post("/mySafeArea/NewsServlet/add", {
						data : target,
						timestamp : (new Date()).valueOf()
					//加时间戳，解决缓存问题    
					}, function(result) {
						//提交变更数据  
						$('#datagrid_news').datagrid('acceptChanges');
						//提示
						$.messager.show({
							title : '公告维护',
							timeout : 3000,
							msg : '保存成功',
							width : 200,
							showType : 'slide'
						});
					});
					$('#dlg_addNew').dialog('close');
                    
                    return blkRet;//返回上传成功后的数据
                    
				} else if (xhr.status != 200 && xhr.responseText) {
					return "";
				}
			};
			startDate = new Date().getTime();
			xhr.send(formData);
		};
		var message = "image/"+new Date().getTime() + ".jpg";
		$.post("/mySafeArea/servlet/TokenServlet", {
			message : message

		}, function(result) {
			var token1 = JSON.parse(result);
			var token = token1.token;
			//var token = "TFhMO08-YFcR5u1qg_rfX8O0Rm9rMX4OXsUigOob:4X3XGgJVO-sQsFT8Bk0ewm091-4=:eyJzY29wZSI6InhpYW95aS10ZXN0IiwiZGVhZGxpbmUiOjMyOTMyMjAzNzl9";
			if ($("#file")[0].files.length > 0 && token != "") {
				Qiniu_upload($("#file")[0].files[0], token, message);
				//$("#key").val() 资源名称 可以设置为需要的图片的名字，注意需要修改，方便从七牛获取
			} else {
				console && console.log("form input error");
				   

		}
	}

	);

	}
	function remove() {
		var msg = "确定要删除吗？";
		if (confirm(msg) == false) {
			return;
		}
		var json_deleted = $('#datagrid_news').datagrid('getSelected');
		var index = $('#datagrid_news').datagrid('getRowIndex', json_deleted);
		$('#datagrid_news').datagrid('deleteRow', index);
		//提交后台执行变更
		$.post("/mySafeArea/NewsServlet/delete", {
			data : JSON.stringify(json_deleted),
			timestamp : (new Date()).valueOf()
		//加时间戳，解决缓存问题    
		}, function(result) {
			//提交变更数据  
			$('#datagrid_news').datagrid('acceptChanges');
			//提示
			$.messager.show({
				title : '公告维护',
				timeout : 3000,
				msg : '保存成功',
				width : 200,
				showType : 'slide'
			});
		});
	}

	//编辑器
	var editor_append;
	KindEditor.ready(function(K) {
		editor_append = K.create('textarea[id="content_append"]', {
			allowFileManager : true,
			resizeType : 1,
			allowPreviewEmoticons : false,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'link' ]
		});
	});
	var editor_update;
	KindEditor.ready(function(K) {
		editor_update = K.create('textarea[id="content_update"]', {
			allowFileManager : true,
			resizeType : 1,
			allowPreviewEmoticons : false,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'link' ]
		});
	});
</script>



</head>

<body class="easyui-layout">
	<div align="center" data-options="region:'north',collapsible:false"
		title='' style="height:100px;background-color:#D6E6DE">
		<%@include file="../../inc/header.inc"%>
	</div>
	<div data-options="region:'west',collapsible:true" title='功能导航'
		style="width:120px;">
		<%@include file="../inc/menu_user.inc"%>
	</div>
	<div data-options="region:'south'"
		style="height:50px;background-color:#D6E6DE" align="center">
		<%@include file="../inc/footer.inc"%>
	</div>
	<div
		data-options="region:'east',title:'帮助',collapsible:true,collapsed:true,width:250"
		style="padding:10px;">
		<%@include file="../inc/help/news.inc"%>
	</div>
	<div data-options="region:'center',title:'新闻列表'">
		<table class="easyui-datagrid" id="datagrid_news"
			data-options="toolbar:'#toolbar_notice',fit:true,striped:true,checkOnSelect:true,selectOnCheck:false,singleSelect:true,pagination:true,onDblClickRow:edit">
			<thead>
				<tr>
					<th data-options="field:'title',width:200">新闻标题</th>
					<th data-options="field:'releaseID',width:0,hidden:true">发布者ID</th>
					<th data-options="field:'releaseName',width:80">发布者</th>
					<th data-options="field:'releaseTime',width:150">日期</th>
					<th data-options="field:'policeName',width:100">派出所</th>

				</tr>
			</thead>
		</table>
		<div id="toolbar_notice" style="height:50px;line-height: 40px;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onclick="append()">添加</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove'" onclick="remove()">删除</a>
		</div>
	</div>
	<!-- 添加公告对话框 -->
	<div id="dlg_addNew" class="easyui-dialog" title="新闻"
		data-options="buttons: '#dlg-buttons',iconCls:'icon-notice2',draggable:false,closed:true,closable:true,modal:true"
		style="width:500px;height:450px">
		<form method="post" action="http://upload.qiniu.com/"
			enctype="multipart/form-data">
			<table cellpadding='5px'>
				<tr>
					<td><input name="token" type="hidden"
						value="TFhMO08-YFcR5u1qg_rfX8O0Rm9rMX4OXsUigOob:4X3XGgJVO-sQsFT8Bk0ewm091-4=:eyJzY29wZSI6InhpYW95aS10ZXN0IiwiZGVhZGxpbmUiOjMyOTMyMjAzNzl9">
					</td>
				</tr>
				<tr>
					<td width=80px>新闻日期:</td>
					<td><input class="easyui-datebox" id="date_append"
						name="date_append" />
					</td>
				</tr>
				<tr>
					<td>新闻标题:</td>
					<td><input class="easyui-textbox" id="title_append"
						name="title_append" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<td>新闻内容:</td>
					<td><textarea name="content_append" id="content_append"
							style="width: 100%; height: 200px; visibility: hidden;"></textarea>
					</td>
				</tr>
				<tr>
					<td>新闻图片:</td>
					<td><input id="file" name="file" type="file"
						style="width:240px"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-exchange'" onclick="upLoad()">确定</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-exit'"
			onclick="javascript:$('#dlg_addNew').dialog('close')">关闭</a>
	</div>
	<!-- 修改公告对话框 -->
	<div id="dlg_editNew" class="easyui-dialog" title="新闻"
		data-options="buttons: '#dlg-buttons',iconCls:'icon-notice2',draggable:false,closed:true,closable:true,modal:true"
		style="width:500px;height:450px">
		<table cellpadding='5px'>
			<tr>
				<td width=80px>新闻日期:</td>
				<td><input class="easyui-datebox" id="date_update" />
				</td>
			</tr>
			<tr>
				<td>新闻标题:</td>
				<td><input class="easyui-textbox" id="title_update"
					data-options="required:true" />
				</td>
			</tr>
			<tr>
				<td>新闻内容:</td>
				<td><textarea id="content_update"
						style="width: 100%; height: 200px; visibility: hidden;"></textarea>
				</td>
			</tr>
			<tr>
				<td>新闻图片:</td>
				<td>
					<input id="file2" name="file2" type="file"
						style="width:240px">
					</td>
			</tr>
		</table>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" id="uploadImg1" class="easyui-linkbutton"
			data-options="iconCls:'icon-exchange'" onclick="saveEdit()">确定</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-exit'"
			onclick="javascript:$('#dlg_editNew').dialog('close')">关闭</a>
	</div>


</body>
</html>
