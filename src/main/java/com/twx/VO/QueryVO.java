package com.twx.VO;

import lombok.Data;

@Data
public class QueryVO {
    /** Job表主键 */
    private Integer jobMasterId;

    /** 预警名称 */
    private String warningTitle;

    /** 预警类型 */
    private String warningType;

    /** 启用状态  */
    private String activated;
}
