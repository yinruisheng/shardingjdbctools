package com.yrs.cn.alarmmanager.pojo;

import lombok.Data;

/**
 * @Classname SearchCondition
 * @Description TODO
 * @Date 2020/12/25 20:59
 * @Created by yinruisheng
 */
@Data
public class SearchCondition {
    private Long id;
    private String type;
    private Long createStartTime;
    private Long createEndTime;
}
