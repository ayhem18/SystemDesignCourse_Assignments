package insurancepackage;

import diseasepackage.Disease;

public class InsuranceOffer {
  private final InsuranceCompany company;
  private final int discount;
  private final Disease disease;

  public InsuranceOffer(InsuranceCompany company, Disease disease, int discount) {
    this.discount = discount;
    this.disease = disease;
    this.company = company;
  }

  public InsuranceCompany getCompany() {
    return company;
  }

  public int getDiscount() {
    return discount;
  }

  public Disease getDisease() {
    return disease;
  }
}
