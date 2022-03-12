package clientspackage;

import visitorpackage.ClientVisitor;
import inputoutputpackage.Communication;
import diseasepackage.Disease;
import diseasepackage.Symptom;
import java.util.ArrayList;
import java.util.List;

public class BasicClient {
  protected String FIRST_NAME;
  protected String LAST_NAME;
  protected int AGE;
  protected Gender SEX;
  protected List<Symptom> currentSymptoms;
  protected final List<Disease> medicalHistory;

  public BasicClient() {
    FIRST_NAME = Communication.getFirstName();
    LAST_NAME = Communication.getLastName();
    AGE = Integer.parseInt(Communication.validateAge());
    SEX = Gender.getGender(Communication.validateSex());
    currentSymptoms = new ArrayList<>();
    medicalHistory = new ArrayList<>();
  }

  /*
     setters and getters for all class fields: encapsulation principle
  */
  public void setFirstName(String FIRST_NAME) {
    this.FIRST_NAME = FIRST_NAME;
  }

  public String getLastName() {
    return LAST_NAME;
  }

  public void setLastName(String LAST_NAME) {
    this.LAST_NAME = LAST_NAME;
  }

  public List<Symptom> getCurrentSymptoms() {
    return currentSymptoms;
  }

  public void setCurrentSymptoms(List<Symptom> currentSymptoms) {
    this.currentSymptoms = currentSymptoms;
  }

  public int getAge() {
    return AGE;
  }

  public void setAge(int AGE) {
    this.AGE = AGE;
  }

  public Gender getSex() {
    return SEX;
  }

  public void setSex(String SEX) {
    this.SEX = Gender.getGender(SEX);
  }

  public List<Disease> getMedicalHistory() {
    return medicalHistory;
  }

  public void addDiseaseToMedicalHistory(Disease d) {
    this.medicalHistory.add(d);
  }

  public void accept(ClientVisitor v) {
    v.visitBasicClient(this);
  }
}
