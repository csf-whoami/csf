package com.csf.whoami.database.dto;

import com.csf.base.utilities.StringUtils;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "Thông tin về các loại.")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeInfo implements BaseInfo {

    private String id;
    private String typeName;
    private String group;

    public TypeInfo(Long id, String name) {
        this.id = StringUtils.fromLong(id);
        this.typeName = name;
    }
}
