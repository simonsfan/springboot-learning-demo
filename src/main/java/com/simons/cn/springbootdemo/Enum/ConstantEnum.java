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
    ZIMUREPLY("字幕问题，有些视频播放时没有显示字幕，请手动加载，方法：在屏幕的上方或者下方有字幕两个字点下，选择对应的字幕加载。\n\n需要污~福利电影的小伙伴请添加QQ:3032879850 获取。"),
    ZHOGNGZI(10003, "种子"),
    ZHOGNGZIERROR(10004, "BT种子使用方法链接：https://pan.baidu.com/s/1HvHX5jMeZ-R6_OPH3fE7uw 密码：u8hr\n\n小福利：离线下载无限制权限。没有会员的来领取下离线特权，点开链接领取即可https://pan.baidu.com/1t\n\n需要污~福利电影的小伙伴请添加QQ:3032879850 获取。"),
    SECRETERROR(10002, "密码错误"),
    SECRETERRORREPLY("客官，所有的密码都是正确的哦~手写容易看错，要注意区分1li0o等极容易看错的字符。建议直接复制密码【所有密码均为4位】，记得复制时不要带空格哦~\n\n需要污~福利电影的小伙伴请添加QQ:3032879850 获取。"),
    NOMATCH("小福利：打开支付宝首页搜索“516277305”，即可领红包\n1、输入具体的电影或电视剧名称即可，例如：您需要侏罗纪世界2，请直接输入侏罗纪世界即可。\n\n2、无字幕问题、错误密码、种子问题请公众号分别回复【字幕】、【密码错误】、【种子】。\n\n3、没有会员的来领取下离线特权，点开链接领取即可https://pan.baidu.com/1t\n\n4、需要污~福利电影的小伙伴请添加QQ:3032879850 获取。\n\n感谢您的关注哦 么么哒~"),
    NOSUPPORTREPLY("暂不支持回复此类型消息~"),
    SUBSCRIBEREPLY("分享最新电影电视剧，韩剧，美剧，日剧，泰剧等\n\n剧名要准确，中文简体字\n\n小福利：离线下载无限制权限。没有会员的来领取下离线特权，点开链接领取即可https://pan.baidu.com/1t\n\n快开始你的影视之旅吧~"),
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
