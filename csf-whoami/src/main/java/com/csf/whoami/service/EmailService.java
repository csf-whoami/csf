package com.csf.whoami.service;

import java.util.Map;

import com.csf.whoami.database.dto.RequestSearchGroup;

public interface EmailService {
    /**
     * Send email confirm create new Group.
     * 
     * @param info
     * @return
     */
    boolean sendEmailConfirmGroup(RequestSearchGroup info, String pincode);

    /**
     * Send email notice to User.
     * 
     * @param email
     * @param template
     * @return
     */
    boolean sendEmailNotice(Long userId, String template, Map<String, String> values);

    boolean sendEmailSystemNotice(String template, Map<String, String> values);
}
