import org.junit.Test;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @Test
    public void testBF() {

    }

    public static void main(String[] args) {
        String str = "abcnd{aa}sasdd{bb}sasdds{saa}";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("aa","韩");
        hashMap.put("bb","福");
        hashMap.put("cc","贵");

        extracted(hashMap,str);
    }

    public static void extracted(Map<String,String> map, String str) {
        char[] chars = str.toCharArray();
        StringBuilder subStr = new StringBuilder();
        int length = chars.length;
        Set<String> set = map.keySet();
        int temp =0;
        boolean flag =false;
        for (int i = 0; i < length; i++) {
            if (chars[i]=='{') {
                flag = true;
            }
            if (flag) {
                subStr = subStr.append(chars[i+1]);
            }
        }
        System.out.println(str);
    }

}
