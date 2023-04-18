package springbootjms.com.dpikus.activemq.jms;

import javax.jms.JMSException;

import org.apache.activemq.Message;

public interface Listener {

  void receiveMessageFromQueue(Message jsonMessage) throws JMSException;

}
