import org.junit.Test;

import java.text.MessageFormat;

/**
 * @Author: Zero
 * @Date: 2022/4/21 09:04
 * @Description:
 */
public class TestClass {
    @Test
    public void test1()  {
        Object[] params = new Object[]{"1234", "5"};
        String msg = MessageFormat.format("验证码:{0},您正在登录管理后台，{1}分钟内输入有效。", params);
        System.out.println(msg);
    }

}
