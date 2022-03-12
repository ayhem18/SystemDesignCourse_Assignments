package sosadmemberships;

import sosad.Client;

/**
 * The ServiceProvider interface is a common interface to all classes that provide services to
 * clients. With a Client object as an argument, the ServiceProvider object will operate according
 * to the client's membership
 */
public interface ServiceProvider {
  void provideService(Client client);
}
