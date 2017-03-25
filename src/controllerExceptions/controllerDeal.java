package controllerExceptions;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import json.jsonResult;

@ControllerAdvice
public class controllerDeal {
	Logger logger=Logger.getLogger(controllerDeal.class);
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public jsonResult handleException(Exception ex){
		ex.printStackTrace();
		logger.error(ex);
		jsonResult json=new jsonResult(false,"操作失败&nbsp;&nbsp;错误提示:["+ex.getMessage()+"]");
		return json;
	}
}
