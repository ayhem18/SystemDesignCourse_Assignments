package sosadmemberships;

import sosad.Client;
import sosad.Communication;
import sosad.Disease;

/**
 * The ProServiceProvider is a concrete Decorator for BasicServiceProvider created for PRO clients.
 * In addition to disease prediction provided by super.provideService(), this class offers the
 * client a set of advices and recommendations specific to the prediction's result
 */
public class ProServiceProvider extends PaidServiceProvider {

  public ProServiceProvider() {
    super();
  }

  @Override
  public void provideService(Client client) {

    // call the basic Service provider
    super.provideService(client);

    // fetch the prediction's result
    Disease currentPrediction =
        client.getMedicalHistory().get(client.getMedicalHistory().size() - 1);

    // display the corresponding information
    Communication.displayDiseaseInfo(currentPrediction);
  }
}
