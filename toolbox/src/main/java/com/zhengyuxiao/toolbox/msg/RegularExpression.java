package com.zhengyuxiao.toolbox.msg;

/**
 * @author xzy
 * @date 2021-01-28 14:41
 * 说明：常用正则表达式
 */
public class RegularExpression {
    /**
     * 中国、手机号码
     */
    public static final String CHINESE_MOBILE_PHONE_NUMBER = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

    /**
     * 判断指定文本是否匹配指定的正则表达式
     *
     * @param content          - 待匹配的内容
     * @param regularException - 正则表达式
     */
    public static boolean isMatched(String content, String regularException) {
        return content.matches(regularException);
    }

    public static void main(String[] args) {
        // 实测：10086无效
        System.out.println(isMatched("10086", CHINESE_MOBILE_PHONE_NUMBER));
    }
}
