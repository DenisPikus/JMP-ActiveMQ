package springbootjms.com.dpikus.activemq.controller;


public interface TestController<T> {

  void virtualTopicTest(T object);
}
