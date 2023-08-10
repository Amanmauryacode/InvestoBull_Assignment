package com.InvestoBull.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor{

	private static final String API_KEY_HEADER = "api-key";
	/**
	 * Api key
	 */
    private static final String VALID_API_KEY = "bvxshxsh"; 

    /**
     * Logic for verifying api-key
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		String apiKey = request.getParameter(API_KEY_HEADER);
		if (apiKey == null || !apiKey.equals(VALID_API_KEY)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API key");
            return false;
        }
        return true;
		
	}
}
