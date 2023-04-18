package springbootjms.com.dpikus.activemq.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Accessors(chain = true)
public class SimpleMessage implements Serializable {

  private String messageId;
  private String message;

}