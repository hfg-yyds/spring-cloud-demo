import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * @Author: Zero
 * @Date: 2022/4/4 09:22
 * @Description:
 */
public class RocketMQClientProducer {
    @SneakyThrows
    public static void main(String[] args) {
        DefaultMQProducer mqProducer = new DefaultMQProducer("xxyy");
        mqProducer.setNamesrvAddr("192.168.88.128:9876");
        mqProducer.start();
        /**
         * topic 消息将要发送的地址
         * body  消息中的具体数据
         *
         */
        Message message = new Message("mytopic","my name is xxyy".getBytes(StandardCharsets.UTF_8));
        mqProducer.send(message);
    }
}
