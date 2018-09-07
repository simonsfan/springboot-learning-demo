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
    ZIMUREPLY("字幕问题，有些视频播放时没有显示字幕，请手动加载，方法：在屏幕的上方或者下方有字幕两个字点下，选择对应的字幕加载。"),
    INVALID("您的回复已收到，小编会第一时间抢修哒~"),
    SECRETERROR(10002, "密码错误"),
    SECRETERRORREPLY("客官，所有的密码都是正确的哦~手写容易看错，要注意区分1li0o等极容易看错的字符。建议直接复制密码【所有密码均为4位】，记得复制时不要带空格哦~"),
/*    NOMATCH("未找到该影片资源，没有的资源小编会第一时间补上。\n\n1、确保输入准确的电影或电视剧名称，例如：您需要侏罗纪世界2，请直接输入侏罗纪世界即可。\n\n2、无字幕问题、错误密码问题请公众号分别回复【字幕】、【密码错误】。\n\n3、没有会员的来领取下离线特权，点开链接领取即可https://pan.baidu.com/1t。"),*/
    NOMATCH("最近很多打的错误剧名，请务必检查下。\n特别提示：剧名一定要准确，中文简体，不要加第几季、集、部等。\n\n正确剧名参照搜索照搜索http://t.cn/RSgDhYM \n\n小福利：打开支付宝首页搜索“516277305”，即可领红包" ),
    NOSUPPORTREPLY("暂不支持回复此类型消息~"),
    SUBSCRIBEREPLY("分享最新电影电视剧，韩剧，美剧等，没有的资源会第一时间补上\n\n剧名要准确，中文简体字，不要加上集数或季、部，例如您需要蚁人2，请直接发送蚁人即可\n\n小福利：离线下载无限制权限。没有会员的来领取下离线特权，点开链接领取即可https://pan.baidu.com/1t"),
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
