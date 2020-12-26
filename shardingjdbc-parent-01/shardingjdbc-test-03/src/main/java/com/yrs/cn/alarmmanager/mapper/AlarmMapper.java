package com.yrs.cn.alarmmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yrs.cn.alarmmanager.entity.Alarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmMapper extends BaseMapper<Alarm> {
}
