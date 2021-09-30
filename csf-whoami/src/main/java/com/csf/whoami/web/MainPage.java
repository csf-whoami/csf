package com.csf.whoami.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.constant.ConstantsURL;
import com.csf.whoami.service.ApiFragment;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = ConstantsURL.MAIN)
@RequiredArgsConstructor
public class MainPage {

    private final ApiFragment apiFragment;

    @GetMapping(value = "/channels/{groupId}")
    public ResponseEntity<String> fetchUserChannels(@PathVariable("groupId") String groupId) {
        String htmlContent = apiFragment.sidebarChannel(groupId);
        return ResponseEntity.ok(htmlContent);
    }

    @GetMapping(value = "/messages/{channelId}")
    public ResponseEntity<String> fetchMessageByChannelId(@PathVariable("channelId") String groupId) {
        String htmlContent = apiFragment.messageConents(groupId);
        return ResponseEntity.ok(htmlContent);
    }

    @GetMapping(value = "content")
    public ModelAndView tempMessage(ModelAndView model) {
        model.setViewName("main-content");
        return model;
    }
}
