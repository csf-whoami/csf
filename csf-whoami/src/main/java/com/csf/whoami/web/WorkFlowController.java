/**
 * 
 */
package com.csf.whoami.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.constant.ConstantsURL;
import com.csf.base.domain.AccountDTO;
import com.csf.base.domain.request.ConfirmGroupInfo;
import com.csf.whoami.service.GroupService;

import lombok.RequiredArgsConstructor;

/**
 * @author mac
 *
 */
@Controller
@RequestMapping(value = "/w")
@RequiredArgsConstructor
public class WorkFlowController {

    private final GroupService groupService;

    @GetMapping(value = "workflow.html")
    public ModelAndView gotoCheckLogin(ModelAndView model) {
        model.setViewName(ConstantsURL.W_CHECK_LOGIN);
        return model;
    }

    @GetMapping(value = "group-info/{id}.html")
    public ModelAndView gotoGroupInfo(ModelAndView model, @PathVariable("id") String groupId) {
        model.setViewName(ConstantsURL.W_GROUP_FOUND);
        return model;
    }

    @GetMapping(value = "group-not-found.html")
    public ModelAndView gotoGroupNotExist(ModelAndView model) {
        model.setViewName(ConstantsURL.W_GROUP_NOT_FOUND);
        return model;
    }

    @PostMapping(value = "login.html")
    public ModelAndView login(@ModelAttribute("formData") AccountDTO search, ModelAndView model) {
        model.setViewName(ConstantsURL.W_GROUP_NOT_FOUND);
        return model;
    }

    @PostMapping(value = "validate-group.html")
    public ModelAndView gotoValidateGroup(ModelAndView model, @ModelAttribute("formData") ConfirmGroupInfo groupInfo) {
        String groupCode = groupService.registerTempGroup(groupInfo);
        System.out.println("Group ID:" + groupCode);
        model.addObject("code", groupCode);
        model.setViewName(ConstantsURL.W_GROUP_MAIL_CONFIRM);
        return model;
    }

    @PostMapping(value = "email-confirm.html")
    public ModelAndView sendEmailAndConfirm(@ModelAttribute("formData") ConfirmGroupInfo groupInfo, ModelAndView model) {
        boolean sendEmailStatus = groupService.sendEmailConfirm(groupInfo);
        System.out.println("Send email: " + sendEmailStatus);
        model.setViewName(ConstantsURL.REDIRECT + "pincode-confirm/" + groupInfo.getCode() + ".html");
        return model;
    }

    @GetMapping(value = "pincode-confirm/{code}.html")
    public ModelAndView confirmPinCode(ModelAndView model, @PathVariable("code") String code) {
        model.addObject("code", code);
        model.setViewName(ConstantsURL.W_PIN_CONFIRM);
        return model;
    }

    @PostMapping(value = "pincode-confirm.html")
    public ModelAndView checkPinCode(ModelAndView model, @ModelAttribute("formData") ConfirmGroupInfo groupInfo) {
        boolean validPIN = groupService.checkPinCode(groupInfo);
        if(!validPIN) {
            model.setViewName(ConstantsURL.W_PIN_CONFIRM);
        } else {
        	groupService.initialAccount(groupInfo);
            model.setViewName(ConstantsURL.REDIRECT + ConstantsURL.MAIN);
        }
        return model;
    }
}
