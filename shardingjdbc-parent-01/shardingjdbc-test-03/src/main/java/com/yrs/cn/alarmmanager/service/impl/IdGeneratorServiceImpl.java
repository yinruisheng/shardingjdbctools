package com.yrs.cn.alarmmanager.service.impl;

import com.yrs.cn.alarmmanager.service.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Classname IdGeneratorServiceImpl
 * @Description TODO
 * @Date 2020/12/24 21:07
 * @Created by yinruisheng
 */
@Service
public class IdGeneratorServiceImpl implements IdGeneratorService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String ID_KEY = "id:generator:product";

    @Override
    public Long incrementId() {
        return stringRedisTemplate.opsForValue().increment(ID_KEY);
    }
}
