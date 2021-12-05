/**
 * 
 */
package com.csf.whoami.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.constant.ConstantsURL;

/**
 * @author mac
 *
 */
@Controller
@RequestMapping(value = "/w")
public class WorkFlowController {

    @GetMapping(value = "workflow.html")
    public ModelAndView gotoCheckLogin(ModelAndView model) {
        model.setViewName(ConstantsURL.W_CHECK_LOGIN);
        return model;
    }
}
