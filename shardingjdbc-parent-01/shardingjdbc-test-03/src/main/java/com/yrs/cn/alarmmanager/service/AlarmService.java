package com.yrs.cn.alarmmanager.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yrs.cn.alarmmanager.entity.Alarm;
import com.yrs.cn.alarmmanager.pojo.SearchCondition;

import java.util.List;

public interface AlarmService extends IService<Alarm> {
    List<Alarm> findAlarmByCondition(SearchCondition searchCondition);
}
