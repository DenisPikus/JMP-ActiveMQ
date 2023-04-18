package springbootjms.com.dpikus.activemq.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springbootjms.com.dpikus.activemq.controller.TestController;
import springbootjms.com.dpikus.activemq.dto.SimpleMessage;
import springbootjms.com.dpikus.activemq.jms.VirtualTopicProducer;

@RestController
@RequiredArgsConstructor
public class TestControllerImpl implements TestController<SimpleMessage> {

  private final VirtualTopicProducer virtualTopicProducer;

  @PostMapping("/virtual-topic")
  public void virtualTopicTest(@RequestBody SimpleMessage simpleMessage) {
    virtualTopicProducer.sendMessage(simpleMessage.toString());
  }

}
