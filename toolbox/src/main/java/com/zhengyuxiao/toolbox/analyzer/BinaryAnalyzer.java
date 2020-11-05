package com.zhengyuxiao.toolbox.analyzer;

/**
 * @author xzy
 * @date 2020-11-02 08:58
 * 说明：二进制分析工具
 */
public class BinaryAnalyzer {
    public static void integer2Binary(int num) {
        int[] binaryCodeArray = new int[32];

        // 00000000000000000000000000000001
        int temp;
        for (int i = 0; i < 32; i++) {
            temp = num;
            num = num ^ 0x00000000000000000000000000000001;
        }
    }
}
