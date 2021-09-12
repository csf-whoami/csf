package com.csf.whoami.base.util;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtils {

    public static HttpSession currentSession() {
        Optional<HttpServletRequest> request = currentRequest();
        return request.isPresent() ? request.get().getSession() : null;
    }

    public static Optional<HttpServletRequest> currentRequest() {
        return Optional
                .ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }

    public static String currentPath() {
        Optional<HttpServletRequest> request = currentRequest();
        return request.isPresent() ? request.get().getRequestURL().toString() : null;
    }
}
