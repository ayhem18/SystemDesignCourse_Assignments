package sosadmemberships;

import sosad.DoctorFinder;
import java.util.List;

public class InsCompanyFinder {

  private final InsCompaniesDatabase database;

  /** private constructor and a public static getInstance() method ensuring the singleton pattern */
  private InsCompanyFinder() {
    database = InsCompaniesDatabase.getInstance();
  }

  private static volatile InsCompanyFinder finder;

  public static InsCompanyFinder getInstance() {
    if (finder == null)
      synchronized (DoctorFinder.class) {
        if (finder == null) finder = new InsCompanyFinder();
      }
    return finder;
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
