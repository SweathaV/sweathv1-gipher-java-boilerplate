package com.stackroute.netflixzuulapigatewayserver.filter;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	System.out.println("Inside zuul API zuul Filter");
    	HttpServletRequest req = (HttpServletRequest) request;
    	HttpServletResponse res = (HttpServletResponse) response;
    	String authHeader = req.getHeader("Authorization");
    	
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
    	
    	if("OPTIONS".equals(req.getMethod())) {
    		res.setStatus(HttpServletResponse.SC_OK);
    		chain.doFilter(req, res);
    	} else {
    	if(authHeader ==  null || !authHeader.startsWith("Bearer ")) {
    		throw new ServletException("Authorization token is missing(zuulapi)");
    	}
    	String token = authHeader.substring(7);
    	//System.out.println("token"+token);
    	final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
    	req.setAttribute("claims", claims);
    	chain.doFilter(req, res);
    	}


    }
}
