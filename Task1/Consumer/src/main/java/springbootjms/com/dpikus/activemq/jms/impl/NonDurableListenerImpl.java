package springbootjms.com.dpikus.activemq.jms.impl;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import springbootjms.com.dpikus.activemq.jms.NonDurableListener;

@Slf4j
@Component
public class NonDurableListenerImpl implements NonDurableListener {

  @Value("${simple-example.dur-topic}")
  private String topic;

  @JmsListener(destination = "${simple-example.dur-topic}", containerFactory = "topicNonDurListenerFactory")
  public void receiveMessageFromTopicByNonDurableSubscriber(Message jsonMessage) throws JMSException {
    TextMessage textMessage = (TextMessage) jsonMessage;
    String messageData = textMessage.getText();
    log.info("Received message: " + messageData + " from topic - " + topic + " by the non durable subscriber.");
  }

}
