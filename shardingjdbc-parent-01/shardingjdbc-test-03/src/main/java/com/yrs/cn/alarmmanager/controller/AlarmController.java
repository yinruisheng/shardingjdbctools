package com.yrs.cn.alarmmanager.controller;

import com.yrs.cn.alarmmanager.entity.Alarm;
import com.yrs.cn.alarmmanager.pojo.SearchCondition;
import com.yrs.cn.alarmmanager.service.AlarmService;
import com.yrs.cn.alarmmanager.service.IdGeneratorService;
import com.yrs.cn.response.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname AlarmController
 * @Description TODO
 * @Date 2020/12/25 17:02
 * @Created by yinruisheng
 */
@RestController
@Api(value = "Alarm Manager")
@RequestMapping("/alarm")
public class AlarmController {
    @Autowired
    AlarmService alarmService;
    @Autowired
    IdGeneratorService idGeneratorService;

    @PostMapping("/add")
    public R<Boolean> add(@RequestBody Alarm alarm){
        alarm.setId(idGeneratorService.incrementId());
        boolean save = alarmService.save(alarm);
        return R.ok(save);
    }

    @PostMapping("/getbycondition")
    public R<List<Alarm>> getByCondition(@RequestBody SearchCondition searchCondition){
        List<Alarm> alarmByCondition = alarmService.findAlarmByCondition(searchCondition);
        return R.ok(alarmByCondition);
    }


}
