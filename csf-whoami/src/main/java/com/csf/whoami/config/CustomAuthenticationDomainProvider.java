package com.csf.whoami.config;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.csf.base.domain.response.AuthenticationInfo;
import com.csf.whoami.service.UserService;

@Component
public class CustomAuthenticationDomainProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    /**
     * @param authentication
     * @return
     * @throws AuthenticationException
     * @desctiption Custom handle authentication Clinic
     */
    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthenticationDomainToken authenticationCustom = (CustomAuthenticationDomainToken) authentication;
        String loginId = authenticationCustom.getPrincipal().toString();
        String password = authenticationCustom.getCredentials().toString();
        String networkId = authenticationCustom.getNetworkId();
        AuthenticationInfo user = userService.getAuthenticationInfo(loginId);
        if (user == null) {
            return null;
        } else {
            if (bcryptEncoder.matches(password, user.getToken())) {
                Set<SimpleGrantedAuthority> list_authorities = new HashSet<>();
                user.getRoles().forEach(role -> {
                    list_authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
                });
                return new CustomAuthenticationDomainToken(user.getLoginName(), password, list_authorities, networkId);
            }
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
