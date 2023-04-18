package springbootjms.com.dpikus.activemq.jms.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import springbootjms.com.dpikus.activemq.jms.DurableListener;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Slf4j
@Component
public class DurableListenerImpl implements DurableListener {

  @Value("${simple-example.dur-topic}")
  private String topic;

  @JmsListener(destination = "${simple-example.dur-topic}", containerFactory = "topicDurListenerFactory")
  public void receiveMessageFromTopicByDurableSubscriber(Message jsonMessage) throws JMSException {

    TextMessage textMessage = (TextMessage) jsonMessage;
    String messageData = textMessage.getText();
    log.info("Received message: " + messageData + " from topic - " + topic + " by the durable subscriber.");
  }

}
