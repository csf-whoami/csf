/**
 * Functions list:
 * 
 * - Add new group		-- OK
 * - Update group info	-- OK
 * - View user's group	-- OK	Include anonimous.
 * - View all group by username (Admin)	-- OK
 * 
 * - Invite user to group.
 * - Accept invite to group.
 * 
 * - Kick user out group.
 * - Out group.
 */
package com.csf.whoami.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.csf.whoami.database.dto.response.GroupInfo;
import com.csf.whoami.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/groups")
@Api
public class GroupsController {

	@Autowired
	private GroupService groupsService;

	@ApiOperation(value = "Phương thức tìm kiếm Group trong hệ thống.")
	@PostMapping(value = "/find-group", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GroupInfo findGroup(@RequestBody String groupUrl) {
		return groupsService.getGroupByGroupUrl(groupUrl);
	}

	@ApiOperation(value = "Phương thức tạo mới group.")
	@PostMapping("/add-group")
//	@Secured({ "ROLE_USER" })
	public GroupInfo addNewGroup(@RequestBody GroupInfo domain, @ApiParam(hidden = true) Authentication auth)
			throws Exception {

//		UserInfo userdto = (UserInfo) auth.getPrincipal();
//		String userId = userdto.getUserId();
//
//		GroupDomain group = groupsService.addNewGroup(domain, userId);
////		if (group == null) {
////			response.setSuccess(false);
////			response.setMessage("Can not create new group.");
////			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
////		}
//		return group;
		return new GroupInfo();
	}

	@ApiOperation(value = "Phương thức lấy danh sách tất cả các group của người dùng.")
	@GetMapping("/list")
//	@Secured({ "ROLE_USER" })
	public List<GroupInfo> findUserGroups(@ApiParam(hidden = true) Authentication auth) {
//		UserInfo userdto = (UserInfo) auth.getPrincipal();
//
//		List<GroupDomain> groups = groupsService.findAllByUser(userdto.getUserId());
//		return groups;
		return new ArrayList<>();
	}

	@ApiOperation(value = "Phương thức lấy thông tin của người dùng.")
	@GetMapping("/findByUser/{username}")
	@ResponseStatus(HttpStatus.OK)
//	@Secured({ "ROLE_ADMIN" })
	public List<GroupInfo> findGroupsByUsername(@RequestParam("username") String username,
			@ApiParam(hidden = true) Authentication auth) {
		List<GroupInfo> groups = groupsService.getGroupsByUsername(username);
		return groups;
	}

//	@DeleteMapping("/{groupid}/{email}")
//	@ResponseStatus(HttpStatus.OK)
//	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
//	public Boolean findGroupsByEmail(@RequestParam("groupid") String groupId, @RequestParam("email") String email,
//			Authentication auth) {
//		// Remove people out of Group.
//		// Need to check permission.
//		return true;
//	}

	@ApiOperation(value = "Phương thức thêm người dùng vào trong nhóm.")
	@PostMapping("/{groupid}/{userid}")
	@ResponseStatus(HttpStatus.OK)
//	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public boolean addUserToGroups(@RequestParam("groupid") String groupId,
			@RequestParam("userid") String userId, @ApiParam(hidden = true) Authentication auth) throws Exception {
//		UserInfo userdto = (UserInfo) auth.getPrincipal();
//		// Add people out of Group.
//		groupsService.addMemberToGroup(groupId, userId, userdto.getUserId());
		return true;
	}

	@ApiOperation(value = "Phương thức cập nhật thông tin cho Group.")
	@PutMapping("/")
	@ResponseStatus(HttpStatus.OK)
//	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public GroupInfo updateGroupInfo(@PathVariable("groupid") String groupId,
			@RequestBody GroupInfo domain, @ApiParam(hidden = true) Authentication auth) throws Exception {

//		UserInfo userdto = (UserInfo) auth.getPrincipal();
//		// Update group information.
//		domain = groupsService.updateGroupInformation(domain, userdto.getUserId());
//		return domain;
		return new GroupInfo();
	}

	@ApiOperation(value = "Phương thức lấy danh sách tất cả kênh con trong Group.")
	@GetMapping("/{groupid}")
//	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public GroupInfo getChannelByGroup(@PathVariable("groupid") String groupId,
			@ApiParam(hidden = true) Authentication auth) throws Exception {
//		UserInfo userdto = (UserInfo) auth.getPrincipal();
//		GroupInfoDomain groupDomain = groupsService.getChannelByGroup(groupId, userdto.getUserId());
//		return groupDomain;
		return new GroupInfo();
	}

	@ApiOperation(value = "Phương thức tạo ra Group tạm thời trong trường hợp người dùng chưa đăng nhập nhưng lại muốn tạo ra Group.")
	@PostMapping("/temp-group")
	public GroupInfo createTempGroup(@RequestBody GroupInfo domain) throws Exception {
		GroupInfo group = groupsService.addTempGroup(domain);
		return group;
	}
}