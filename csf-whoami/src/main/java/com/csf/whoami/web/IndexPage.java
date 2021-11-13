package com.csf.whoami.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.constant.ConstantsURL;
import com.csf.base.domain.mainpage.DirectMessageInfo;
import com.csf.base.domain.mainpage.MenuChannelInfo;
import com.csf.base.domain.mainpage.MenuGroupInfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = ConstantsURL.HOME)
@RequiredArgsConstructor
public class IndexPage {

    /**
     * Home page.
     * 
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView index(ModelAndView model) {
        model.setViewName(ConstantsURL.PAGE_START);
        return model;
    }

    /**
     * Main page. Require member login.
     * 
     * @param model
     * @return
     */
    @GetMapping(value = ConstantsURL.MAIN)
    public ModelAndView gotoMainPage(ModelAndView model) {
        List<MenuGroupInfo> groups = dummyMenuGroups();
        model.addObject("groups", groups);
        List<MenuChannelInfo> channels = dummyMenuChannels();
        model.addObject("channels", channels);
        List<DirectMessageInfo> directMessages = dummyDirectMessages();
        model.addObject("directMessages", directMessages);
        model.setViewName(ConstantsURL.PAGE_MAIN);
        return model;
    }

    private List<DirectMessageInfo> dummyDirectMessages() {
        List<DirectMessageInfo> directMessages = new ArrayList<>();
        return directMessages;
    }

    private List<MenuGroupInfo> dummyMenuGroups() {
        List<MenuGroupInfo> groups = new ArrayList<>();
        MenuGroupInfo group1 = new MenuGroupInfo();
        group1.setId("1");
        group1.setName("DA");
        group1.setActive("true");
        group1.setPriority("1");
        groups.add(group1);

        MenuGroupInfo group2 = new MenuGroupInfo();
        group2.setId("2");
        group2.setName("SS");
        group2.setPriority("2");
        groups.add(group2);

        MenuGroupInfo group3 = new MenuGroupInfo();
        group3.setId("1");
        group3.setName("CS");
        group3.setPriority("3");
        groups.add(group3);

        return groups;
    }

    private List<MenuChannelInfo> dummyMenuChannels() {
        List<MenuChannelInfo> channels = new ArrayList<>();
        MenuChannelInfo menu1 = new MenuChannelInfo();
        menu1.setId("1");
        menu1.setName("general");
        menu1.setType("public");
        menu1.setPriority("1");
        channels.add(menu1);

        MenuChannelInfo menu2 = new MenuChannelInfo();
        menu2.setId("2");
        menu2.setName("random");
        menu2.setType("public");
        menu2.setPriority("2");
        channels.add(menu2);

        MenuChannelInfo menu3 = new MenuChannelInfo();
        menu3.setId("3");
        menu3.setName("demo-channel");
        menu3.setType("public");
        menu3.setPriority("3");
        channels.add(menu3);

        return channels;
    }

    @GetMapping(value = "/search")
    public ModelAndView searchGroup(ModelAndView model) {
        model.setViewName("login/seach-group");
        return model;
    }

    @PostMapping(value = "/group/search")
    public ModelAndView searchGroup(@RequestBody String keyword, ModelAndView model) {
        System.out.println("Search by Name: " + keyword);
        model.setViewName("login/group-info");
        return model;
    }

    @GetMapping(value = "/test")
    public ModelAndView testPage(ModelAndView model) {
        model.setViewName("create-quiz");
        return model;
    }

    @GetMapping(value = "/test-final")
    public ModelAndView createQuizPage(ModelAndView model) {
        model.setViewName("quiz");
        return model;
    }

    @GetMapping(value = "/preview-final")
    public ModelAndView createQuizPreviewPage(ModelAndView model) {
        model.setViewName("quiz-preview");
        return model;
    }
}
