package com.demo.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Sheva
 * @Date 2021/3/25
 */
@Slf4j
public class DateUtil {

    public static long calculateTimeDif(String lastReceiveTime) {
//        System.out.println("最后时间" + lastReceiveTime);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = df.parse(currTime());
            Date date2 = df.parse(lastReceiveTime);
            long diff = date1.getTime() - date2.getTime();
//            System.out.println("毫秒数：" + diff);
            //计算两个时间之间差了多少分钟
            return diff / (1000 * 60);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }
        return -1;
    }

    /**
     * 得到当前时间	yyyy-MM-dd HH:mm:ss格式
     *
     * @return 当前时间
     */
    private static String currTime() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currTime = df.format(date);
//        System.out.println("当前时间" + currTime);
        return currTime;
    }
}
