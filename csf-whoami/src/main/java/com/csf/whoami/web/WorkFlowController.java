/**
 * 
 */
package com.csf.whoami.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
