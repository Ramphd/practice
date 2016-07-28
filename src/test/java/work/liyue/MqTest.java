package work.liyue;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Created by hzliyue1 on 2016/7/28,0028, 19:23:23.
 */
public class MqTest {

    public MqTest() throws IOException, TimeoutException {
        MessageConsumer consumer = new MessageConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        MessageProducer producer = new MessageProducer("queue");

        for (int i = 0; i < 1000000; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number "+ i +" sent.");
        }
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        new MqTest();
    }
}
