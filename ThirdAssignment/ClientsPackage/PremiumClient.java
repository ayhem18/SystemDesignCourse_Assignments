package clientspackage;

import insurancepackage.InsCompaniesDatabase;
import insurancepackage.InsuranceCompany;
import visitorpackage.ClientVisitor;
import insurancepackage.InsuranceOffer;
import inputoutputpackage.Communication;
import java.util.ArrayList;
import java.util.List;

public class PremiumClient extends BasicClient {

  protected String currentInsCompanyName;
  protected InsuranceCompany currentInsCompany;
  protected final List<InsuranceOffer> offersHistory;

  public PremiumClient() {
    super();
    currentInsCompanyName =
        Communication.insuranceProcedure(InsCompaniesDatabase.getInstance().getNames());
    Communication.reactToName(currentInsCompanyName);
    offersHistory = new ArrayList<>();
  }

  public String getCurrentInsCompanyName() {
    return currentInsCompanyName;
  }

  public void setCurrentInsCompanyName(String currentInsCompanyName) {
    this.currentInsCompanyName = currentInsCompanyName;
  }

  public InsuranceCompany getCurrentInsCompany() {
    return currentInsCompany;
  }

  public void setCurrentInsCompany(InsuranceCompany currentInsCompany) {
    this.currentInsCompany = currentInsCompany;
  }

  public List<InsuranceOffer> getOffersHistory() {
    return offersHistory;
  }

  public void addOffer(InsuranceOffer offer) {
    offersHistory.add(offer);
  }

  @Override
  public void accept(ClientVisitor clientVisitor) {
    clientVisitor.visitPremiumClient(this);
  }
}
