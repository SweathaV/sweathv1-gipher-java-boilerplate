package com.stackroute.netflixzuulapigatewayserver.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ZuulLoggingFilter extends ZuulFilter{

	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}


	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		RequestContext req = RequestContext.getCurrentContext();
		HttpServletRequest request = req.getRequest();
		System.out.println("request -> " + request + "request uri ->" + request.getRequestURL().toString());
		return null;
	}


	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}


	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	

}
