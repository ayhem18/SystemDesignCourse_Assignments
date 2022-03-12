package sosadmemberships;

import sosad.DiseaseDatabase;
import java.util.ArrayList;
import java.util.List;

/** This class represents a database for the application's partners. */
public class InsCompaniesDatabase {
  private static volatile InsCompaniesDatabase database;

  // private arraylist that contains instances of InsuranceCompany
  private final ArrayList<InsuranceCompany> data;

  // private arraylist that stores the partners' names
  private final ArrayList<String> names;

  /*
   * private constructor where we add a default set of insurance companies
   */
  private InsCompaniesDatabase() {
    data = new ArrayList<>();
    names = new ArrayList<>();
    data.add(new InsuranceCompany("SOGAZ"));
    data.add(new InsuranceCompany("ALFA"));
    data.add(new InsuranceCompany("INGOSSTRACK"));
    data.add(new InsuranceCompany("SBERBANK"));
    for (InsuranceCompany company : data) {
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
    return data;
  }

  /** The method adds an insurance company to the database */
  public void addCompany(InsuranceCompany company) {
    data.add(company);
    names.add(company.getName());
  }

  // this method returns a list containing the partners' names
  public List<String> getNames() {
    return names;
  }
}
