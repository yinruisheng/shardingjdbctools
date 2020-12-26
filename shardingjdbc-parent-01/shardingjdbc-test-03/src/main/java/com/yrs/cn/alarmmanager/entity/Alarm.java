package com.yrs.cn.alarmmanager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @Classname Alarm
 * @Description TODO
 * @Date 2020/12/23 15:44
 * @Created by yinruisheng
 */
@Data
@TableName(value = "alarm")
public class Alarm {
    @Id
    private Long id;
    private String type;
    private Long createTime;
    public Alarm(){}
    public Alarm(Long id,String type,Long createTime){
        this.id = id;
        this.type = type;
        this.createTime = createTime;
    }
}
