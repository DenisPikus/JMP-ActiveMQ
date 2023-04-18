package springbootjms.com.dpikus.activemq.controller;

public interface SimpleController<T> {

  void sendMessageToQueue(T object);

}
