package sosadmemberships;

import sosad.Client;
import sosad.Communication;
import sosad.Disease;

/**
 * The PremiumServiceProvider is a concrete decorator created for Premium clients. Before predicting
 * the disease, which is ensured by super.provideService(), this class verifies the user's insurance
 * company. If the latter is one of application's partners, a percentage of the treatment cost is
 * covered. The functionality is delegated to the private InsCompanyFinder field
 */
public class PremiumServiceProvider extends PaidServiceProvider {
  private final InsCompanyFinder finder;

  public PremiumServiceProvider() {
    super();
    finder = InsCompanyFinder.getInstance();
  }

  @Override
  public void provideService(Client client) {

    // obtain the company's name
    String companyName = Communication.insuranceProcedure(finder.getCompaniesNames());
    Communication.reactToName(companyName);

    // call the basic Service provider
    super.provideService(client);

    // obtain the company object
    InsuranceCompany company = finder.findCompany(companyName);

    // display the offer which depends on the resulting prediction
    if (company != null) {
      Disease currentPrediction =
          client.getMedicalHistory().get(client.getMedicalHistory().size() - 1);
      Communication.announceOffer(companyName, company.getOffer(currentPrediction));
    }
  }
}
