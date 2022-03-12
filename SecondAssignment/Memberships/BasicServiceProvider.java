package sosadmemberships;

import sosad.Client;
import sosad.Communication;
import sosad.PredictionSystem;

/**
 * BasicServiceProvider is a ServiceProvider. its main service is predicting the client's disease.
 * The functionality is delegated to a private PredictionSystem field.
 */
public class BasicServiceProvider implements ServiceProvider {
  private final PredictionSystem predictionSystem;

  public BasicServiceProvider() {
    predictionSystem = PredictionSystem.getInstance();
  }

  @Override
  public void provideService(Client client) {
    // ask for the client's symptoms
    client.setCurrentSymptoms(Communication.askForSymptoms());
    // the prediction system field will predict the disease
    // then add it to the client's medical history
    client.addDiseaseToMedicalHistory(
        Communication.displayPrediction(
            predictionSystem.makePrediction(client.getCurrentSymptoms())));
  }
}
