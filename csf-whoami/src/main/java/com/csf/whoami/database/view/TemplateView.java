package com.csf.whoami.database.view;

import java.util.Date;

import com.csf.whoami.database.dto.YesNo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TemplateView {

    private Long id;
    private String name;
    private String code;
    private YesNo isActive;
    private Date activeDate;
}
