package com.csf.whoami.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csf.base.domain.mainpage.MenuGroupInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csf.base.domain.AccountDTO;
import com.csf.base.domain.SearchVO;
import com.csf.base.domain.enumtype.AccountTypeEnum;
import com.csf.base.domain.enumtype.EventTypeEnum;
import com.csf.base.domain.request.ConfirmGroupInfo;
import com.csf.base.domain.response.AccountInfo;
import com.csf.base.domain.response.ChannelInfo;
import com.csf.base.domain.response.GroupInfo;
import com.csf.base.exception.CustomException;
import com.csf.base.exception.ErrorException;
import com.csf.base.exception.HttpStatus;
import com.csf.base.utilities.ObjectUtil;
import com.csf.base.utilities.StringUtils;
import com.csf.database.adapter.ConvertGroupDTO;
import com.csf.database.adapter.GroupAdapter;
import com.csf.database.models.AccountEntity;
import com.csf.database.models.EventEntity;
import com.csf.database.models.GroupEntity;
import com.csf.database.models.PinCodeEntity;
import com.csf.database.models.TbUserGroup;
import com.csf.database.repository.AccountRepository;
import com.csf.database.repository.ChannelRepository;
import com.csf.database.repository.EventRepository;
import com.csf.database.repository.GroupRepository;
import com.csf.database.repository.PinCodeRepository;
import com.csf.database.repository.UserGroupRepository;
import com.csf.whoami.service.EmailService;
import com.csf.whoami.service.GroupService;
import com.csf.whoami.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

	private final UserService userService;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
    private final AccountRepository userRepository;
    private final ChannelRepository channelRepository;
    private final EmailService emailService;
    private final PinCodeRepository pinCodeRepository;
    private final EventRepository eventRepository;

    @Override
    public List<GroupInfo> findAllByUser(Long userId) {
//        List<GroupView> entities = groupRepository.findAllByUser(userId);
//        List<GroupInfo> groups = new ArrayList<GroupInfo>();
//        for (GroupView item : entities) {
//            groups.add(GroupAdapter.viewToDomain(item));
//        }
//        return groups;
    	return null;
    }

    @Override
    public GroupInfo getGroupByGroupUrl(String groupUrl) {
    	groupRepository.findByGroupUrl(groupUrl);
//        param.put("groupUrl", groupUrl);
//        List<ZValue> group = groupMapper.findByGroupUrl(param);

//        if(CollectionUtils.isEmpty(group) || group.size() > 1) {
//            return null;
//        }
//        return GroupAdapter.viewToDomain(group.get(0));
        return new GroupInfo();
    }

    @Override
    public Long registerOrUpdate(GroupInfo domain) {
        GroupEntity entity = GroupAdapter.domainToModel(domain);
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
//        List<GroupView> entities = groupMapper.findAllByUsername(username);
//        List<GroupInfo> groups = new ArrayList<GroupInfo>();
//        for (GroupView item : entities) {
//            groups.add(GroupAdapter.viewToDomain(item));
//        }
//        return groups;
    	return null;
    }

    @Transactional
    @Override
    public GroupInfo updateGroupInformation(GroupInfo domain, String userId) throws Exception {
        GroupEntity group = groupRepository.findById(Long.parseLong(domain.getId())).orElse(null);
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
        GroupEntity entity = groupRepository.findById(Long.parseLong(groupId)).orElse(null);
        if (entity == null) {
            throw new Exception("Can not found group to update.");
        }

//        if (!ownerUser.equals(entity.getGroupOwner())) {
//            throw new Exception("Have no permission add member to this group.");
//        }

        // Get user member
        AccountEntity user = userRepository.findById(Long.parseLong(userAdded)).orElse(null);
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
    public List<ChannelInfo> getChannelsByGroup(String groupId, String userId) throws Exception {
        TbUserGroup checkUserGroup = userGroupRepository.findAllByUserIdAndGroupId(userId, groupId);
        if (checkUserGroup == null) {
            GroupEntity group = groupRepository.findById(Long.parseLong(groupId)).orElse(null);
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
        return new ArrayList<>();
    }

    /**
     * Register channel after login.
     * @throws Exception 
     */
    @Override
    public GroupInfo addTempGroup(GroupInfo domain) throws Exception {
//        GroupEntity isExist = groupRepository.findByUrlAndType(domain.getGroupUrl(), domain.getGroupType());
//        if (isExist != null) {
//            throw new Exception("Exist group");
//        }
//        isExist = GroupAdapter.domainToModel(domain);
//        isExist.setIsLock("0");
//        isExist = groupRepository.save(isExist);
//        
//        if (isExist == null) {
//            throw new Exception("Can not create temp group.");
//        }
//
//        return GroupAdapter.modelToDomain(isExist);
    	return null;
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
//        return groupRepository.groupList(search, pageable);
        return null;
    }

    /**
     * Get group information.
     * 
     * @param id
     * @return
     */
    @Override
    public GroupInfo groupDetail(Long id) {
        GroupEntity group = groupRepository.findById(id).orElse(null);
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
        GroupEntity group = ConvertGroupDTO.domainToDb(groupDetail);
        GroupEntity result = groupRepository.save(group);
        return result.getId();
    }

    @Override
    public String registerTempGroup(ConfirmGroupInfo groupRequest) {
        GroupEntity group = ConvertGroupDTO.tempDomainToDb(groupRequest);
        GroupEntity result = groupRepository.save(group);
        return result.getCode();
    }

    @Transactional
    @Override
    public boolean sendEmailConfirm(ConfirmGroupInfo groupInfo) {
        if (groupInfo == null || groupInfo.getCode() == null) {
            return false;
        }
        GroupEntity group = groupRepository.findByCode(groupInfo.getCode());
        if (group == null) {
            return false;
        }
        /// Generate Pincode
        PinCodeEntity pinCode = generatePinCode();
        pinCode = pinCodeRepository.save(pinCode);
        return emailService.sendEmailConfirmGroup(groupInfo, pinCode.getPinCode());
    }

    /**
     * Generate pin-code.
     * 
     * @return
     */
    private PinCodeEntity generatePinCode() {
        PinCodeEntity pinCode = new PinCodeEntity();
        pinCode.setTypeCode(EventTypeEnum.CREATE_GROUP.getCode());
        pinCode.setPinCode(genTempPinCode()); // random value 6 number.
        return pinCode;
    }

    @Override
    public boolean checkPinCode(ConfirmGroupInfo groupInfo) {
        return true;
    }

	@Override
	public boolean initialAccount(ConfirmGroupInfo groupInfo) {
//		EventEntity event = eventRepository.fetchEventInfo(groupInfo.getEmail(), groupInfo.getPinCode(), EventTypeEnum.CREATE_GROUP.getCode());
		EventEntity event = eventRepository.fetchEventInfo(groupInfo.getEmail(), groupInfo.getCode());
		if(event == null || event.getActivedAt() != null) {
			return false;
		}
		// Update group information
		GroupEntity group = groupRepository.findByCode(groupInfo.getCode());
		if(group == null) {
			return false;
		}
		group.setActivedAt(new Date());
		groupRepository.save(group);

		// Create account
		AccountDTO account = new AccountDTO();
		account.setUsername(groupInfo.getEmail());
		account.setPassword(StringUtils.generateCode(12, false));
		account.setType(AccountTypeEnum.WHOAMI.getCode());
		AccountInfo info = userService.signUp(account);
		// Send email account info.
		// generate login info.
		
		// Update event.
		event.setActivedAt(new Date());
		eventRepository.save(event);
		
		return true;
	}

    @Override
    public List<MenuGroupInfo> getMemberGroupInfo(ConfirmGroupInfo groupInfo) {
        GroupInfo info = getGroupByGroupUrl(groupInfo.getUrl());
        AccountInfo acc = userService.getUserByUsername(groupInfo.getEmail());

        try {
            List<ChannelInfo> channels = getChannelsByGroup(info.getId(), String.valueOf(acc.getUserId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<>();
    }

    private String genTempPinCode() {
		int min = 100000;
	    int max = 999999;
		int randomPinCode = (int)Math.floor(Math.random()*(max-min+1)+min);
		return String.valueOf(randomPinCode);
	}
}
