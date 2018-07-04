package com.simons.cn.springbootdemo.Enum;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.util
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/28 17:27
 */
public enum ConstantEnum {

    ERROR(-1, "system error!"),
    SUCCESS(100, "success"),
    LESS10(101, "自定义异常信息-我小于10岁"),
    MORE50(5001, "自定义异常信息-我大于50岁"),

    ZIMU(10001, "字幕"),
    SECRETERROR(10002, "密码错误"),
    SECRETERRORREPLY("客官，所有的密码都是正确的哦~手写容易看错，要注意区分1li0o等极容易看错的字符。建议直接复制密码【所有密码均为4位】，记得复制时不要带空格哦~~"),
    ZIMUREPLY("字幕问题，有些视频播放时没有显示字幕，请手动加载，方法：在屏幕的上方或者下方有字幕两个字点下，选择对应的字幕加载。"),
    SUBSCRIBEREPLY("小福利：打开支付宝首页搜索“516277305”，即可领红包\n1、字母请用小写，汉字请打准确，特殊符号请省略掉。剧名要准确无误。\n\n2、无字幕问题、错误密码问题请公众号分别回复【字幕】、【密码错误】。\n\n3、重要：请保证剧名准确无误。中文译名优先，英文尽量不用。繁体不支持，简称不支持。\n\n感谢您的关注哦 么么哒~~"),
    NOSUPPORTREPLY("暂不支持回复此类型消息哦~"),
    ;

    private Integer code;
    private String msg;
    private String content;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ConstantEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ConstantEnum(String msg) {
        this.msg = msg;
    }

    ConstantEnum(Integer code, String msg, String content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }
}
