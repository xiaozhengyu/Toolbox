package com.zhengyuxiao.toolbox.random;

/**
 * @author xzy
 * @date 2020-12-24 11:55
 * 说明：随机工具
 */
public class RandomUtils {

    private RandomUtils() {
    }

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
}
