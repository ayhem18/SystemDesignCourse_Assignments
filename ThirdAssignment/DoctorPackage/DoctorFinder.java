package doctorpackage;

import inputoutputpackage.AddressHandler;

public class DoctorFinder {
  private static final int defaultDoctorIndex = 0;
  private final DoctorDatabase database;
  private static volatile DoctorFinder doctorFinder;

  /** private constructor and a public static getInstance() method ensuring the singleton pattern */
  private DoctorFinder() {
    database = DoctorDatabase.getInstance();
  }

  public static DoctorFinder getInstance() {
    if (doctorFinder == null) {
      synchronized (DoctorFinder.class) {
        if (doctorFinder == null) {
          doctorFinder = new DoctorFinder();
        }
      }
    }
    return doctorFinder;
  }

  /**
   * The method calculates the distance according to model specified by calling the corresponding
   * method in AddressHandler class It takes in consideration the case where the patient's city is
   * not included in the doctor database by sending a default doctor
   *
   * @param address the patient's address
   * @return Returns the nearest doctor to the passed address
   */
  public Doctor findDoctor(String address) {
    // since the MatchPatientCity field could have been set to false from a previous call
    // we need to re-set it to true before processing the new call

    database.getDoctors().get(defaultDoctorIndex).setMatchPatientCity(true);

    int minDistance = AddressHandler.NOT_SAME_CITY_CODE;
    int indexMin = 0;
    int N = database.getDoctors().size();
    int temp;

    for (int i = 0; i < N; i++) {
      Doctor d = database.getDoctors().get(i);
      if (minDistance
          > (temp = AddressHandler.distanceBetweenTwoAddresses(address, d.getAddress()))) {
        minDistance = temp;
        indexMin = i;
      }
    }

    if (minDistance == AddressHandler.NOT_SAME_CITY_CODE) {
      database.getDoctors().get(defaultDoctorIndex).setMatchPatientCity(false);
    }
    return database.getDoctors().get(indexMin);
  }
}
