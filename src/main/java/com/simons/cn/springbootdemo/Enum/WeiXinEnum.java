package com.simons.cn.springbootdemo.Enum;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.Enum
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/7/3 9:29
 */
public enum  WeiXinEnum {

    MESSAGE_TEXT("text","文本消息"),
    MESSAGE_VOICE("voice","语音消息"),
    MESSAGE_VIDEO("video","视频消息"),
    MESSAGE_SHORTVIDEO("shortvideo","小视频消息"),
    MESSAGE_LOCATION("location","地理位置消息"),
    MESSAGE_LINK("link","链接消息"),
    MESSAGE_EVENT("event","取消或关注事件"),
    MESSAGE_IMAGE("image","图片消息");

    private String contentType;
    private String desc;


    public String getContentType() {
        return contentType;
    }

    public String getDesc() {
        return desc;
    }

     WeiXinEnum(String contentType, String desc) {
        this.contentType = contentType;
        this.desc = desc;
    }
}
