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
    ;

    private Integer code;
    private String msg;

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
}
