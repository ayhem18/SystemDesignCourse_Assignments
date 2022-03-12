package sosadmemberships;

import sosad.Client;

/**
 * This is an abstract DECORATOR for the BasicServiceProvider class Derived classes should implement
 * The provideService method in such a way that the work is delegated to the super.provideService()
 * while performing additional functionality either before or after the call
 */
public abstract class PaidServiceProvider implements ServiceProvider {
  protected BasicServiceProvider basic;

  public PaidServiceProvider() {
    basic = new BasicServiceProvider();
  }

  @Override
  public void provideService(Client client) {
    this.basic.provideService(client);
  }
}
