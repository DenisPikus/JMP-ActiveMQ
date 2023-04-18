package springbootjms.com.dpikus.activemq.controller.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import springbootjms.com.dpikus.activemq.controller.SimpleController;
import springbootjms.com.dpikus.activemq.dto.SimpleMessage;
import springbootjms.com.dpikus.activemq.jms.Producer;

@RestController
@RequiredArgsConstructor
public class SimpleControllerImpl implements SimpleController<SimpleMessage> {

  private final Producer producer;

  @PostMapping("/queue")
  public void compositeQueueTest(@RequestBody SimpleMessage simpleMessage) {
    producer.sendMessageToQueue(simpleMessage.toString());
  }

  @PostMapping("/topic")
  public void virtualTopicTest(@RequestBody SimpleMessage simpleMessage) {
    producer.sendMessageToTopic(simpleMessage.toString());
  }

}
