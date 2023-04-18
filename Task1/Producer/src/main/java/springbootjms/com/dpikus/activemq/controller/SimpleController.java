package springbootjms.com.dpikus.activemq.controller;

public interface SimpleController<T> {

  void compositeQueueTest(T object);

  void virtualTopicTest(T object);

}
