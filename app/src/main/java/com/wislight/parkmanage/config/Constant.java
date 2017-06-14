package com.wislight.parkmanage.config;

/**
 * Created by Administrator on 2017/6/14.
 * @author zzx
 * @explain:常量类（网络接口等）
 */

public class Constant {
    // 网络协议
    public static final String AGREEMENT = "http://";
    // public static final String SERVER_IP = "192.168.0.109"; //本地测试地址
    // private static String protNumber = "8080"; // 本地测试端口号

    // IP地址
    public static final String SERVER_IP = "192.168.0.164";
    public static String OPENFIRE_SERVER_IP = "192.168.0.164";
    // 端口号
    private static String protNumber = "5222";
    // 接口共用地址
    public static final String mainPath = AGREEMENT + SERVER_IP + ":"
            + protNumber + "/device_server/";
    public static int STEP_INFO_UPLOAD_NO = 0; /* 未上传 */
    public static int STEP_INFO_UPLOAD_YES = 1; /* 已上传 */
    // 头像保存地址
    public static String photoPath = Constant.mainPath + "faceImg/";
}
