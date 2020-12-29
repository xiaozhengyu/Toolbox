package com.zhengyuxiao.toolbox.random;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author xzy
 * @date 2020-12-24 11:55
 * 说明：随机工具
 */
public class RandomUtils {

    private RandomUtils() {
    }

    public static final Random RANDOM = new Random();

    /**
     * 字母集
     */
    public static final char[] LETTER_SET = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'};

    /**
     * 数字集
     */
    public static final char[] NUMBER_SET = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    /**
     * 随机决策
     *
     * @param probability - 返回值为true的概率
     * @return true or false
     */
    public static boolean randomDecision(double probability) {
        // Math.random()随机返回数值在0.0和1.0之间的double值。
        return Math.random() <= probability;
    }

    /**
     * 获取随机字符串
     *
     * @param source - 字符集
     * @param length - 字符串长度
     * @return - 随机字符串
     */
    public static String getRandomString(char[] source, int length) {
        StringBuilder randomStringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomStringBuilder.append(source[RANDOM.nextInt(source.length)]);
        }
        return randomStringBuilder.toString();
    }

    public static <E> String getRandomString(List<E> source, int length) {
        // 1.打乱字符集
        Collections.shuffle(source);

        // 2.随机抽取元素生成字符串
        StringBuilder randomStringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomStringBuilder.append(source.get(RANDOM.nextInt(source.size())));
        }
        return randomStringBuilder.toString();
    }
}
