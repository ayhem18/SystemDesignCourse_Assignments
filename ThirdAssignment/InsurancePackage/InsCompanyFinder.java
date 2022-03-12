package insurancepackage;

import doctorpackage.DoctorFinder;
import java.util.List;

public class InsCompanyFinder {

  private final InsCompaniesDatabase database;

  /** private constructor and a public static getInstance() method ensuring the singleton pattern */
  private InsCompanyFinder() {
    database = InsCompaniesDatabase.getInstance();
  }

  private static volatile InsCompanyFinder insCompanyFinder;

  public static InsCompanyFinder getInstance() {
    if (insCompanyFinder == null)
      synchronized (DoctorFinder.class) {
        if (insCompanyFinder == null) insCompanyFinder = new InsCompanyFinder();
      }
    return insCompanyFinder;
  }

  /** the method finds the insurance company according to the provided name */
  public InsuranceCompany findCompany(String name) {
    List<InsuranceCompany> companies = database.getDatabase();
    for (InsuranceCompany company : companies) {
      if (company.getName().equals(name)) return company;
    }
    return null;
  }

  // this method returns a list containing the companies names
  public List<String> getCompaniesNames() {
    return database.getNames();
  }
}
