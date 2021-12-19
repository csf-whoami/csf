package com.csf.whoami.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csf.base.domain.SearchVO;
import com.csf.base.domain.request.ConfirmGroupInfo;
import com.csf.base.domain.response.ChannelInfo;
import com.csf.base.domain.response.GroupInfo;
import com.csf.base.exception.CustomException;
import com.csf.base.exception.ErrorException;
import com.csf.base.exception.HttpStatus;
import com.csf.base.utilities.ObjectUtil;
import com.csf.base.utilities.StringUtils;
import com.csf.database.adapter.ConvertGroupDTO;
import com.csf.database.adapter.GroupAdapter;
import com.csf.database.mappers.GroupMapper;
import com.csf.database.models.TbAccount;
import com.csf.database.models.TbGroup;
import com.csf.database.models.TbPinCode;
import com.csf.database.models.TbUserGroup;
import com.csf.database.repository.AccountRepository;
import com.csf.database.repository.ChannelRepository;
import com.csf.database.repository.GroupRepository;
import com.csf.database.repository.PinCodeRepository;
import com.csf.database.repository.UserGroupRepository;
import com.csf.database.view.GroupView;
import com.csf.whoami.service.EmailService;
import com.csf.whoami.service.GroupService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
    private final AccountRepository userRepository;
    private final ChannelRepository channelRepository;
    private final EmailService emailService;
    private final PinCodeRepository pinCodeRepository;
    private final GroupMapper groupMapper;

    @Override
    public List<GroupInfo> findAllByUser(Long userId) {
        List<GroupView> entities = groupMapper.findAllByUser(userId);
        List<GroupInfo> groups = new ArrayList<GroupInfo>();
        for (GroupView item : entities) {
            groups.add(GroupAdapter.viewToDomain(item));
        }
        return groups;
    }

    @Override
    public GroupInfo getGroupByGroupUrl(String groupUrl) {
    	GroupView group = groupMapper.findByGroupUrl(groupUrl);
        return GroupAdapter.viewToDomain(group);
    }

    @Override
    public Long registerOrUpdate(GroupInfo domain) {
        TbGroup entity = GroupAdapter.domainToModel(domain);
        entity = groupRepository.save(entity);
        if (entity == null) {
            throw new CustomException(ErrorException.CANT_CREATE_GROUP.getMessage(),
                    ErrorException.CANT_CREATE_GROUP.getCode(),
                    HttpStatus.BAD_REQUEST);
        }
        return entity.getId();
    }

    @Override
    public List<GroupInfo> getGroupsByUsername(String username) {
        List<GroupView> entities = groupMapper.findAllByUsername(username);
        List<GroupInfo> groups = new ArrayList<GroupInfo>();
        for (GroupView item : entities) {
            groups.add(GroupAdapter.viewToDomain(item));
        }
        return groups;
    }

    @Transactional
    @Override
    public GroupInfo updateGroupInformation(GroupInfo domain, String userId) throws Exception {
        TbGroup group = groupRepository.findById(Long.parseLong(domain.getId())).orElse(null);
        if (group == null) {
            throw new Exception("Can not found group to update.");
        }

//        if (userId.equals(group.getGroupOwner())) {
//            group.setGroupName(domain.getGroupName());
//            group.setGroupType(domain.getGroupType());
//            group = groupRepository.save(group);
//
//            if (group == null) {
//                throw new Exception("Can not update group.");
//            }
//        } else {
//            throw new Exception("Have no permission update this group.");
//        }

        return GroupAdapter.modelToDomain(group);
    }

    @Transactional
    @Override
    public void addMemberToGroup(String groupId, String userAdded, String ownerUser) throws Exception {
        TbGroup entity = groupRepository.findById(Long.parseLong(groupId)).orElse(null);
        if (entity == null) {
            throw new Exception("Can not found group to update.");
        }

//        if (!ownerUser.equals(entity.getGroupOwner())) {
//            throw new Exception("Have no permission add member to this group.");
//        }

        // Get user member
        TbAccount user = userRepository.findById(Long.parseLong(userAdded)).orElse(null);
        if (user == null) {
            throw new Exception("Can not found User.");
        }

        // Add member to group.
        TbUserGroup userGroup = new TbUserGroup();
//        userGroup.setGroup(entity);
//        userGroup.setUser(user);

        userGroupRepository.save(userGroup);
    }

    /**
     * Get group and channel information.
     * @author tuan
     */
    @Transactional
    @Override
    public GroupInfo getChannelByGroup(String groupId, String userId) throws Exception {
        TbUserGroup checkUserGroup = userGroupRepository.findAllByUserIdAndGroupId(userId, groupId);
        if (checkUserGroup == null) {
            TbGroup group = groupRepository.findById(Long.parseLong(groupId)).orElse(null);
            if (group == null) {
                throw new Exception("Group is not exist.");
            }
//            TbAccount user = userRepository.findById(Long.parseLong(userId)).orElse(null);

            checkUserGroup = new TbUserGroup();
//            checkUserGroup.setGroup(group);
//            checkUserGroup.setUser(user);
            checkUserGroup = userGroupRepository.save(checkUserGroup);

            if (checkUserGroup == null) {
                throw new Exception("Can not create group.");
            }
        }

        GroupInfo groupInfo = new GroupInfo();
//        groupInfo.setGroupId(String.valueOf(checkUserGroup.getGroup().getId()));
//        groupInfo.setGroupName(checkUserGroup.getGroup().getGroupName());
//        groupInfo.setGroupUrl(checkUserGroup.getGroup().getGroupUrl());

//        groupInfo.setChannels(groupRepository.findAllByParentGroup(groupId).stream()
//                .map(group -> new ChannelInfoDomain(
//                        group.getId(),
//                        group.getGroupName(),
//                        group.getGroupDescription(),
//                        "",
//                        "")
////                        StringUtils.convertDateToString(group.getCreatedDate()),
////                        StringUtils.convertObjectToString(group.getIsLock()))
//                ).collect(Collectors.toList()));
        return groupInfo;
    }

    /**
     * Register channel after login.
     * @throws Exception 
     */
    @Override
    public GroupInfo addTempGroup(GroupInfo domain) throws Exception {
        TbGroup isExist = groupRepository.findByGroupUrlAndGroupType(domain.getGroupUrl(), domain.getGroupType());
        if (isExist != null) {
            throw new Exception("Exist group");
        }
        isExist = GroupAdapter.domainToModel(domain);
        isExist.setIsLock("0");
        isExist = groupRepository.save(isExist);
        
        if (isExist == null) {
            throw new Exception("Can not create temp group.");
        }

        return GroupAdapter.modelToDomain(isExist);
    }

    @Override
    public boolean checkUserInChannel(String channelId, String userId) {
        return userGroupRepository.findAllByUserIdAndGroupId(userId, channelId) != null;
    }

    /**
     * Search groups by conditions.
     * 
     * @param search
     * @param pageable
     * @return
     */
    @Override
    public Page<GroupInfo> groupList(SearchVO search, Pageable pageable) {
        ObjectUtil.removeEmptyField(search);
        return groupRepository.groupList(search, pageable);
    }

    /**
     * Get group information.
     * 
     * @param id
     * @return
     */
    @Override
    public GroupInfo groupDetail(Long id) {
        TbGroup group = groupRepository.findById(id).orElse(null);
        if (group == null) {
            return null;
        }

        Page<ChannelInfo> channels = channelRepository.findAllByGroupId(group.getId(), PageRequest.of(0, 20));
        GroupInfo info = ConvertGroupDTO.dbToDomain(group);
//        info.setChannels(channels);
        return info;
    }

    /**
     * Insert or Update group base entity information.
     * 
     * @param groupDetail
     * @return
     */
    @Override
    public Long registerGroup(GroupInfo groupDetail) {
        TbGroup group = ConvertGroupDTO.domainToDb(groupDetail);
        TbGroup result = groupRepository.save(group);
        return result.getId();
    }

    @Override
    public Long registerTempGroup(ConfirmGroupInfo groupRequest) {
        TbGroup group = ConvertGroupDTO.tempDomainToDb(groupRequest);
        TbGroup result = groupRepository.save(group);
        return result.getId();
    }

    @Transactional
    @Override
    public boolean sendEmailConfirm(ConfirmGroupInfo groupInfo) {
        Long id = StringUtils.toLongOrNull(groupInfo.getGroupId());
        if (id == null) {
            return false;
        }
        TbGroup group = groupRepository.findById(id).orElse(null);
        if (group == null) {
            return false;
        }
        /// Generate Pincode
        TbPinCode pinCode = generatePinCode();
        pinCode = pinCodeRepository.save(pinCode);
        return emailService.sendEmailConfirmGroup(groupInfo, pinCode.getPinCode());
    }

    /**
     * Generate pin-code.
     * 
     * @return
     */
    private TbPinCode generatePinCode() {
        TbPinCode pinCode = new TbPinCode();
        pinCode.setGroupType("GROUP");
        pinCode.setPinCode("123456"); // random value 6 number.
        return pinCode;
    }
}
