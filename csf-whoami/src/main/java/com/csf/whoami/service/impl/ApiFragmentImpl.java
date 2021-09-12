package com.csf.whoami.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.csf.base.domain.mainpage.MenuChannelInfo;
import com.csf.whoami.service.ApiFragment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiFragmentImpl implements ApiFragment {

    private final ThymeleafService thymeleafService;

    @Override
    public String sidebarChannel(String groupId) {
        Map<String, Object> params = new HashMap<>();
        List<MenuChannelInfo> channels = dummyMenuChannels(groupId);
        params.put("channelDatas", channels);
        return thymeleafService.exportHtmlBody(thymeleafService.getContent("sidebar-channels", params));
    }

    private List<MenuChannelInfo> dummyMenuChannels(String groupId) {
        List<MenuChannelInfo> channels = new ArrayList<>();
        MenuChannelInfo menu1 = new MenuChannelInfo();
        menu1.setId("1");
        menu1.setName("general-" + groupId);
        menu1.setType("public");
        menu1.setPriority("1");
        channels.add(menu1);

        MenuChannelInfo menu2 = new MenuChannelInfo();
        menu2.setId("2");
        menu2.setName("random-" + groupId);
        menu2.setType("public");
        menu2.setPriority("2");
        channels.add(menu2);

        MenuChannelInfo menu3 = new MenuChannelInfo();
        menu3.setId("3");
        menu3.setName("demo-channel-" + groupId);
        menu3.setType("public");
        menu3.setPriority("3");
        channels.add(menu3);

        return channels;
    }

    @Override
    public String messageConents(String channelId) {
        Map<String, Object> params = new HashMap<>();
        List<MenuChannelInfo> messages = dummyMenuChannels(channelId);
        params.put("channelDatas", messages);
        return thymeleafService.exportHtmlBody(thymeleafService.getContent("messages-content", params));
    }
}
