package com.csf.base.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MenuDomain {

    private String id                    = null;
    private String menuName              = null;
    private String linkScreen            = null;
    private String locale                = null;
    private Long parentMenu              = null;
    private boolean root                 = false;
}
