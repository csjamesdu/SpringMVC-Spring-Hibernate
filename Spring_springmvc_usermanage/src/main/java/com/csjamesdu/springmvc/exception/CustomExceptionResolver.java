package com.csjamesdu.springmvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		
		CustomException customException=null;		
		if(exception instanceof CustomException){
			customException = (CustomException)exception;
		}else{
			customException = new CustomException("Unknown Error");
		}
		
		String errorMessage = customException.getErrorMessage();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", errorMessage);
		modelAndView.setViewName("errorPage");
		
		return modelAndView;
	}

}
