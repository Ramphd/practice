package work.liyue;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by hzliyue1 on 2016/7/28,0028, 19:15:09.
 */
public class MessageProducer extends EndPoint{


    public MessageProducer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void sendMessage(Serializable object)throws IOException {
        channel.basicPublish("", routingKey, null, SerializationUtils.serialize(object));
    }
}
