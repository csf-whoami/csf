package com.csf.whoami.adm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.domain.SearchVO;
import com.csf.base.domain.response.ChannelInfo;
import com.csf.base.domain.response.GroupInfo;
import com.csf.whoami.base.constant.ConstantsParam;
import com.csf.whoami.base.constant.ConstantsURL;
import com.csf.whoami.base.util.RequestUtils;
import com.csf.whoami.base.util.StringUtils;
import com.csf.whoami.service.ChannelService;
import com.csf.whoami.service.GroupService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = ConstantsURL.URI_CHANNEL)
public class ChannelPage extends DashboardPage<ChannelInfo> {

    private final GroupService groupService;
    private final ChannelService channelService;

    @Override
    protected String functionPath() {
        return ConstantsURL.URI_CHANNEL;
    }

    @Override
    protected ChannelInfo initialModel() {
        return new ChannelInfo();
    }

    @Override
    protected Page<ChannelInfo> searchData(SearchVO search, Pageable pageable, ModelAndView model) {
        Map<String, Object> params = search.getParams();
        String group = (String) params.get(ConstantsParam.PARAM_GROUP);

        Page<GroupInfo> groups = groupService.groupList(search, pageable);
        model.addObject(ConstantsParam.GROUPS, groups);
        return channelService.channelList(StringUtils.toLongOrNull(group), pageable);
    }

    @Override
    protected ChannelInfo detailInfo(Long id, ModelAndView model) {
        return channelService.getChannelDetail(id);
    }

    @Override
    protected boolean registerOrUpdate(ChannelInfo data, ModelAndView model) {
        try {
            channelService.registerOrUpdate(data);
            // Redirect in case success.
            model = new ModelAndView(managementPage(new SearchVO()));
        } catch (Exception ex) {
            model.addObject(ConstantsParam.DATAS, new ChannelInfo());
            model.setViewName(detailPage());
            return false;
        }
        return true;
    }

    @Override
    public ModelAndView registerPage(ModelAndView model) {
        ChannelInfo channel = initialModel();
        HttpServletRequest request = RequestUtils.currentRequest().orElse(null);
        if (request != null) {
            Map<String, String[]> paramMap = request.getParameterMap();
            if (paramMap.containsKey("id")) 
            {
                String[] params = paramMap.get("id");
                if(params.length == 1) {
                    channel.setGroupId(params[0]);
                }
            }
        }
        model.addObject(ConstantsParam.DATAS, channel);
        model.setViewName(detailPage());
        return model;
    }
}
