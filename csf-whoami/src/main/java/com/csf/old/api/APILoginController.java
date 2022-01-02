package com.csf.old.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csf.base.constant.ConstantsURL;
import com.csf.whoami.service.GroupService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ConstantsURL.LOGIN)
@RequiredArgsConstructor
@Api(tags = "2. Member login functions")
public class APILoginController {

    private final GroupService groupService;

    /**
     * Find group by URL.
     * 
     * @param groupRequest
     * @param model
     * @return
     */
//    @ApiOperation(value = "1. Search by group information.")
//    @PostMapping(value = ConstantsURL.SEARCH_GROUP)
//    public ResponseEntity<ResponseDataAPI> searchGroup(@RequestBody RequestSearchGroup groupRequest) {
//
//        GroupInfo group = groupService.getGroupByGroupUrl(groupRequest.getGroupURL());
//        return ResponseEntity.ok(ResponseDataAPI.builder()
//                .data(group)
//                .build());
//    }

    /**
     * Register temp group by user.
     * @param groupRequest
     * @param model
     * @return
     */
//    @ApiOperation(value = "2. Register new group.")
//    @PostMapping(value = ConstantsURL.REGISTER_GROUP)
//    public ResponseEntity<ResponseDataAPI> registerTempGroup(@RequestBody RequestSearchGroup groupRequest) {
//        Long id = groupService.registerTempGroup(groupRequest);
//        return ResponseEntity.ok(ResponseDataAPI.builder()
//                .data(id)
//                .build());
//    }

//    @ApiOperation(value = "3. Confirm account through email.")
//    @PostMapping(value = ConstantsURL.CONFIRM_EMAIL)
//    public ResponseEntity<ResponseDataAPI> confirmEmail(@RequestBody RequestSearchGroup groupRequest) {
//        boolean sendEmailStatus = groupService.sendEmailConfirm(groupRequest);
//        return ResponseEntity.ok(ResponseDataAPI.builder()
//                .data(sendEmailStatus)
//                .build());
//    }

//    @ApiOperation(value = "4. Confirm account by PINCODE.")
//    @GetMapping(value = ConstantsURL.CONFIRM_PINCODE)
//    public ResponseEntity<ResponseDataAPI> confirmPinCode(@RequestBody String groupId) {
//        return ResponseEntity.ok(ResponseDataAPI.builder()
//                .data(true)
//                .build());
//    }
}
