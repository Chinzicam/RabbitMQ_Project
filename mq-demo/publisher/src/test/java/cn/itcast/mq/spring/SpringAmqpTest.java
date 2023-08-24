package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        String queueName = "simple.queue";
        String message = "欢迎使用SpringAMQP";
        rabbitTemplate.convertAndSend(queueName, message);
    }
    @Test
    public void testWorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "Hello, SpringAMQP! __ ";
        for (int i = 1; i <= 50; i++) {
            // 循环发送50条消息，带上消息编号
            rabbitTemplate.convertAndSend(queueName, message + i);
            // 休眠20ms，模拟在1s内发送完
            Thread.sleep(20);
        }
    }
}
