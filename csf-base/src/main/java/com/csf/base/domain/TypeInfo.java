package com.csf.base.domain;

import com.csf.base.utilities.StringUtils;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
