<input class="easyui-combobox" style="width:100px;" id="cmb_key" data-options="onSelect:changeKey,url:'<%=key_json %>',valueField:'id',textField:'text',editable:false"/>
<input class="easyui-combobox" style="width:50px;" id="cmb_relation" data-options="url:'json/relation.json',valueField:'id',textField:'text',editable:false"/>
<span id="span_value"><input class="easyui-textbox" id="value" /></span>
<a class="easyui-splitbutton" data-options="plain:false,menu:'#mm2',iconCls:'icon-ok'" onclick="addCondition()">增加条件</a>
<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchRecord()" >查找</a>
&emsp;&emsp;查询条件<input id="cond_show" class="easyui-textbox" disabled=true; style="width:200px;" />
<input id="cond_hidden" type="text" style="width:300px;display:none" />
<a class="easyui-linkbutton" iconCls="icon-reset" onclick="resetCondition()" >重置查询</a>

 <div id="mm2" style="width:50px;">
	<div onclick="setLogicRelation(1)">与</div>
	<div onclick="setLogicRelation(0)">或</div>
</div>

<script>
var valueElement = "textbox";//控件类型
var valueType = "string";//数据类型
var logicOp;//逻辑运算符
function changeKey(record){
	valueElement = record.valueElement;
	valueType = record.valueType;
	
	//重新渲染存放“值”的组件
	if(valueElement == "textbox"){
		$("#span_value").html("<input id='value' class='easyui-textbox'>");
	}else if(valueElement == "datebox"){
		$("#span_value").html("<input id='value' class='easyui-datebox'>");
	}else if(valueElement == "combobox"){
		$("#span_value").html("<input id='value' class='easyui-combobox'>");		
		$("#value").combobox({
			url:record.url,
			valueField:record.valueField,
			textField:record.textField
		});	
	}else if(valueElement == "combotree"){
		$("#span_value").html("<input id='value' class='easyui-combotree'>");
		$("#value").combotree({
		  url:'json/item.json'
	    });
	}			
	$.parser.parse($("#span_value"));
	
}

//添加查询条件
function addCondition(){
	var key_hidden = $("#cmb_key").combobox('getValue');
	var key_show = $("#cmb_key").combobox('getText');
	var relation_hidden = $("#cmb_relation").combobox('getValue');
	var relation_show = $("#cmb_relation").combobox('getText');
	var value_hidden,value_show;
	if(valueElement == "textbox"){
		value_hidden = $("#value").textbox('getValue');//传递字符串型应该用单引号，但单引号不能传递，用特殊字符代替，后台需转换
		value_show = $("#value").textbox('getText');
	}else if(valueElement == "datebox"){
		value_hidden = $("#value").datebox('getValue');
		value_show = $("#value").datebox('getText');
	}else if(valueElement == "combobox"){
		value_hidden = $("#value").combobox('getValue');
		value_show = $("#value").combobox('getText');
	}else if(valueElement == "combotree"){
		value_hidden = $("#value").combotree('getValue');
		value_show = $("#value").combotree('getText');
	}
	
	//如果是模糊查询，则需要加通配符%
	if(relation_hidden == " like "){
		value_hidden = "%"+value_hidden+"%";
	} 
	
	//传递字符串型应该用单引号，但单引号不能传递，用特殊字符代替，后台需转换
	if(valueType == "string"){
		value_hidden = "@"+value_hidden+"@";
	}		

	//添加条件
	var cond_show = key_show+relation_show+value_show;
	var cond_hidden = key_hidden+" "+relation_hidden+" "+value_hidden;			
	if($("#cond_show").textbox('getValue').length > 0){
		cond_show = $("#cond_show").textbox('getValue')+(logicOp == 0?" 或 ":" 且 ")+cond_show;
		cond_hidden = $("#cond_hidden").val()+(logicOp == 0?" or ":" and ")+cond_hidden;
		$("#cond_show").textbox('setValue',cond_show);
		$("#cond_hidden").val(cond_hidden);
	}else{
		$("#cond_show").textbox('setValue',cond_show);
		$("#cond_hidden").val(cond_hidden);
	}
}

//重置查询条件
function resetCondition(){
	$("#cond_show").textbox('setValue',"");
	$("#cond_hidden").val("");
}

//选择逻辑运算“且”-“或”
function setLogicRelation(value){
	logicOp = value;
}
</script>