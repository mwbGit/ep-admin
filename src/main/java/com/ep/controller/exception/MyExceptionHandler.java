package com.ep.controller.exception;

import com.ep.controller.common.ServiceResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
	@ResponseBody
	public ServiceResponse Exception(Exception ex){
		LOGGER.error("出错了异常",ex);

		//业务异常
		if (ex instanceof BizException) {
			BizException bex = (BizException) ex;
			LOGGER.error("bussiness exception, errorCode:{}, errorMsg:{}, e:{}",
					bex.getCode(), bex.getMsg(), ExceptionUtils.getStackTrace(bex));

			return new ServiceResponse(bex.getCode(), bex.getMsg());
		}

		return ServiceResponse.ERROR;
	}

}
