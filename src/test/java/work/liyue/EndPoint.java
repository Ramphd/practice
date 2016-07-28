package work.liyue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by hzliyue1 on 2016/7/28,0028, 19:07:12.
 */
public abstract class EndPoint {
    protected Channel channel;
    protected Connection connection;
    //routing key
    protected String routingKey;

    public EndPoint(String routingKey) throws IOException, TimeoutException {
        this.routingKey = routingKey;
        //Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();
        //hostname of your rabbitmq server
        factory.setHost("211.87.227.209");
        factory.setPort(5672);
        factory.setUsername("wadqse");
        factory.setPassword("wadqse");
        //getting a connection
        connection = factory.newConnection();
        //creating a channel
        channel = connection.createChannel();
        //declaring a queue for this channel. If queue does not exist,
        //it will be created on the server.
        channel.queueDeclare(routingKey, false, false, false, null);
        /**
         * 关闭channel和connection。并非必须，因为隐含是自动调用的。
         * @throws IOException
         */
    }

}
