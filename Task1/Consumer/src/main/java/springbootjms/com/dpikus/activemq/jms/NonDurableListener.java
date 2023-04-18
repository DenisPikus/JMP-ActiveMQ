package springbootjms.com.dpikus.activemq.jms;

import javax.jms.JMSException;

import org.apache.activemq.Message;

public interface NonDurableListener {

  void receiveMessageFromTopicByNonDurableSubscriber(Message jsonMessage) throws JMSException;

}
