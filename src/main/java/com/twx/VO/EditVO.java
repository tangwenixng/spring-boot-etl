package com.twx.VO;

import lombok.Data;

@Data
public class EditVO {

    /** Job表主键 */
    private Integer jobMasterId;

    /** 预警名称 */
    private String warningTitle;

    /** 预警类型 */
    private String warningType;

    /** 启用状态  */
    private Boolean activated;

    /** 阈值 */
    private Integer threshold;

    /** 版本号 */
    private Integer version;
}
