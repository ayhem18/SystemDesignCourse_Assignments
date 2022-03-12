package insurancepackage;

import diseasepackage.DiseaseDatabase;
import java.util.ArrayList;
import java.util.List;

/** This class represents a database for the application's partners. */
public class InsCompaniesDatabase {
  private static volatile InsCompaniesDatabase database;

  // private arraylist that contains instances of InsuranceCompany
  private final ArrayList<InsuranceCompany> COMPANIES;

  // private arraylist that stores the partners' names
  private final ArrayList<String> names;

  /*
   * private constructor where we add a default set of insurance companies
   */
  private InsCompaniesDatabase() {
    COMPANIES = new ArrayList<>();
    names = new ArrayList<>();
    COMPANIES.add(new InsuranceCompany("SOGAZ"));
    COMPANIES.add(new InsuranceCompany("ALFA"));
    COMPANIES.add(new InsuranceCompany("INGOSSTRACK"));
    COMPANIES.add(new InsuranceCompany("SBERBANK"));
    for (InsuranceCompany company : COMPANIES) {
      names.add(company.getName());
    }
  }

  public static InsCompaniesDatabase getInstance() {
    if (database == null) {
      synchronized (DiseaseDatabase.class) {
        if (database == null) database = new InsCompaniesDatabase();
      }
    }
    return database;
  }

  /**
   * The method returns the ArrayList of Insurance Company that we have in database
   *
   * @return Returns data
   */
  public ArrayList<InsuranceCompany> getDatabase() {
    return COMPANIES;
  }

  /** The method adds an insurance company to the database */
  public void addCompany(InsuranceCompany company) {
    COMPANIES.add(company);
    names.add(company.getName());
  }

  // this method returns a list containing the partners' names
  public List<String> getNames() {
    return names;
  }
}
