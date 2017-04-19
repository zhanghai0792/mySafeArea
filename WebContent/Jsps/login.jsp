<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="pojo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>平安小区管理系统</title>
<link rel="stylesheet" type="text/css"href="<%=path %>/easyUI/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/easyUI/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/easyUI/css/style.css"/>
<script type="text/javascript" src="<%=path %>/easyUI/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyUI/locale/easyui-lang-zh_CN.js"></script>
<script src="<%=path %>/js/cookie.js"></script>
<script src="<%=path %>/js/jquery.js"></script>
<script src="<%=path %>/js/bgstretcher.js"></script>
<script type="text/javascript" charset="utf-8">

function keyDown(e){
	var ev= window.event||e;
	//13是键盘上面固定的回车键
	if (ev.keyCode == 13) {
	//你要执行的方法
		document.form1.submit();
	}
}

$(document).ready(function(){	
	var role= getCookie('role');
	if(role == 1){
		//$("input[id='admin']").checked = true;
		document.getElementById('admin').checked = true;
	}else{
		document.getElementById('emploee').checked = true;
	}
	$("#USER_LOGINNAME").val(getCookie('user_loginName'));	
	//$("#USER_PASSWD").val(getCookie('user_password'));
	//$("input[name='role']:checked").val(getCookie('role'));	
	$("#USER_LOGINNAME").focus();
	  
        //  Initialize Backgound Stretcher	   
		$('.login_bgr').bgStretcher({
			images: ['<%=path %>/images/01.jpg', '<%=path %>/images/02.jpg', '<%=path %>/images/03.jpg', '<%=path %>/images/04.jpg', '<%=path %>/images/05.jpg'],
			imageWidth: 1024, 
			imageHeight: 400, 
			slideDirection: 'N',
			slideShowSpeed: 2000,
			transitionEffect: 'fade',
			sequenceMode: 'normal',
			buttonPrev: '',
			buttonNext: '',
			pagination: '',
			anchoring: 'left center',
			anchoringImg: 'left center'
		});
		
	});	
	function login(){
		var userId = $("#USER_LOGINNAME").val();
		var password = $("#USER_PASSWD").val();
		var role = $("input[name='role']:checked").val();
		$.post("/Order/LoginServlet",
		{
			userId:userId,
			password:password,
			role:role
		},function(result){
			var data = JSON.parse(result); 
			var success = data.success;
			if(!success){
				alert(data.msg);
				return;
			}
			if(document.getElementById('check').checked == true){
			//if($("input[name='check']:checked").val() == 1){
				setCookie('user_loginName', userId);
				//setCookie('user_password', password);
				setCookie('role',role);
			}else{
			delCookie('user_loginName');
			//delCookie('user_password');
			delCookie('role');
			//setCookie('role',$("input[name='role']:checked").attr("value"));				
			}
			
	    });
	}
	
	
</script>
  </head>
<!-- 登录原始登录-->
	  <body background="<%=path %>/images/login.jpg">
	    <form action="<%=path %>/user/Login" method="post">
			用户名<input type="text" name="phone" value="123456"/>
			密码<input type="password" name="password" value="000000"/>
			<input type="submit" value="登录"/>
		</form>
	  </body>
    
   <!-- 
  <body>
<div class="login_up">
    <img src="../images/login_logo.png" />
</div>
<div class="login_box_top"></div>
<div class="login">
  <form > 
  <div class="login_enter">
	  <div class="login_d">
	   <div class="login-box">
         <div class="mt">
             <img src="../images/login_p.png" />
         </div>
         <div class="mc">
         <div class="form">
         <div class="item_item-fore1">
              <div class="login-label1"></div>
              <input id="USER_LOGINNAME" name="eID" type="text" placeholder="用户名" Required=true class="idxt"/>
              <span id="show_text1"></span>
         </div>
         <div class="item_item-fore1">
              <div for="loginname" class="login-label2"></div>
              <input id="USER_PASSWD" name="ePassWord" type="password" placeholder="密码" Required=true class="idxt" onkeydown="keyDown(event)"/>
              <span id="show_text2"></span> 
         </div>
		 <div class="item_item-fore1">
		 	 &emsp;&emsp;
              <input id="emploee" type="radio" name="role" value="0" checked >职工
              <input id="admin" type="radio" name="role" value="1" >管理员
              <input id="check"type="checkbox" name="check" value="1" checked>记住帐号
         </div>
         <div class="item item-fore3" style="padding-left:30px;">
              <div class="safe">
              	  
              <div class="login-btn">					
			  <input id="btnLoginOk" type="button" class="button" onclick="login();" value="">
				 	 
              </div>

              </div>
           </div>
   		 </div>
       </div>
	   </div>
	 </div>
  </div>
  </form>
</div>
<div class="login_bgr"></div>
<div class="login_footer"><span class="c_l">2016  九江石化食堂订餐系统</span><span class="c_l">技术支持 <a href="http://www.gtkj.com.cn/">江西省国泰科技有限公司</a></span><span class="c_r">建议您使用IE8+、Google Chrome，分辨率1280*800及以上浏览本系统，获得更好用户体验。</span></div>
</body>
 -->
</html>
