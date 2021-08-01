package com.csf.whoami.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.csf.base.domain.RequestSearchGroup;
import com.csf.base.domain.SearchVO;
import com.csf.base.domain.response.GroupInfo;

public interface GroupService {

    List<GroupInfo> findAllByUser(Long userId);

    GroupInfo getGroupByGroupUrl(String groupUrl);

    List<GroupInfo> getGroupsByUsername(String username);

    GroupInfo updateGroupInformation(GroupInfo domain, String userId) throws Exception;

    void addMemberToGroup(String groupId, String userId, String userId2) throws Exception;

    GroupInfo getChannelByGroup(String groupId, String userId) throws Exception;

    GroupInfo addTempGroup(GroupInfo domain) throws Exception;

    boolean checkUserInChannel(String channelId, String userId);

    Page<GroupInfo> groupList(SearchVO search, Pageable pageable);

    GroupInfo groupDetail(Long id);

    Long registerGroup(GroupInfo groupDetail);

    Long registerTempGroup(RequestSearchGroup groupRequest);

    /**
     * List user's groups.
     * 
     * @param search
     * @param pageable
     * @return
     */
//    Page<GroupInfo> findAllByRole(SearchVO search, Pageable pageable);

    /**
     * Register group.
     * @param groupinfo
     * @return
     */
    Long registerOrUpdate(GroupInfo groupinfo);

    /**
     * Send URL and code through email.
     * 
     * @param groupRequest
     * @return
     */
    boolean sendEmailConfirm(RequestSearchGroup groupRequest);
}
