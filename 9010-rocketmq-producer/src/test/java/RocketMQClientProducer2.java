import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * @Author: Zero
 * @Date: 2022/4/4 11:51
 * @Description:
 */
public class RocketMQClientProducer2 {
    @SneakyThrows
    public static void main(String[] args) {
        DefaultMQProducer mqProducer = new DefaultMQProducer("xxyy");
        mqProducer.setNamesrvAddr("192.168.88.128:9876");
        mqProducer.start();

        Message message = new Message("mytopic", "TAG-B", "Hello".getBytes(StandardCharsets.UTF_8));
        //单向消息,网络不确定
        mqProducer.sendOneway(message);
    }
}
