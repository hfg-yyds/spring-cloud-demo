package com.hfg.util;

import org.testng.annotations.Test;

import java.text.MessageFormat;

/**
 * @Author: Zero
 * @Date: 2022/4/23 23:27
 * @Description:
 */
public class Utils {
    @Test
    public void test1()  {
        Object[] params = new Object[]{"1234", "5"};
        String msg = MessageFormat.format("验证码:{0},您正在登录管理后台，{1}分钟内输入有效。", params);
        System.out.println(msg);
    }
}
