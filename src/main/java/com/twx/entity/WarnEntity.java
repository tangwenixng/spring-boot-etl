package com.twx.entity;

import lombok.Data;

@Data
public class WarnEntity {
    /** Job表主键 */
    private Integer jobMasterId;

    /** 预警名称 */
    private String warningTitle;

    /** 预警类型 */
    private String warningType;

    /** 启用状态  */
    private Boolean isActivated;

    /** 详细内容 */
    private String jsonParameter;

    /** 版本号 */
    private Integer version;

}
