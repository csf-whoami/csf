package com.csf.whoami.config;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.csf.whoami.base.exception.CustomError;
import com.csf.whoami.base.exception.CustomException;
import com.csf.whoami.base.exception.ResponseDataAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    /**
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     * @description filter internal
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String token = tokenProvider.resolveToken(req);
        try {
            if (token != null && tokenProvider.validateToken(token)) {
                Authentication auth = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (CustomException ex) {
            SecurityContextHolder.clearContext();
            ResponseDataAPI errorResponse = ResponseDataAPI.build();
            errorResponse.setSuccess(false);
            errorResponse.setError(Collections.singletonList(new CustomError(null, "S002", ex.getMessage())));
            res.setStatus(ex.getHttpStatus().value());
            res.setContentType("application/json");
            res.getWriter().write(convertObjectToJson(errorResponse));
            return;
        }

        chain.doFilter(req, res);
    }

    /**
     * @author Tuan
     * @param object
     * @return String
     * @throws JsonProcessingException
     * @description convert object to Json
     */
    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}