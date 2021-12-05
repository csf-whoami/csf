/**
 * 
 */
package com.csf.whoami.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csf.base.domain.response.GroupInfo;
import com.csf.base.exception.CustomError;
import com.csf.base.exception.CustomException;
import com.csf.base.exception.ErrorException;
import com.csf.base.exception.HttpStatus;
import com.csf.base.exception.ResponseDataAPI;
import com.csf.whoami.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @author mac
 *
 */
@Api
@RestController
@RequestMapping(value = "/w/api/v1/workflow")
@RequiredArgsConstructor
public class WorkFlowAPI {

    private final GroupService groupsService;

    @ApiOperation(value = "1. Phương thức tìm kiếm Group trong hệ thống.")
    @GetMapping(value = "/findGroup")
    public ResponseEntity<ResponseDataAPI> findGroup(@RequestParam(name = "type") String groupType, @RequestParam(name = "name") String groupName){
        GroupInfo group = groupsService.getGroupByGroupUrl(groupName);
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
}
