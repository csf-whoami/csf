package com.csf.whoami.database.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class LoginUser {

    @NotNull(message = "login_id must be not null")
    @Size(min=2, message="login_id should have at least 8 characters")
    private String loginId;
    @NotNull
    private String password;
}
