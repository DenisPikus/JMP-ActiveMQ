package springbootjms.com.dpikus.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class RequesterApplication {

  public static void main(String[] args) {
    SpringApplication.run(RequesterApplication.class, args);
  }

}
