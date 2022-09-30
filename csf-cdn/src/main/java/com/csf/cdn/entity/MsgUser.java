package com.csf.cdn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class MsgUser extends BaseEntity {

    private Long id;
    private String username;
    private String password;
}
