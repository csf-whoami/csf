package com.csf.whoami.service;

import java.util.Map;

import com.csf.base.domain.request.ConfirmGroupInfo;

public interface EmailService {
    /**
     * Send email confirm create new Group.
     * 
     * @param info
     * @return
     */
    boolean sendEmailConfirmGroup(ConfirmGroupInfo info, String pincode);

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
