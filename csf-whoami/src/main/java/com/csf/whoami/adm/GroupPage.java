package com.csf.whoami.adm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.constant.ConstantsURL;
import com.csf.base.domain.SearchVO;
import com.csf.base.domain.response.GroupInfo;
import com.csf.whoami.service.GroupService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = ConstantsURL.URI_GROUP)
public class GroupPage extends DashboardPage<GroupInfo> {

    private final GroupService groupService;

    @Override
    protected String functionPath() {
        return ConstantsURL.URI_GROUP;
    }

    @Override
    protected Page<GroupInfo> searchData(SearchVO search, Pageable pageable, ModelAndView model) {
        return groupService.groupList(search, pageable);
    }

    @Override
    protected GroupInfo detailInfo(Long dataId, ModelAndView model) {
        return groupService.groupDetail(dataId);
    }

    @Override
    protected boolean registerOrUpdate(GroupInfo data, ModelAndView model) {
        return groupService.registerOrUpdate(data) > 0L;
    }

    @Override
    protected GroupInfo initialModel() {
        return new GroupInfo();
    }
}
