package com.zhengyuxiao.toolbox.test;

/**
 * @author xzy
 * @date 2020-10-26 10:54
 * 说明：
 */
@FunctionalInterface
public interface TestCodeBlock {

    /**
     * 运行待测试的代码段
     */
    void test();
}
