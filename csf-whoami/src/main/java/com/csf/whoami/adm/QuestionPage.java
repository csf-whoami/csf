package com.csf.whoami.adm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.domain.QuestionInfo;
import com.csf.base.domain.QuestionManagementInfo;
import com.csf.base.domain.SearchVO;
import com.csf.base.domain.TypeInfo;
import com.csf.base.domain.response.ChannelInfo;
import com.csf.base.domain.response.GroupInfo;
import com.csf.whoami.base.constant.ConstantsCommon;
import com.csf.whoami.base.constant.ConstantsParam;
import com.csf.whoami.base.constant.ConstantsString;
import com.csf.whoami.base.constant.ConstantsURL;
import com.csf.whoami.base.util.RequestUtils;
import com.csf.whoami.base.util.StringUtils;
import com.csf.whoami.database.type.EnumGroupType;
import com.csf.whoami.service.ChannelService;
import com.csf.whoami.service.GroupService;
import com.csf.whoami.service.QuestionService;
import com.csf.whoami.service.TypeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = ConstantsURL.URI_QUESTION)
public class QuestionPage extends DashboardPage<QuestionInfo> {

    private final QuestionService questionService;
    private final GroupService groupService;
    private final ChannelService channelService;
    private final TypeService typeService;

    @Override
    protected String functionPath() {
        return ConstantsURL.URI_QUESTION;
    }

    @Override
    protected QuestionInfo initialModel() {
        return new QuestionInfo();
    }

    /**
     * Go to management page.
     * 
     * @param search
     * @param pageable
     * @param model
     * @return
     */
    @Override
    protected Page<QuestionInfo> searchData(SearchVO search, Pageable pageable, ModelAndView model) {

        Map<String, Object> params = new HashMap<>();

        // Fetch groups data.
        Page<GroupInfo> groups = groupService.groupList(search, pageable);
        model.addObject(ConstantsParam.GROUPS, groups);

        HttpServletRequest request = RequestUtils.currentRequest().orElse(null);
        if (request != null) {
            Long groupId = null;
            Long channelId = null;

            String group = request.getParameter(ConstantsParam.PARAM_GROUP);
            if (!StringUtils.isNullOrEmpty(group)) {
                groupId = StringUtils.toLongOrNull(group.trim());
            }
            params.put(ConstantsParam.PARAM_GROUP, group);
            if (groupId == null) {
                return Page.empty();
            }

            // Fetch channels data.
            Page<ChannelInfo> channels = channelService.channelList(groupId, pageable);
            model.addObject(ConstantsParam.CHANNELS, channels);

            String channel = request.getParameter(ConstantsParam.PARAM_CHANNEL);
            if (!StringUtils.isNullOrEmpty(channel)) {
                channelId = StringUtils.toLongOrNull(channel.trim());
            }
            params.put(ConstantsParam.PARAM_CHANNEL, channel);
            if (channelId == null) {
                return Page.empty();
            }

            Page<QuestionManagementInfo> questions = questionService.fetchQuestionsByChannel(channelId, pageable);
            model.addObject(ConstantsParam.QUESTIONS, questions);

            search.setParams(params);
        }
        return Page.empty();
    }

    /**
     * 
     */
    @Override
    protected String addSearchParams(SearchVO search) {
        StringBuilder builder = new StringBuilder();
        builder.append(ConstantsString.BLANK);
        Map<String, Object> params = search.getParams();
        String group = (String) params.get(ConstantsParam.PARAM_GROUP);
        if (!StringUtils.isNullOrEmpty(group)) {
            builder.append(ConstantsCommon.JOIN);
            builder.append(ConstantsParam.PARAM_GROUP);
            builder.append(ConstantsCommon.EQUAL);
            builder.append(group);
        }

        String channel = (String) params.get(ConstantsParam.PARAM_CHANNEL);
        if (!StringUtils.isNullOrEmpty(channel)) {
            builder.append(ConstantsCommon.JOIN);
            builder.append(ConstantsParam.PARAM_CHANNEL);
            builder.append(ConstantsCommon.EQUAL);
            builder.append(channel);
        }
        return builder.toString();
    }

//    <input id="books0.title" name="books[0].title" value="">
    @Override
    protected boolean registerOrUpdate(QuestionInfo data, ModelAndView model) {
        Long id = questionService.registerOrUpdateDatas(data);
        return id > 0L;
    }

    /**
     * initial Data.
     */
    @Override
    protected void initialData(QuestionInfo data, ModelAndView model) {
        HttpServletRequest request = RequestUtils.currentRequest().orElse(null);
        if (request != null) {
            String group = request.getParameter(ConstantsParam.PARAM_GROUP);
            if (StringUtils.isNullOrEmpty(group)) {
                redirectManagementPage(model);
            }

            String channel = request.getParameter(ConstantsParam.PARAM_CHANNEL);
            if (StringUtils.isNullOrEmpty(channel)) {
                redirectManagementPage(model);
            }

            QuestionInfo info = questionService.questionInfo(StringUtils.toLongOrNull(group), StringUtils.toLongOrNull(channel));
            data.setGroupId(info.getGroupId());
            data.setGroupName(info.getGroupName());
            data.setChannelId(info.getChannelId());
            data.setChannelName(info.getChannelName());

            loadQuestionType(model);
        } else {
            redirectManagementPage(model);
        }
    }

    /**
     * Find data by ID.
     */
    @Override
    protected QuestionInfo detailInfo(Long id, ModelAndView model) {
        QuestionInfo question = questionService.findById(id);
        loadQuestionType(model);
        return question;
    }

    private void loadQuestionType(ModelAndView model) {
        List<TypeInfo> types = typeService.fetchTypesByGroup(EnumGroupType.QUESTION.getValue());
        model.addObject(ConstantsParam.TYPES, types);
    }
}
