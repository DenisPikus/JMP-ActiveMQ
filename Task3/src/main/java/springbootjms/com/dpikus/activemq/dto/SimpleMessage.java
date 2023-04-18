package springbootjms.com.dpikus.activemq.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Accessors(chain = true)
public class SimpleMessage implements Serializable {

  private String simpleMessageId;
  private String simpleMessage;

  @Override
  public String toString() {
    return "SimpleMessage{" +
        "simpleMessageId='" + simpleMessageId + '\'' +
        ", simpleMessage='" + simpleMessage + '\'' +
        '}';
  }
}