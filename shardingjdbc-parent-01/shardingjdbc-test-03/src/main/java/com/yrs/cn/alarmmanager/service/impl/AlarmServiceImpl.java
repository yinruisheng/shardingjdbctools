package com.yrs.cn.alarmmanager.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yrs.cn.alarmmanager.entity.Alarm;
import com.yrs.cn.alarmmanager.mapper.AlarmMapper;
import com.yrs.cn.alarmmanager.pojo.SearchCondition;
import com.yrs.cn.alarmmanager.service.AlarmService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname AlarmServiceImpl
 * @Description TODO
 * @Date 2020/12/23 15:47
 * @Created by yinruisheng
 */
@Service
public class AlarmServiceImpl extends ServiceImpl<AlarmMapper, Alarm> implements AlarmService {
    @Override
    public List<Alarm> findAlarmByCondition(SearchCondition searchCondition){
        Wrapper<Alarm> query = new QueryWrapper<Alarm>().lambda()
                .eq(searchCondition.getId()!=null,Alarm::getId,searchCondition.getId())
                .eq(searchCondition.getType()!=null,Alarm::getType,searchCondition.getType())
                .between(searchCondition.getCreateStartTime()!=null&&searchCondition.getCreateEndTime()!=null,
                        Alarm::getCreateTime,searchCondition.getCreateStartTime(),searchCondition.getCreateEndTime());
        return this.list(query);
    }
}
