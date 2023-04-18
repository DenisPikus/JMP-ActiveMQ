package springbootjms.com.dpikus.activemq.jms;

import javax.jms.JMSException;

public interface Producer {

  void sendMessageToQueueWithReply(String message) throws JMSException;

}
