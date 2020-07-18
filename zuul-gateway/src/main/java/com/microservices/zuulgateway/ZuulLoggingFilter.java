package com.microservices.zuulgateway;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public boolean shouldFilter() {
	return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("Logging Request...");
		// TODO Auto-generated method stub
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("", request.getRequestURI());
		System.out.println("Request Intercepted - " + request.getRequestURL());
		System.out.println("===================================");
		return null;
	}

}
