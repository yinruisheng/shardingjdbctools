package com.yrs.cn.alarmmanager.controller;

/**
 * @Classname TestStr
 * @Description TODO
 * @Date 2020/12/25 18:22
 * @Created by yinruisheng
 */
public class TestStr {
    public static void main(String[] args) {
        String s = "yrs_base_${2020..2022}.alarm_${1..12}";
        //获取yrs_base_ 以及 alarm_
//        System.out.println(s.indexOf("$"));
        System.out.println(s.substring(0,s.indexOf("$")));
//        System.out.println(s.lastIndexOf("$"));
//        System.out.println(s.indexOf("}."));
        System.out.println(s.substring(s.indexOf("}.")+2, s.lastIndexOf("$")));
    }
}
