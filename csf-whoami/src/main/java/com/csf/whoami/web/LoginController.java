package com.csf.whoami.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.domain.RequestSearchGroup;
import com.csf.base.domain.response.AccountInfo;
import com.csf.base.domain.response.GroupInfo;
import com.csf.whoami.base.constant.ConstantsParam;
import com.csf.whoami.base.constant.ConstantsURL;
import com.csf.whoami.base.exception.CustomException;
import com.csf.whoami.base.util.StringUtils;
import com.csf.whoami.config.CustomAuthenticationDomainProvider;
import com.csf.whoami.config.CustomAuthenticationDomainToken;
import com.csf.whoami.service.GroupService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = ConstantsURL.LOGIN)
@RequiredArgsConstructor
public class LoginController {

    private final GroupService groupService;
    private final CustomAuthenticationDomainProvider authenticationManager;

    @GetMapping(value = ConstantsURL.SEARCH_GROUP)
    public ModelAndView gotoSearchGroupPage(ModelAndView model) {
        model.addObject(ConstantsParam.PARAM_GROUP, new RequestSearchGroup());
        model.setViewName(ConstantsURL.PAGE_SEARCH_GROUP);
        return model;
    }

    /**
     * Find group by URL.
     * 
     * @param groupRequest
     * @param model
     * @return
     */
    @PostMapping(value = ConstantsURL.SEARCH_GROUP)
    public ModelAndView searchGroup(@Valid @ModelAttribute(ConstantsParam.PARAM_GROUP) RequestSearchGroup groupRequest, ModelAndView model) {

        Map<String, String> errors = new HashMap<>();
        // Check business logic.
        if (StringUtils.isNullOrEmpty(groupRequest.getGroupURL())) {
            errors.put(ConstantsParam.PARAM_GROUP, "Group name must be required.");
        } else if (groupRequest.getGroupURL().length() > 64 || groupRequest.getGroupURL().length() < 8) {
            errors.put(ConstantsParam.PARAM_GROUP, "Group name length range in 8 to 64.");
        }
        if (!errors.isEmpty()) {
            model.addObject(ConstantsParam.ERRORS, errors);
            return model;
        }

        GroupInfo group = groupService.getGroupByGroupUrl(groupRequest.getGroupURL());
        if (group != null) {
            // Move to group information.
            model.addObject(ConstantsParam.PARAM_GROUP, group);
            model.setViewName(ConstantsURL.PAGE_GROUP_INFO);
        } else {
            model.addObject(ConstantsParam.PARAM_GROUP, groupRequest);
            model.setViewName(ConstantsURL.PAGE_GROUP_CONFIRM);
        }
        return model;
    }

    /**
     * Register temp group by user.
     * @param groupRequest
     * @param model
     * @return
     */
    @PostMapping(value = ConstantsURL.REGISTER_GROUP)
    public ModelAndView registerTempGroup(@Valid @ModelAttribute(ConstantsParam.PARAM_GROUP) RequestSearchGroup groupRequest, ModelAndView model) {
        Long id = groupService.registerTempGroup(groupRequest);
        Map<String, String> errors = new HashMap<>();
        if (id == null) {
            errors.put(ConstantsParam.PARAM_GROUP, "Can not register group.");
            model.addObject(ConstantsParam.ERRORS, errors);
            return model;
        }
        model.addObject("id", id);
        model.setViewName(ConstantsURL.PAGE_EMAIL_CONFIRM);
        return model;
    }

    @PostMapping(value = ConstantsURL.CONFIRM_EMAIL)
    public ModelAndView confirmEmail(@Valid @ModelAttribute(ConstantsParam.PARAM_GROUP) RequestSearchGroup groupRequest, ModelAndView model) {
        boolean sendEmailStatus = groupService.sendEmailConfirm(groupRequest);
        if (sendEmailStatus == false) {
            model.addObject(ConstantsParam.ERRORS, "Send email fail.");
        }
        model.setViewName(ConstantsURL.PAGE_REGISTER_SUCCESS);
        return model;
    }

    @GetMapping(value = ConstantsURL.AUTHENTICATE)
    public ModelAndView loginPage(@PathVariable("id") String groupId, ModelAndView model) {
        model.addObject(ConstantsParam.PARAM_GROUP, StringUtils.toLongOrNull(groupId));
        model.setViewName(ConstantsURL.PAGE_AUTHENTICATE);
        return model;
    }

    /**
     * @param loginUser
     * @return
     * @throws Exception
     * @desctiption Controller login system for role user
     */
    @PostMapping(value = ConstantsURL.AUTHENTICATE)
    public ModelAndView loginToGroup(@Valid @ModelAttribute(ConstantsParam.PARAM_USER) AccountInfo loginUser, ModelAndView model) {

        Authentication authentication = null;
        try {
//            authentication = authenticationManager.authenticate(
//                    new CustomAuthenticationDomainToken(loginUser.getUsername(), loginUser.getPassword(), loginUser.getNetworkId()));
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof CustomException) {
                model.addObject(ConstantsParam.ERRORS, e.getMessage());
            }
        }

        if (authentication == null) {
            model.addObject(ConstantsParam.ERRORS, "Username or password invalid.");
            model.setViewName(ConstantsURL.PAGE_AUTHENTICATE);
            return model;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.setViewName(ConstantsURL.PAGE_MAIN);
        return model;
    }

    @GetMapping(value = ConstantsURL.CONFIRM_PINCODE)
    public ModelAndView confirmPinCode(@PathVariable("id") String groupId, ModelAndView model) {
        model.setViewName(ConstantsURL.PAGE_REGISTER_SUCCESS);
        return model;
    }
}
