import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
        Message message1 = new Message("mytopic","my name is xxyy1".getBytes(StandardCharsets.UTF_8));
        Message message2 = new Message("mytopic","my name is xxyy2".getBytes(StandardCharsets.UTF_8));
        Message message3 = new Message("mytopic","my name is xxyy3".getBytes(StandardCharsets.UTF_8));


        ArrayList<Message> messageArrayList = new ArrayList<>();
        messageArrayList.add(message1);
        messageArrayList.add(message2);
        messageArrayList.add(message3);

        //发完消息之后  会等一下broker返回信息是否收到
        //同步消息发送
        SendResult sendResult = mqProducer.send(messageArrayList);

        //异步可靠消息
        //不会阻塞,采用监听事件方式等待broker返回的确认
        Message message = new Message("mytopic", "韩福贵".getBytes(StandardCharsets.UTF_8));
        mqProducer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("消息发送成功");
                System.out.println("sendResult"+sendResult);
            }

            @Override
            public void onException(Throwable e) {
                //如果发生异常 尝试重投消息
                //或者调整业务逻辑
                e.printStackTrace();
                System.out.println("发送异常");
            }
        });

        System.out.println(sendResult);
        /**
         * 这个不能随便写   因为你不知道异步回调是什么时候发生的
         * 若回调晚于shutdown  就会发生broker连接失败异常
         */
        mqProducer.shutdown();
    }
}
