package springbootjms.com.dpikus.activemq.jms.impl;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springbootjms.com.dpikus.activemq.jms.Listener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListenerImpl implements Listener {

  public static final String RECEIVED_MESSAGE = "Message: We successfully received your message!";
  private final ActiveMQConnectionFactory connectionFactory;

  @Value("${simple-example.request-queue}")
  private String queue;

  @JmsListener(destination = "${simple-example.request-queue}")
  public void receiveMessageFromQueue(Message jsonMessage) throws JMSException {

    String messageData =  jsonMessage.getStringProperty("message");

    Session session = connectionFactory.createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);

    TextMessage replyMessage = session.createTextMessage(RECEIVED_MESSAGE);
    replyMessage.setJMSDestination(jsonMessage.getJMSReplyTo());
    replyMessage.setJMSCorrelationID(jsonMessage.getJMSCorrelationID());

    MessageProducer producer = session.createProducer(jsonMessage.getJMSReplyTo());
    producer.send(replyMessage);

    session.close();

    log.info("Replyed Message: " + messageData + " to queue - " + queue);

  }

}