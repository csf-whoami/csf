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

import lombok.RequiredArgsConstructor;

/**
 * @author mac
 *
 */
@Controller
@RequestMapping(value = "/w")
@RequiredArgsConstructor
public class WorkFlowController {

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

    @GetMapping(value = "validate-group.html")
    public ModelAndView gotoValidateGroup(ModelAndView model) {
        model.setViewName(ConstantsURL.W_GROUP_MAIL_CONFIRM);
        return model;
    }

    @PostMapping(value = "email-confirm.html")
    public ModelAndView sendEmailAndConfirm(@ModelAttribute("formData") ConfirmGroupInfo groupInfo, ModelAndView model) {
    	System.out.println("Group email: " + groupInfo.getEmail());
//        model.setViewName(ConstantsURL.W_GROUP_NOT_FOUND);
        return model;
    }
}
