package com.csf.base.domain.mainpage;

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
public class MenuGroupInfo {
    private String id;
    private String name;
    private String avatar;
    private String active;
    private String priority;
}
