package springbootjms.com.dpikus.activemq.jms.impl;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import springbootjms.com.dpikus.activemq.jms.Producer;

@Slf4j
@Component
@RestController
public class ProducerImpl implements Producer {

  @Autowired
  @Qualifier("queueJmsTemplate")
  private JmsTemplate jmsTemplate;

  @Value("${simple-example.request-queue}")
  private String queue;

  @Autowired
  ActiveMQConnectionFactory connectionFactory;

  public void sendMessageToQueueWithReply(String messageText) throws JMSException {

    log.info("Sending message " + messageText + " to queue - " + queue);

    Connection connection = connectionFactory.createConnection();
    connection.start();
    Session session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

    TemporaryQueue temporaryQueue = session.createTemporaryQueue();

    Message message = (Message) session.createMessage();
    message.setStringProperty("message", messageText);
    message.setJMSReplyTo(temporaryQueue);
    message.setJMSCorrelationID(Long.toHexString(
            new Random(System.currentTimeMillis()).nextLong()));

    jmsTemplate.convertAndSend(queue, message);

    MessageConsumer consumer = session.createConsumer(temporaryQueue);
    TextMessage reply = (TextMessage)consumer.receive();

    session.close();

    log.info("Received successfully: " +  reply.getText());
  }

}
