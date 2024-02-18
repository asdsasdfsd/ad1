package iss.sa.team2.ad.interceptor;

import java.io.IOException;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws IOException {
	    LOGGER.info("Intercepting: " + request.getRequestURI());
	    
	    HttpSession session = request.getSession();

	    if (session.getAttribute("userId") == null) {   
	      response.sendRedirect("/user/login");
	      return false;
	    }
	     
	    return true;
	}
	
	@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse
		response, Object handler, ModelAndView modelAndView) {
		LOGGER.info("postHandle");
	}
	
	@Override
		public void afterCompletion(HttpServletRequest request,
		HttpServletResponse response, Object handler, Exception ex) {
		LOGGER.info("afterCompletion");
	}
}
