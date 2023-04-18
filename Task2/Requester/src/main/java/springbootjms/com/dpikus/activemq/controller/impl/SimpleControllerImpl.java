package springbootjms.com.dpikus.activemq.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import springbootjms.com.dpikus.activemq.controller.SimpleController;
import springbootjms.com.dpikus.activemq.dto.SimpleMessage;
import springbootjms.com.dpikus.activemq.jms.Producer;

import javax.jms.JMSException;

@RestController
@RequiredArgsConstructor
public class SimpleControllerImpl implements SimpleController<SimpleMessage> {

  private final Producer producer;

  @PostMapping("/queue")
  @ResponseStatus(HttpStatus.OK)
  public void sendMessageToQueue(@RequestBody SimpleMessage simpleMessage) {
    try {
      producer.sendMessageToQueueWithReply(simpleMessage.toString());
    } catch (JMSException e) {
      throw new RuntimeException(e);
    }
  }

}
