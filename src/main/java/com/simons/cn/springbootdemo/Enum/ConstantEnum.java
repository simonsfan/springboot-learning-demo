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
    ZIMUREPLY("字幕问题，有些视频播放时没有显示字幕，请手动加载，方法：在屏幕的上方或者下方有字幕两个字点下，选择对应的字幕加载。~"),
    INVALID("您的回复已收到，小编会第一时间抢修哒~"),
    SECRETERROR(10002, "密码错误"),
    GUI_ZE(10005, "规则"),
    SECRETERRORREPLY("客官，所有的密码都是正确的哦~手写容易看错，要注意区分1li0o等极容易看错的字符。建议直接复制密码【所有密码均为4位】，记得复制时不要带空格哦~"),
/*    NOMATCH("未找到该影片资源，没有的资源小编会第一时间补上。\n\n1、确保输入准确的电影或电视剧名称，例如：您需要侏罗纪世界2，请直接输入侏罗纪世界即可。\n\n2、无字幕问题、错误密码问题请公众号分别回复【字幕】、【密码错误】。\n\n3、没有会员的来领取下离线特权，点开链接领取即可https://pan.baidu.com/1t。"),*/

//NOMATCH("最近很多打的错误剧名，请务必检查下。\n特别提示：剧名一定要准确，中文简体，不要加第几季、集、部等。\n\n正确剧名参照搜索照搜索http://t.cn/RSgDhYM \n\n小福利：打开支付宝首页搜索“516277305”，即可领红包" ),
NOMATCH("求片或者忘记更新，可以添加QQ群：779594107 反馈~\n\n宝贝儿、影、无双、李茶的姑妈、胖子行动队、悲伤逆流成河、天坑鹰猎、如懿传、延禧攻略、行尸走肉、东京食尸鬼等热门电影电视剧已更新上线。\n\n剧名一定要准确无误，错误剧名无法识别。剧名以百科、豆瓣等大众认可的名称为准。【建议回复“规则”，看看回复时注意事项】\n\n求片或者忘记更新，可以添加QQ群：779594107 反馈，影视资源共享群~" ),
    NOSUPPORTREPLY("暂不支持回复此类型消息~"),

    SUBSCRIBEREPLY("分享最新最热电影电视剧，韩剧，美剧等，没有的资源会第一时间补上\n\n使用说明：直接回复您想要的电影或电视剧名称即可，例如回复侏罗纪世界\n\n小福利：离线下载无限制权限。没有会员的来领取下离线特权，点开链接领取即可https://pan.baidu.com/1t\n\n如果不会使用，请在公众号里回复 ‘规则’ 获取使用说明哦~\n\n求片或者忘记更新，可以添加QQ群：779594107 反馈~"),
   
    GUIZE("【回复规则】\n" +
            "\n" +
            "前言概述：不要加任何修饰词，如第几部，第几季，多少期，哪一年等，，只需回复剧名，如移动迷宫3：死亡解药，只需回复移动迷宫即可。\n" +
            "剧名一定要准确无误【特殊情况下边有说明】总之多尝试吧。1、必须回复官方剧名，以豆瓣、百科收录为准。 不要打简称，不要用繁体字。【特别说明：部分有逗号问号等特殊符号需忽略掉的除外，详细在第4条讲】另外错别字、错误剧名，漏字，多字等都是无法识别的，所以，请务必保证剧名的准确性。\n" +
            "\n" +
            "2、有些国外影视剧，请用中文译名，百度、豆瓣为准，可以多尝试几个译名。没有中文翻译的可用英文。另外港澳台地区也会有与大陆地区有不一样的译名，请尽量以大陆译名为准。 \n" +
            "\n" +
            "3、字母问题：如果没有中文译名，请务必用小写字母，不要用大写。\n" +
            "\n" +
            "4、带逗号，叹号，问号，破折号，空格等特殊格式忽略即可，只回复汉字即可。如你好，旧时光 直接 回复 你好旧时光 即可。 \n" +
            "\n" +
            "5、请不要带第几季，第几部，第几集等具体内容。只需回复影视剧名称即可。电影系列如蝙蝠侠：黑暗骑士，最好只回复蝙蝠侠即可，以此类推。\n" +
            "\n" +
            "6、补充5，系列剧以点进链接，查看到具体内容为准。 \n" +
            "\n" +
            "7、补充：有部分重名的影视剧可能分两个链接，最好把全部链接看完。 \n" +
            "\n" +
            "8、补充：很多资源都是种子类型，以torrent结尾的，如果不会使用种子，在公众号回复 ‘种子’ 获取使用说明。\n" +
            "\n" +
            "9、有些链接失效了，过段时间重新回复。 部分影视剧链接失效，可能同时回复了两个，请务必把回复的看完，两个链接都试下。\n" +
            "最后如果以上都没有，请耐心等待小编后期添加。 \n" +
            "\n" +
            "感谢您的关注哦 爱你~~")
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
