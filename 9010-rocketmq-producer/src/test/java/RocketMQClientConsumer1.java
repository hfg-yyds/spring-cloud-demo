import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/4/4 09:38
 * @Description:
 */

public class RocketMQClientConsumer1 {
    @SneakyThrows
    public static void main(String[] args) {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("TAG-A-GROUP");
        pushConsumer.setNamesrvAddr("192.168.88.128:9876");
        /**
         * 每一个consumer只能关注一个topic
         * 过滤器
         */
        pushConsumer.subscribe("mytopic","TAG-B");
        /**
         * rocketmq接受消息的方式是通过事件监听的方式
         */
        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            /**
             * @param msgs 可能一次推一堆消息
             * @param context
             * @return
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    byte[] body = msg.getBody();
                    System.out.println(new String(body));
                }
                //默认情况下  这条消息只能被一个consumer消费  点对点的方式
                //这个也叫ACK
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //消费模式
        //默认是集群模式  一个消息只能消费一次
//        pushConsumer.setMessageModel(MessageModel.CLUSTERING); 默认模式
        //一个消息可以被消费多次
        pushConsumer.setMessageModel(MessageModel.BROADCASTING);
        pushConsumer.start();
//        pushConsumer.shutdown();
        //一个集群一个广播模式  貌似广播模式消息都能收到,集群模式只能收到同步发送的消息  ？
    }

}
