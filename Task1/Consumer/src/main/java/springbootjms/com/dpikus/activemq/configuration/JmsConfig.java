package springbootjms.com.dpikus.activemq.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class JmsConfig {

  @Value("${spring.activemq.broker-url}")
  private String broker_url;

  @Value("${spring.activemq.user}")
  private String broker_username;

  @Value("${spring.activemq.password}")
  private String broker_password;

  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL(broker_url);
    connectionFactory.setPassword(broker_username);
    connectionFactory.setUserName(broker_password);
    return connectionFactory;
  }

  @Bean(name = "queueListenerFactory")
  public DefaultJmsListenerContainerFactory jmsListenerQueueContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    return factory;
  }

  @Bean(name = "topicNonDurListenerFactory")
  public DefaultJmsListenerContainerFactory jmsNonDurListenerTopicContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setPubSubDomain(true);
    factory.setSubscriptionDurable(false);
    return factory;
  }

  @Bean(name = "topicDurListenerFactory")
  public DefaultJmsListenerContainerFactory jmsDurListenerTopicContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setPubSubDomain(true);
    factory.setSubscriptionDurable(true);
    factory.setClientId("dur-1");
    return factory;
  }

}
