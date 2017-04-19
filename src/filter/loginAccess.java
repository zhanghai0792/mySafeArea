package filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.userLogin.currentUser;
import json.jsonResult;
import pojo.user;
import util.JsonUtil;
import util.ListUtil;
import util.StringUtil;

/**
 * Servlet Filter implementation class loginAccess
 */
public class loginAccess implements Filter {

    /**
     * Default constructor. 
     */
    public loginAccess() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request1, ServletResponse response1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)request1;
		HttpServletResponse response=(HttpServletResponse)response1;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		if((StringUtil.isNotEmpty(request.getParameter("method"))&&StringUtil.isNotEmpty(request.getParameter("params")))||StringUtil.isNotEmpty(request.getParameter("message"))){	
			//如果是android的平台放行
			chain.doFilter(request, response);
		}else{
			//如果是web端则要拦截判断权限
			String acceUrl=request.getRequestURI();
			String webAppPath=request.getContextPath();
			//如果是第一次登陆(/index.htm,/user/Login,/img/,/js)放行，
			if(webAppPath.equals(acceUrl)||(webAppPath+"/").equals(acceUrl)||(webAppPath+"/index.htm").equals(acceUrl)||acceUrl.startsWith(webAppPath+"/user/Login")||acceUrl.startsWith(webAppPath+"/img")||acceUrl.startsWith(webAppPath+"/js")
					||acceUrl.startsWith(webAppPath+"/Jsps/login.jsp")||acceUrl.startsWith(webAppPath+"/easyUI")||acceUrl.startsWith(webAppPath+"/images")||acceUrl.startsWith(webAppPath+"/inc")||acceUrl.startsWith(webAppPath+"/json")){
				chain.doFilter(request, response);
			}else{
				//否则cookie判断
				Cookie[] cookies=request.getCookies();
		        if(ListUtil.isEmpty(cookies)){
		        	response.setContentType("application/json;charset=UTF-8");
		        	jsonResult json=new jsonResult(false, "没有Cookie登录信息");
					responseWriteJosn(response,json);
					return;
		        }
				//cookie用结构存储"userId;-userName;-userType";
				String cookieValue=null;
				for(Cookie c:cookies){
					//System.out.println(c.getName());
					if("mySafeAreaUser".equals(c.getName())){
						cookieValue=c.getValue();
						break;
					}
				}
				if(StringUtil.isNotEmpty(cookieValue)){
					//cookie找到了
					//要转成user对象处理
					//要做一个汉字的编码处理
					String cookieZ=URLDecoder.decode(cookieValue,"UTF-8");;
					String[] key=cookieZ.split("<>");
					if(key==null||key.length!=4){
						jsonResult json=new jsonResult(false, "登录cookie校验格式不正确，请重新登录");
						responseWriteJosn(response,json);
					}else{
						user user=new user();
						user.setId(Integer.parseInt(key[0]));
						user.setName(key[1]);
						user.setType(Integer.parseInt(key[2]));
						user.setPoliceID(Integer.parseInt(key[3]));
						currentUser.login(user);
						chain.doFilter(request, response);
						currentUser.exit();
					}
					
				}else{
					//没有登录,进入登录页面
					request.getRequestDispatcher("/Jsps/login.jsp").forward(request,response);
				}
			}
			
		}
		
		
	}

	private void responseWriteJosn(HttpServletResponse output,jsonResult jsonResult)throws IOException, ServletException{
		output.setContentType("application/json;charset=UTF-8");
		String json= JsonUtil.getJsonString(jsonResult);
		   PrintWriter out = output.getWriter();
		   out.print(json);
		   out.flush();
		   out.close();
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
