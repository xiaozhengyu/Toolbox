package com.zhengyuxiao.toolbox.test;

/**
 * @author xzy
 * @date 2020-10-26 10:55
 * 说明：
 */
public class Assert {

    public static void noException(TestCodeBlock testCodeBlock) {
        boolean testResult = test(testCodeBlock);
        assert testResult;
    }

    private static boolean test(TestCodeBlock testCodeBlock) {
        try {
            testCodeBlock.test();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
