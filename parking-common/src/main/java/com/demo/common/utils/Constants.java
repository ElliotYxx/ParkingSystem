package com.demo.common.utils;

/**
 * @Author Sheva
 * @Date 2021/2/23
 */
public final class Constants {

    /** 前后端响应相关参数 **/
    public static final int STATUS_OK = 20000;
    public static final int STATUS_FAIL = 500;
    public static final String MESSAGE_OK = "成功";
    public static final String MESSAGE_FAIL = "失败：";

    /** 管理员id代码 **/
    public final static Integer ADMIN_ID = 2;

    /** 图片文件存储路径 **/
    public static final String IMAGE_SAVE_PATH = "/Users/sheva/IdeaProjects/ParkingSystem/parking-admin/src/main/resources/upload_images/";

    /** 停车每分钟的花费 **/
    public static final Double COST_PER_MINUTE = 0.25;
}
