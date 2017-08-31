package com.ep.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.model.common.Log;

/**
 * 异常控制
 * 跳转页面
 */
@ControllerAdvice
public class MyExceptionHandler {
	private static final Log LOGGER = Log.getLog(MyExceptionHandler.class);

	@ExceptionHandler({Exception.class})
	public ModelAndView Exception(Exception ex){
		LOGGER.error("出错了异常",ex);
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("exception", ex);

		ApplicationContextUtils.removeUser();

		return mv;
	}
	
}
