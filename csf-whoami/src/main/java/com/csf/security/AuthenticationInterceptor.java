package com.csf.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor  {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception{
//        HttpSession session = request.getSession();

//        if (request.getServletPath().length() <= 1) {
//        	return true;
//        }
//        response.sendRedirect("/");
//        return false;
//        if(session == null){
//            response.sendRedirect("/test");
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
