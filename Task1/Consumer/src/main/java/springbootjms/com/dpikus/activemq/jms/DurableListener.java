package springbootjms.com.dpikus.activemq.jms;

import javax.jms.JMSException;

import org.apache.activemq.Message;

public interface DurableListener {

  void receiveMessageFromTopicByDurableSubscriber(Message jsonMessage) throws JMSException;

}
