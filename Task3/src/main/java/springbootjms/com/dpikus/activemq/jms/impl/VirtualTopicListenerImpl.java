package springbootjms.com.dpikus.activemq.jms.impl;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import springbootjms.com.dpikus.activemq.jms.VirtualTopicListener;

@Slf4j
@Component
public class VirtualTopicListenerImpl implements VirtualTopicListener {

  @Value("${virtual-topic-example.topic}")
  private String virtualTopic;

  @JmsListener(destination = "Consumer.myConsumer1." + "${virtual-topic-example.topic}", containerFactory = "queueListenerFactory")
  public void receiveMessageFromTopic_1(Message jsonMessage) throws JMSException {

    TextMessage textMessage = (TextMessage) jsonMessage;
    String messageData = textMessage.getText();
    log.info("Received message: " + messageData + " from virtual topic - " + virtualTopic + " by the first consumer");
  }

  @JmsListener(destination = "Consumer.myConsumer2." + "${virtual-topic-example.topic}", containerFactory = "queueListenerFactory")
  public void receiveMessageFromTopic_2(Message jsonMessage) throws JMSException {

    TextMessage textMessage = (TextMessage) jsonMessage;
    String messageData = textMessage.getText();
    log.info("Received message: " + messageData + " from virtual topic - " + virtualTopic + " by the second consumer");
  }

}
