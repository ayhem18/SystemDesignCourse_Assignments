package visitorpackage;

import clientspackage.BasicClient;
import clientspackage.PremiumClient;
import clientspackage.ProClient;
import clientspackage.ProPlusClient;
import diseasepackage.Disease;
import diseasepackage.PredictionSystem;
import doctorpackage.Doctor;
import doctorpackage.DoctorFinder;
import insurancepackage.InsCompanyFinder;
import insurancepackage.InsuranceCompany;
import insurancepackage.InsuranceOffer;
import inputoutputpackage.*;

public class ServiceVisitor implements ClientVisitor {
  private final PredictionSystem predictionSystem;
  private final DoctorFinder doctorFinder;
  private final InsCompanyFinder companyFinder;

  public ServiceVisitor() {
    predictionSystem = PredictionSystem.getInstance();
    doctorFinder = DoctorFinder.getInstance();
    companyFinder = InsCompanyFinder.getInstance();
  }

  @Override
  public void visitBasicClient(BasicClient client) {
    // ask for the client's symptoms
    client.setCurrentSymptoms(Communication.askForSymptoms());
    // the prediction system field will predict the disease
    // then add it to the client's medical history
    client.addDiseaseToMedicalHistory(
        Communication.displayPrediction(
            predictionSystem.makePrediction(client.getCurrentSymptoms())));
  }

  @Override
  public void visitProClient(ProClient client) {
    // call the basic visit client
    visitBasicClient(client);

    // fetch the prediction's result
    Disease currentPrediction =
        client.getMedicalHistory().get(client.getMedicalHistory().size() - 1);

    // display the corresponding information and add it to history of recommendations
    client.addRecommendation(Communication.displayDiseaseInfo(currentPrediction));
  }

  @Override
  public void visitProPlusClient(ProPlusClient client) {
    Doctor ourDoctor;
    // call the basic visit client
    visitProClient(client);

    // finding, displaying and adding to client the closest doctor
    ourDoctor = doctorFinder.findDoctor(client.getAddress());
    client.setCurrentDoctor(Communication.displayDoctor(ourDoctor));
  }

  @Override
  public void visitPremiumClient(PremiumClient client) {
    // call the basic visit client
    visitBasicClient(client);

    // obtain the company object
    InsuranceCompany company = companyFinder.findCompany(client.getCurrentInsCompanyName());

    // display the offer which depends on the resulting prediction, add current company to Client
    if (company != null) {
      // fetch the result of the prediction
      Disease currentPrediction =
          client.getMedicalHistory().get(client.getMedicalHistory().size() - 1);
      // set the client's current insurance company
      client.setCurrentInsCompany(company);
      // fetch the value of the offer proposed by the company
      int offerValue = company.getOffer(currentPrediction);
      // display the offer
      Communication.announceOffer(client.getCurrentInsCompanyName(), offerValue);
      // store the offer in the client's history
      client.addOffer(new InsuranceOffer(company, currentPrediction, offerValue));
    }
  }
}
