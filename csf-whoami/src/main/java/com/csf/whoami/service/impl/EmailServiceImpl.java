package com.csf.whoami.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.csf.base.domain.request.ConfirmGroupInfo;
import com.csf.whoami.service.EmailService;
import com.csf.whoami.service.TemplateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final TemplateService templateService;

    @Override
    public boolean sendEmailConfirmGroup(ConfirmGroupInfo info, String pincode) {
        Map<String, Object> params = new HashMap<>();
        params.put("url", info.getGroupURL());
        params.put("pin", pincode);
        return templateService.sendMail(info.getEmail(), "mail-confirm-group", "Mail confirm group owner.", params);
    }

    @Override
    public boolean sendEmailNotice(Long userId, String template, Map<String, String> values) {
        return false;
    }

    @Override
    public boolean sendEmailSystemNotice(String template, Map<String, String> values) {
        return false;
    }
}
