package com.csf.base.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@ApiModel(description = "Model menu")
public class MenuDomain {

    @ApiModelProperty(value = "ID", position = 1)
    private String id                    = null;
    @ApiModelProperty(value = "Tiêu đề", position = 2)
    private String menuName              = null;
    @ApiModelProperty(value = "Đường dẫn tới trang", position = 3)
    private String linkScreen            = null;
    @ApiModelProperty(value = "Ngôn ngữ", position = 4)
    private String locale                = null;
    @ApiModelProperty(value = "Menu con", position = 5)
    private String parentMenu            = null;
    @ApiModelProperty(value = "Có phải là menu nhiều cấp hay không", position = 6)
    private boolean root                 = false;
}
