package sosad;

import java.util.List;

/*
 * PredictionSystem class is the singleton class
 * It represents the system which can find most probable
 * disease the patient has according to his symptoms
 */
public class PredictionSystem {
  // private instance of class DiseaseDatabase that represents the database of diseases
  private final DiseaseDatabase diseasesDatabase;
  // private single instance of singleton class PredictionSystem
  private static volatile PredictionSystem predSys;

  /*
   * private constructor forcing the use of
   * getInstance() to create PredictionSystem object
   */
  private PredictionSystem() {
    diseasesDatabase = DiseaseDatabase.getInstance();
  }

  /**
   * The method returns the single object of singleton class PredictionSystem or creates this first
   * instance if there was no one before
   *
   * @return Returns PredictionSystem
   */
  public static PredictionSystem getInstance() {
    if (predSys == null) {
      synchronized (PredictionSystem.class) {
        if (predSys == null) {
          predSys = new PredictionSystem();
        }
      }
    }
    return predSys;
  }

  /**
   * The method finds from the diseasesDatabase the disease that has the most symptoms identical to
   * the symptoms of the patient.
   *
   * @return Returns patientDisease
   */
  public Disease makePrediction(List<Symptom> symptoms) {
    int numOfMatches;
    int maxOfMatches = 0;
    Disease patientDisease = null;

    for (Disease illness : diseasesDatabase.getDatabase()) {
      numOfMatches = 0;

      for (Symptom symptomOfDisease : illness.getSymptoms()) {
        for (Symptom symptomOfPatient : symptoms) {
          if (symptomOfPatient.compare(symptomOfDisease)) {
            numOfMatches++;
          }
        }
      }

      if (numOfMatches > maxOfMatches) {
        maxOfMatches = numOfMatches;
        patientDisease = illness;
      }
    }
    return patientDisease;
  }
}
