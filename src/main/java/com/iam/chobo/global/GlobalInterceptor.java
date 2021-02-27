package com.iam.chobo.global;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) {
		logger.info("================ Before Method");

		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();

		logger.info("Bean: {}", method.getBean());
		logger.info("Method: {}", methodObj);
		return true;
	}
	
	@Override
	public void postHandle( HttpServletRequest request,
							HttpServletResponse response,
							Object handler,
							ModelAndView modelAndView) {
		logger.info("================ Method Executed");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, 
								Object handler, 
								Exception ex) {
		logger.info("================ Method Completed");
	}
}