package com.csf.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.csf.base.domain.RoleInfo;
import com.csf.base.domain.response.AuthenticationInfo;

public final class AuthenticationUtil {

    public static AuthenticationInfo getUser() {
        Optional<HttpServletRequest> request = getCurrentHttpRequest();
        if (request.isPresent()) {
            HttpSession session = request.get().getSession();
            Object userAdmin = session.getAttribute("admin");
            if (userAdmin == null) {
                return dummyUser();
            }
            if (userAdmin instanceof AuthenticationInfo) {
                return (AuthenticationInfo) session.getAttribute("admin");
            }
            return dummyUser();
        }
        return null;
    }

    public static HttpSession currentSession() {
        Optional<HttpServletRequest> request = getCurrentHttpRequest();
        return request.isPresent() ? request.get().getSession() : null;
    }

    private static Optional<HttpServletRequest> getCurrentHttpRequest() {
        return Optional
                .ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }

    private static AuthenticationInfo dummyUser() {
        AuthenticationInfo authInfo = new AuthenticationInfo();

        List<RoleInfo> roleInfos = new ArrayList<>();
        RoleInfo role = new RoleInfo();
        role.setRoleId("1");
        role.setRoleName("ADMIN");
        roleInfos.add(role);

        authInfo.setLoginName("Demo User");
        authInfo.setRoles(roleInfos);
//        authInfo.setToken(token);
        return authInfo;
    }
}
