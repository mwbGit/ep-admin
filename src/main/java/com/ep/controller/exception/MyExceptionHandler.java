package com.ep.controller.exception;

import com.ep.controller.common.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.model.common.Log;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常控制
 * 跳转页面
 */
@ControllerAdvice
public class MyExceptionHandler {
	private static final Log LOGGER = Log.getLog(MyExceptionHandler.class);

	@ExceptionHandler({Exception.class})
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ServiceResponse Exception(Exception ex){
		LOGGER.error("出错了异常",ex);
		ServiceResponse response = new ServiceResponse();
		response.setCode("-1");
		response.setMessage("系统错误");

		return response;
	}

}
