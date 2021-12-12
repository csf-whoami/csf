/**
 * 
 */
package com.csf.whoami.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csf.base.domain.AccountDTO;
import com.csf.base.domain.response.GroupInfo;
import com.csf.base.exception.CustomError;
import com.csf.base.exception.ErrorException;
import com.csf.base.exception.ResponseDataAPI;
import com.csf.whoami.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api
@RestController
@RequestMapping(value = "/w/api/v1/workflow")
@RequiredArgsConstructor
public class WorkFlowAPI {

    private final GroupService groupService;

    @ApiOperation(value = "1. Phương thức tìm kiếm Group trong hệ thống.")
    @GetMapping(value = "/findGroup")
    public ResponseEntity<ResponseDataAPI> findGroup(@RequestParam(name = "type") String groupType, @RequestParam(name = "name") String groupName){
        GroupInfo group = groupService.getGroupByGroupUrl(groupName);
        if(group == null) {
            CustomError error = new CustomError(null, ErrorException.DATA_NOT_FOUND.getCode(), ErrorException.DATA_NOT_FOUND.getMessage());
            return ResponseEntity.ok(ResponseDataAPI.builder()
                    .data(error)
                    .build());
        }
        return ResponseEntity.ok(ResponseDataAPI.builder()
              .data(group)
              .build());
    }

    @ApiOperation(value = "2. Phương thức đăng nhập vào trong hệ thống.")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDataAPI> login(@RequestBody AccountDTO userInfo){

        return ResponseEntity.ok(ResponseDataAPI.builder()
                .data(null)
                .build());
    }

    @PostMapping(value = "/registerGroup")
    public ResponseEntity<ResponseDataAPI> registerGroup(@RequestBody GroupInfo groupInfo) {
        Long groupId = groupService.registerGroup(groupInfo);
        return ResponseEntity.ok(ResponseDataAPI.builder()
                .data(groupId)
                .build());
    }
}
